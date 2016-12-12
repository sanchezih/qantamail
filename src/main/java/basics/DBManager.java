package basics;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBManager {

	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_NAME = "qantamail";
	private static final String DB_URL = "jdbc:mysql://localhost/" + DB_NAME;
	private static final String DB_USERNAME = "root";
	private static final String DB_PASSWORD = "";
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
		Connection conexion = null;
		try {
			Class.forName(DRIVER).newInstance();
			conexion = DriverManager.getConnection(DB_URL, DB_USERNAME,
					DB_PASSWORD);
			conexion.setAutoCommit(false);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return conexion;
	}

	public void shutdown() throws Exception {
		Connection conn = connect();
		Statement s = conn.createStatement();
		s.execute("SHUTDOWN");
		conn.close();
	}
}
