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
			return "�̸��� �Է��ϼ���.";
		} else if (student.address.equals("")) {
			return "�ּҸ� �Է��ϼ���.";
		} else if (student.address.equals("")) {
			return "��������� �Է��ϼ���";
		}
		String query = "INSERT INTO " + super.dbTableName + "(name, address, birthday) VALUES('" + student.name + "', '"
				+ student.address + "', '" + student.birthday + "');";
		Statement statement = super.connection.createStatement();
		statement.executeUpdate(query);
		statement.close();
		return "�ԷµǾ����ϴ�.";
	}
}
