package com.myspring.com;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
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

import com.myspring.account.Account;
import com.myspring.character.Battle;
import com.myspring.character.Character;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */

	@RequestMapping(value = "/create_table", method = RequestMethod.GET)
	public String createTable(Locale locale, Model model) {
		DBCommon<Account> dbCommon = new DBCommon<Account>("c:\\tomcat\\my_game.sqlite", "account");
		dbCommon.createTable(new Account());
		return "no_session_form";
	}

	@RequestMapping(value = "/account_create", method = RequestMethod.GET)
	public String accountCreate(Locale locale, Model model, HttpServletRequest request) {
		return "account_create";
	}

	@RequestMapping(value = "/do_create_account", method = RequestMethod.GET)
	public String doCreateAccount(HttpServletRequest request, @RequestParam("id") String id,
			@RequestParam("passwd1") String passwd1, @RequestParam("passwd2") String passwd2) {
		DBCommon<Account> dbCommon = new DBCommon<Account>("c:\\tomcat\\my_game.sqlite", "account");
		if (!passwd1.equals(passwd2)) {
			logger.info("check your passwd");
			return "redirect:/account_create";
		}
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(passwd1.getBytes());
			StringBuilder sb = new StringBuilder();
			for (byte b : md.digest()) {
				sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
			}
			passwd1 = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		String inputINum = (String) session.getAttribute("key_number");
		int intInputINum;
		try {
			intInputINum = Integer.parseInt(inputINum);
			if (inputINum.length() != 6) {
				throw new Exception();
			}
		} catch (NumberFormatException e) {
			logger.info("String keyNumber Entered");
			return "redirect:/check_identify";
		} catch (Exception e) {
			logger.info("keyNumber Format Problem");
			return "redirect:/check_identify";
		}

		if (dbCommon.do_create_account(new Account(Integer.toString(intInputINum), id, passwd1))) {
			return "login";
		} else {
			logger.info("ID Duplicated");
			return "redirect:/account_create";
		}
	}

	@RequestMapping(value = "/check_identify", method = RequestMethod.GET)
	public String checkIdentify(Locale locale, Model model) {
		return "check_identify";
	}

	@RequestMapping(value = "/duplicate_check", method = RequestMethod.GET)
	public String duplicateCheck(Locale locale, Model model, HttpServletRequest request,
			@RequestParam("key_number") String keyNumber) {
		DBCommon<Account> dbCommon = new DBCommon<Account>("c:\\tomcat\\my_game.sqlite", "account");
		if (dbCommon.duplicate_check(keyNumber)) {
			return "redirect:/login";
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("key_number", keyNumber);
			return "redirect:/account_create";
		}
	}

	@RequestMapping(value = "/search_id", method = RequestMethod.GET)
	public String findId(Locale locale, Model model) {
		return "search_id";
	}

	@RequestMapping(value = "/do_search_id", method = RequestMethod.GET)
	public String dofindId(Locale locale, Model model, @RequestParam("key_number") String keyNumber) {
		DBCommon<Account> dbCommon = new DBCommon<Account>("c:\\tomcat\\my_game.sqlite", "account");
		HashMap<String, String> searchIdResult = dbCommon.do_search_id(keyNumber);
		if (searchIdResult.get("success").equals("success")) {
			model.addAttribute("id_result", searchIdResult.get("id"));
		} else if (searchIdResult.get("success").equals("fail")) {
			return "redirect:/search_id";
		} else {
			logger.info("Unknown Error");
			return "redirect:/login";
		}
		return "showId";
	}

	@RequestMapping(value = "/search_pw", method = RequestMethod.GET)
	public String findPw(Locale locale, Model model) {
		return "search_pw";
	}

	@RequestMapping(value = "/do_search_pw", method = RequestMethod.GET)
	public String dofindpw(HttpServletRequest request, Locale locale, Model model, @RequestParam("key_number") String keyNumber,
			@RequestParam("id") String id) {
		DBCommon<Account> dbCommon = new DBCommon<Account>("c:\\tomcat\\my_game.sqlite", "account");
		HashMap<String, String> searchPwResult = dbCommon.do_search_pw(keyNumber, id);

		if (searchPwResult.get("success").equals("success")) {
			model.addAttribute("pw_result", searchPwResult.get("passwd"));
		} else if (searchPwResult.get("success").equals("fail")) {
			return "redirect:/search_pw";
		} else {
			logger.info("Unknown Error");
			return "redirect:/login";
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("key_number", keyNumber);
		session.setAttribute("id_result", id);
		
		return "update_pw";
	}

	@RequestMapping(value = "/updatePw", method = RequestMethod.GET)
	public String updatePw(Locale locale, Model model) {
		return "update_pw";
	}

	@RequestMapping(value = "/do_update_pw", method = RequestMethod.GET)
	public String doupdatePw(HttpServletRequest request, Locale locale, Model model, @RequestParam("passwd") String passwd) {
		DBCommon<Account> dbCommon = new DBCommon<Account>("c:\\tomcat\\my_game.sqlite", "account");
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(passwd.getBytes());
			StringBuilder sb = new StringBuilder();
			for (byte b : md.digest()) {
				sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
			}
			passwd = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		HttpSession session = request.getSession();
		String keyNumber = (String) session.getAttribute("key_number");
		String id = (String) session.getAttribute("id_result");
		
		dbCommon.pwUpdateData(new Account(keyNumber, id, passwd), keyNumber);

		return "login";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String noSessionForm(Locale locale, Model model) {
		return "no_session_form";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Locale locale, Model model) {
		return "login";

	}
	
	@RequestMapping(value = "/create_character_form", method = RequestMethod.GET)
	public String createCharacterForm(Locale locale, Model model) {
		return "create_character_form";
	}

	@RequestMapping(value = "/yes_session_form", method = RequestMethod.GET)
	public String yesSessionForm(Locale locale, Model model) {
		return "yes_session_form";
	}

	@RequestMapping(value = "/do_create_character", method = RequestMethod.GET)
	public String doCreateCharacter(Locale locale, Model model) {
		return "yes_session_form";
	}

	@RequestMapping(value = "/do_delete_character", method = RequestMethod.GET)
	public String doDeleteCharacter(Locale locale, Model model) {
		return "yes_session_form";
	}

	@RequestMapping(value = "/select_enemy_form", method = RequestMethod.GET)
	public String selectEnemy(Locale locale, Model model) {
		return "select_enemy_form";
	}

	@RequestMapping(value = "/battle_form", method = RequestMethod.GET)
	public String battle(HttpServletRequest request, Locale locale, Model model,
			@RequestParam("enemy_id") String enemyId) {

		HttpSession session = request.getSession();
		String loginId = (String) session.getAttribute("login_id");

		// if (loginId == null) {
		// logger.info("Login Session Not Found");
		// return "redirect:/login";
		// }

		loginId = "1";

		DBCommon<Character> dbCommon = new DBCommon<Character>("c:\\tomcat\\my_game.sqlite", "character");
		HashMap<String, String> selectedCharacter;

		selectedCharacter = dbCommon.selectDataById(new Character(), loginId);

		Character myCharacter = new Character();
		int myGauge;
		int myCurrentHp;
		try {
			String strMyGauge = (String) session.getAttribute("my_gauge");
			try {
				myGauge = Integer.parseInt(strMyGauge);
			} catch (NumberFormatException e) {
				myGauge = 0;
			}
			String strMyCurrentHp = (String) session.getAttribute("my_currenthp");
			try {
				myCurrentHp = Integer.parseInt(strMyCurrentHp);
			} catch (NumberFormatException e) {
				myCurrentHp = Integer.parseInt(selectedCharacter.get("hp"));
			}
			myCharacter.setHp(Integer.parseInt(selectedCharacter.get("hp")));
			myCharacter.setAtk(Integer.parseInt(selectedCharacter.get("atk")));
			myCharacter.setDef(Integer.parseInt(selectedCharacter.get("def")));
			myCharacter.setAtkRate(Integer.parseInt(selectedCharacter.get("atkRate")));
			myCharacter.setDefRate(Integer.parseInt(selectedCharacter.get("defRate")));
			myCharacter.setSpeed(Integer.parseInt(selectedCharacter.get("speed")));
			myCharacter.setSpeed(Integer.parseInt(selectedCharacter.get("speed")));
			myCharacter.setAtkGauge(myGauge);
			myCharacter.setCurrentHp(myCurrentHp);
		} catch (NumberFormatException e) {
			logger.info("String My Character Status Entered");
			return "redirect:/select_enemy_form";
		}

		selectedCharacter = dbCommon.selectDataById(new Character(), enemyId);
		Character enemyCharacter = new Character();
		int enemyGauge;
		int enemyCurrentHp;
		try {
			String strEnemyGauge = (String) session.getAttribute("enemy_gauge");
			try {
				enemyGauge = Integer.parseInt(strEnemyGauge);
			} catch (NumberFormatException e) {
				enemyGauge = 0;
			}
			String strEnemyCurrentHp = (String) session.getAttribute("enemy_currenthp");
			try {
				enemyCurrentHp = Integer.parseInt(strEnemyCurrentHp);
			} catch (NumberFormatException e) {
				enemyCurrentHp = Integer.parseInt(selectedCharacter.get("hp"));
			}
			enemyCharacter.setHp(Integer.parseInt(selectedCharacter.get("hp")));
			enemyCharacter.setAtk(Integer.parseInt(selectedCharacter.get("atk")));
			enemyCharacter.setDef(Integer.parseInt(selectedCharacter.get("def")));
			enemyCharacter.setAtkRate(Integer.parseInt(selectedCharacter.get("atkRate")));
			enemyCharacter.setDefRate(Integer.parseInt(selectedCharacter.get("defRate")));
			enemyCharacter.setSpeed(Integer.parseInt(selectedCharacter.get("speed")));
			enemyCharacter.setSpeed(Integer.parseInt(selectedCharacter.get("speed")));
			enemyCharacter.setAtkGauge(enemyGauge);
			enemyCharacter.setCurrentHp(enemyCurrentHp);
		} catch (NumberFormatException e) {
			logger.info("String Enemy Character Status Entered");
			return "redirect:/select_enemy_form";
		}

		Battle battle = new Battle(myCharacter, enemyCharacter);

		ArrayList<int[]> gaugeList = new ArrayList<int[]>();
		int myLastGauge = myGauge;
		int enemyLastGauge = enemyGauge;
		while (myLastGauge < 100 && enemyLastGauge < 100) {
			gaugeList.add(battle.turnExecute());
			myLastGauge = gaugeList.get(gaugeList.size() - 1)[0];
			enemyLastGauge = gaugeList.get(gaugeList.size() - 1)[1];
		}

		if (gaugeList.size() == 0) {
			int[] oneGauge = { myLastGauge, enemyLastGauge };
			gaugeList.add(oneGauge);
		}

		String resultString = new String();
		for (int i = 0; i < gaugeList.size(); i++) {
			for (int j = 0; j < gaugeList.get(i).length; j++) {
				resultString += gaugeList.get(i)[j] + "&";
			}
			resultString = resultString.substring(0, resultString.length() - 1);
			resultString += "|";
		}
		resultString = resultString.substring(0, resultString.length() - 1);
		model.addAttribute("gauge_list", resultString);

		session.setAttribute("my_gauge", Integer.toString(myLastGauge));
		session.setAttribute("my_currenthp", Integer.toString(myCurrentHp));
		session.setAttribute("enemy_gauge", Integer.toString(enemyLastGauge));
		session.setAttribute("enemy_currenthp", Integer.toString(enemyCurrentHp));

		return "battle_form";
	}

}
