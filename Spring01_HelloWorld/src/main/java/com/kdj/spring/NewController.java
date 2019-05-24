package com.kdj.spring;

import java.sql.SQLException;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class NewController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public String selectGet(Locale locale, Model model) {
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
		return "select";
	}

	@RequestMapping(value = "/select", method = RequestMethod.POST)
	public String selectPost(Locale locale, Model model) {
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
		return "select";
	}
}
