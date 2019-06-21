package com.kdj.address;

import java.sql.Statement;

public class CreateDatabase extends DatabaseConnection {

	public CreateDatabase(String databaseFileName, String dbTableName) {
		super.dbFileName = databaseFileName;
		super.dbTableName = dbTableName;
	}
	
	public void createTable() throws Exception {
		if (super.connection == null) {
			throw new Exception("DB is not open");
		}
		String query = "CREATE TABLE " + super.dbTableName
				+ "(idx INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, address TEXT, birthday TEXT);";
		Statement statement = super.connection.createStatement();
		statement.executeUpdate(query);
		statement.close();
	}
}
