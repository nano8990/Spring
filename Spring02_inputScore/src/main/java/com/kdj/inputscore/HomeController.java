package com.kdj.inputscore;

import java.sql.SQLException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		DataReader dataReader = new DataReader(
				"E:\\SpeingWork\\Development Environment\\apache-tomcat-9.0.20-windows-x64\\tomcat.sqlite", "students");
		dataReader.open();
		try {
			model.addAttribute("query_result", dataReader.selectData());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
		} finally {
			dataReader.close();
		}
		return "inputscore";
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insert(HttpServletRequest httpServletRequest, Model model) {
		DataReader dataReader = new DataReader(
				"E:\\SpeingWork\\Development Environment\\apache-tomcat-9.0.20-windows-x64\\tomcat.sqlite", "students");
		dataReader.open();
		String p1 = httpServletRequest.getParameter("name");
		String p2 = httpServletRequest.getParameter("score");
		try {
			model.addAttribute("query_result", dataReader.insertData(p1, p2));
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
		} finally {
			dataReader.close();
		}
		return "done";
	}

	@RequestMapping(value = "/inputscore", method = RequestMethod.GET)
	public String inputscore(Locale locale, Model model) {
		return "inputscore";
	}
}
