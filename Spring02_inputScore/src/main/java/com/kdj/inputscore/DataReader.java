package com.kdj.inputscore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.sqlite.SQLiteConfig;

public class DataReader {
	private Connection connection;
	private String dbFileName;
	private String dbTableName;
	static {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DataReader(String databaseFileName, String dbTableName) {
		this.dbFileName = databaseFileName;
		this.dbTableName = dbTableName;
	}

	public boolean open() {
		try {
			SQLiteConfig config = new SQLiteConfig();
			this.connection = DriverManager.getConnection("jdbc:sqlite:/" + this.dbFileName, config.toProperties());
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean close() {
		if (this.connection == null) {
			return true;
		}
		try {
			this.connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public int createTable() throws Exception {
		if (this.connection == null) {
			throw new Exception("DB is not open");
		}
		String query = "CREATE TABLE " + this.dbTableName + "(idx INT PRIMARY KEY, name TEXT, score REAL);";
		Statement statement = this.connection.createStatement();
		int result = statement.executeUpdate(query);
		statement.close();
		return result;
	}

	public int insertData(String s1, String s2) throws Exception {
		if (this.connection == null) {
			throw new Exception("DB is not open");
		}
		String query = "INSERT INTO " + this.dbTableName + "(name, score) VALUES('" + s1 + "', " + s2 + ")";
		Statement statement = this.connection.createStatement();
		int result = statement.executeUpdate(query);
		statement.close();
		return result;
	}
	public String selectData() throws SQLException, Exception {
		if (this.connection == null) {
			throw new Exception("DB is not open");
		}
		String result = "";
		String query = "SELECT * FROM " + this.dbTableName + " WHERE ?;";
		PreparedStatement preparedStatement = this.connection.prepareStatement(query);
		preparedStatement.setInt(1, 1);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			System.out.println(resultSet.getString("name"));
			result += resultSet.getString("name");
			result += " : ";
			result += resultSet.getString("score");
			result += "<br>";
		}
		resultSet.close();
		preparedStatement.close();
		return result;
	}
}