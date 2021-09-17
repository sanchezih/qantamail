package basics;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBManager {
	
	
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DB_NAME = "qantamail";
	private static final String DB_URL = "jdbc:mysql://192.168.4.172/" + DB_NAME;
	private static final String DB_USERNAME = "root";
	private static final String DB_PASSWORD = "password1234";
	private static DBManager instance = null;

	private DBManager() {
	}

	public static DBManager getInstance() {
		if (instance == null) {
			instance = new DBManager();
		}
		return instance;
	}

	public Connection connect() {
		Connection connection = null;
		InputStream input = DBManager.class.getClassLoader().getResourceAsStream("config.properties");
		try {
			Class.forName(DRIVER).newInstance();
			connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
			connection.setAutoCommit(false);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return connection;
	}

	public void shutdown() throws Exception {
		Connection connection = connect();
		Statement statement = connection.createStatement();
		statement.execute("SHUTDOWN");
		connection.close();
	}
}
