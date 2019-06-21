package com.kdj.signin;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest request, Locale locale, Model model) {
		HttpSession session = request.getSession();
		Object isSignIn = session.getAttribute("sign_in");
		if (isSignIn != null && (Boolean)isSignIn) {
			DBCommon<Person> dbCommon = new DBCommon<Person>("c:\\tomcat\\student190527.sqlite", "student190527");
			model.addAttribute("select_result", dbCommon.selectDataTableTag(new Person()));
			return "list";
		} else {
			return "redirect:/sign_in";
		}
	}

	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modify(Model model, @RequestParam("idx") int idx) {
		DBCommon<Person> dbCommon = new DBCommon<Person>("c:\\tomcat\\student190527.sqlite", "student190527");
		model.addAllAttributes(dbCommon.detailsData(new Person(), idx));
		return "modify";
	}

	@RequestMapping(value = "/update_data", method = RequestMethod.GET)
	public String updataData(Model model, @RequestParam("idx") int idx, @RequestParam("id") String id,
			@RequestParam("password") String password, @RequestParam("name") String name,
			@RequestParam("address") String address, @RequestParam("birthday") String birthday,
			@RequestParam("favorite_color") String favoriteColor) {
		DBCommon<Person> dbCommon = new DBCommon<Person>("c:\\tomcat\\student190527.sqlite", "student190527");

		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(password.getBytes());
			StringBuilder sb = new StringBuilder();
			for (byte b : md.digest()) {
				sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
			}
			password = sb.toString();
			dbCommon.updateData(new Person(id, password, name, address, birthday, favoriteColor), idx);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "done";
	}

	@RequestMapping(value = "/sign_in", method = RequestMethod.GET)
	public String signIn() {
		return "sign_in";
	}
	
	@RequestMapping(value = "/sign_out", method = RequestMethod.GET)
	public String signOut(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/sign_in";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginSubmit(HttpServletRequest request, @RequestParam("id") String id, @RequestParam("password") String password) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(password.getBytes());
			StringBuilder sb = new StringBuilder();
			for (byte b : md.digest()) {
				sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
			}
			password = sb.toString();
			
			DBCommon<Person> dbCommon = new DBCommon<Person>("c:\\tomcat\\student190527.sqlite", "student190527");
			
			if (dbCommon.signIn(id, password)) {
				
				HttpSession session = request.getSession();
				session.setAttribute("sign_in", true);
				return "redirect:/";
			} else {
				return "redirect:/sign_in";
			}
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "redirect:/sign_in";
	}
}
