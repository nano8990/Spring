package com.kdj.address;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SelectDatabase extends DatabaseConnection {

	ArrayList<Student> students = new ArrayList<Student>();
	
	public SelectDatabase(String databaseFileName, String dbTableName) {
		super.dbFileName = databaseFileName;
		super.dbTableName = dbTableName;
	}
	
	public void selectData() throws SQLException {
		String query = "SELECT * FROM " + super.dbTableName + " WHERE ?;";
		PreparedStatement preparedStatement = super.connection.prepareStatement(query);
		preparedStatement.setInt(1, 1);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			this.students.add(new Student(resultSet.getInt("idx"), resultSet.getString("name"),
					resultSet.getString("address"), resultSet.getString("birthday")));
		}
		resultSet.close();
		preparedStatement.close();
	}

	public String getHtmlString() {
		String htmlString = "";
		for (int i = 0; i < this.students.size(); i++) {
			htmlString = htmlString + "<tr>";
			htmlString = htmlString + "<td>";
			htmlString = htmlString + this.students.get(i).idx;
			htmlString = htmlString + "</td>";
			htmlString = htmlString + "<td>";
			htmlString = htmlString + this.students.get(i).name;
			htmlString = htmlString + "</td>";
			htmlString = htmlString + "<td>";
			htmlString = htmlString + this.students.get(i).address;
			htmlString = htmlString + "</td>";
			htmlString = htmlString + "<td>";
			htmlString = htmlString + this.students.get(i).birthday;
			htmlString = htmlString + "</td>";
			htmlString = htmlString + "</tr>";
		}
		return htmlString;
	}
	public int getAddress(String inputString) {
		int count = 0;
		for (int i = 0; i < this.students.size(); i++) {
			String address = students.get(i).address;
			if (address.matches(".*"+ inputString +".*")) {
				count++;
			}
		}
		return count;
	}
}
