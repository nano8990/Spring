package com.kdj.address;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.sqlite.SQLiteConfig;

public class DBCommon<T> {
	private String dbFileName;
	private String tableName;
	private Connection connection;
	private ArrayList<T> dataList;

	public DBCommon(String dbFileName, String tableName) {
		this.dbFileName = dbFileName;
		this.tableName = tableName;
	}

	private void open() {
		try {
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			this.connection = DriverManager.getConnection("jdbc:sqlite:/" + this.dbFileName, config.toProperties());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void close() {
		if (this.connection != null) {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		this.connection = null;
	}

	public void createTable(T t) {
		if (this.connection == null) {
			this.open();
		}
		try {
			Class<?> dataClass = t.getClass();
			Field[] dataClassFields = dataClass.getDeclaredFields();
			String query = "";
			for (Field field : dataClassFields) {
				if (!query.isEmpty()) {
					query = query + ", ";
				}
				String fieldType = field.getType().toString();
				String fieldName = field.getName();

				if (fieldType.matches("int")) {
					query += fieldName + ' ' + "INTEGER";
				} else if (fieldType.matches("double|float")) {
					query += fieldName + ' ' + "REAL";
				} else if (fieldType.matches(".*String")) {
					query += fieldName + ' ' + "TEXT";
				}

				if (fieldName.matches("idx")) {
					query += " PRIMARY KEY AUTOINCREMENT";
				}
			}
			query = "CREATE TABLE " + this.tableName + " (" + query + ");";
			Statement statement = this.connection.createStatement();
			int result = statement.executeUpdate(query);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
	}

	public void insertData(T t) {
		if (this.connection == null) {
			this.open();
		}
		try {
			Class<?> dataClass = t.getClass();
			Field[] dataClassFields = dataClass.getDeclaredFields();
			String fieldString = "";
			String valueString = "";
			for (Field field : dataClassFields) {
				String fieldName = field.getName();
				String fieldType = field.getType().toString();
				if (fieldName.matches("idx")) {
					continue;
				}
				if (!fieldString.isEmpty()) {
					fieldString = fieldString + ", ";
				}
				if (!valueString.isEmpty()) {
					valueString = valueString + ", ";
				}
				if (fieldType.matches(".*String")) {
					valueString = valueString + "'" + field.get(t) + "'";
				} else {
					valueString = valueString + field.get(t);
				}
				fieldString += fieldName;
			}
			String query = "INSERT INTO " + this.tableName + "(" + fieldString + ") VALUES(" + valueString + ");";
			Statement statement = this.connection.createStatement();
			int result = statement.executeUpdate(query);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
	}
	
	public void updateData(T t) {
		if (this.connection == null) {
			this.open();
		}
		try {
			Class<?> dataClass = t.getClass();
			Field[] dataClassFields = dataClass.getDeclaredFields();
			String queryString = "";
			int whereCondition = -1;
			for (Field field : dataClassFields) {
				String fieldName = field.getName();
				String fieldType = field.getType().toString();
				if (fieldName.matches("idx")) {
					whereCondition = (Integer) field.get(t);
					continue;
				}
				if (!queryString.isEmpty()) {
					queryString = queryString + ", ";
				}
				queryString += fieldName + "=";
				if (fieldType.matches(".*String")) {
					queryString = queryString + "'" + field.get(t) + "'";
				} else {
					queryString = queryString + field.get(t);
				}
			}
			String query = "UPDATE " + this.tableName + "SET (" + queryString + ") WHERE idx=" + whereCondition + ";";
			Statement statement = this.connection.createStatement();
			int result = statement.executeUpdate(query);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
	}
	
	public void deleteData(T t) {
		if (this.connection == null) {
			this.open();
		}
		try {
			Class<?> dataClass = t.getClass();
			Field[] dataClassFields = dataClass.getDeclaredFields();
			int whereCondition = -1;
			for (Field field : dataClassFields) {
				String fieldName = field.getName();
				if (fieldName.matches("idx")) {
					whereCondition = (Integer) field.get(t);
					continue;
				}
			}
			String query = "DELETE FROM " + this.tableName + " WHERE idx=" + whereCondition + ";";
			Statement statement = this.connection.createStatement();
			int result = statement.executeUpdate(query);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
	}	
}
