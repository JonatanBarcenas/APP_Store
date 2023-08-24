package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	
	private static Database database = null;
	private String name;
	private String user;
	private String password;
	private String driver;
	private String protocol;
	private Connection connection;
	
	private Database(String name, String user, String password, String driver, String protocol) {
		super();
		this.name = name;
		this.user = user;
		this.password = password;
		this.driver = driver;
		this.protocol = protocol;
	}
	
	public Connection getConnection() {
		return connection;
	}
	
	public String doConnection() {
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(protocol + name, user, password);
			return "successful connection";
		} catch (ClassNotFoundException | SQLException e) {
			return e.toString();
		}
	}
	
	public void closeConnection() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static Database newInstance(String name, String user, String password, String driver, String protocol) {
		return database == null ? new Database(name, user, password, driver, protocol) : database;
	}

}
