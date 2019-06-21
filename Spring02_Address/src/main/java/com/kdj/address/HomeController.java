package com.kdj.address;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
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

	/**
	 * Simply selects the home view to render by returning its name.
	 */

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		SelectDatabase selectDatabase = new SelectDatabase("c:\\tomcat\\student190527.sqlite", "student190527");
		if (selectDatabase.open()) {
			try {
				selectDatabase.selectData();
				String result = selectDatabase.getHtmlString();
				model.addAttribute("resultString", result);

				int seoulCount = selectDatabase.getAddress("서울");
				int gyeonggiCount = selectDatabase.getAddress("경기");
				String sgCount = "서울 : " + seoulCount + "명, 경기 : " + gyeonggiCount + "명";
				model.addAttribute("sgCount", sgCount);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		selectDatabase.close();
		return "list";
	}

	@RequestMapping(value = "/input_student", method = RequestMethod.GET)
	public String inputStudent(Locale locale, Model model) {
		return "input_student";
	}

	@RequestMapping(value = "/input_data", method = RequestMethod.GET)
	public String inputData(@RequestParam("name") String name, @RequestParam("address") String address,
			@RequestParam("birthday") String birthday, Model model) {
		InsertDatabase insertDatabase = new InsertDatabase("c:\\tomcat\\student190527.sqlite", "student190527");
		Student student = new Student(name, address, birthday);
		if (insertDatabase.open()) {
			try {
				model.addAttribute("resultString", insertDatabase.insertData(student));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		insertDatabase.close();
		return "done";
	}

	@RequestMapping(value = "/create_db", method = RequestMethod.GET)
	public String createDb(Locale locale, Model model) {
		CreateDatabase createDatabase = new CreateDatabase("c:\\tomcat\\student190527.sqlite", "student190527");
		if (createDatabase.open()) {
			try {
				createDatabase.createTable();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		createDatabase.close();
		return "done";
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(Locale locale, Model model) {
		return "people";
	}

	@RequestMapping(value = "/test/create_table", method = RequestMethod.GET)
	public String createTable(Locale locale, Model model) {
		DBCommon<People> dbCommon = new DBCommon<People>("c:\\tomcat\\people.sqlite", "people");
		dbCommon.createTable(new People());
		return "done";
	}

	@RequestMapping(value = "/test/insert_data", method = RequestMethod.GET)
	public String insertData(@RequestParam("name") String name, @RequestParam("favoriteColor") String favoriteColor) {
		DBCommon<People> dbCommon = new DBCommon<People>("c:\\tomcat\\people.sqlite", "people");
		System.out.println(name + " " + favoriteColor);
		dbCommon.insertData(new People(name, favoriteColor));
		return "done";
	}
}
