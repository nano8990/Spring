package com.kdj.address;

import java.sql.SQLException;
import java.sql.Statement;

public class InsertDatabase extends DatabaseConnection {

	public InsertDatabase(String databaseFileName, String dbTableName) {
		super.dbFileName = databaseFileName;
		super.dbTableName = dbTableName;
	}

	public String insertData(Student student) throws SQLException {
		if (student.name.equals("")) {
			return "이름을 입력하세요.";
		} else if (student.address.equals("")) {
			return "주소를 입력하세요.";
		} else if (student.address.equals("")) {
			return "생년월일을 입력하세요";
		}
		String query = "INSERT INTO " + super.dbTableName + "(name, address, birthday) VALUES('" + student.name + "', '"
				+ student.address + "', '" + student.birthday + "');";
		Statement statement = super.connection.createStatement();
		statement.executeUpdate(query);
		statement.close();
		return "입력되었습니다.";
	}
}
