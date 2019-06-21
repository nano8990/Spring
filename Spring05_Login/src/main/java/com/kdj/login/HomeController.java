package com.kdj.login;

import java.util.Locale;

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
	public String home(Locale locale, Model model) {
		return "list";
	}

	@RequestMapping(value = "/signin", method = RequestMethod.GET)
	public String signin(Locale locale, Model model) {
		return "signin";
	}

	@RequestMapping(value = "/do_signin", method = RequestMethod.POST)
	public String doSignin(Locale locale, Model model) {
		return "redirect:/";
	}
	
	@RequestMapping(value = "/signout", method = RequestMethod.GET)
	public String signout(Locale locale, Model model) {
		return "redirect:/signin";
	}

	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insert(Locale locale, Model model) {
		return "insert";
	}

	@RequestMapping(value = "/insert_data", method = RequestMethod.POST)
	public String insertData(Locale locale, Model model, @RequestParam("id") String id,
			@RequestParam("password1") String password1, @RequestParam("password2") String password2,
			@RequestParam("name") String name, @RequestParam("age") String age, @RequestParam("sex") String sex) {
		return "done";
	}

	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modify(Locale locale, Model model) {
		return "modify";
	}

	@RequestMapping(value = "/modify_data", method = RequestMethod.POST)
	public String modifyData(Locale locale, Model model, @RequestParam("id") String id,
			@RequestParam("origin_password") String originPassword, @RequestParam("password1") String password1,
			@RequestParam("password2") String password2, @RequestParam("name") String name,
			@RequestParam("age") String age, @RequestParam("sex") String sex) {
		return "done";
	}

}
