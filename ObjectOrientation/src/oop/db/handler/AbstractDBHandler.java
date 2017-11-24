package oop.db.handler;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.hsqldb.jdbcDriver;

import oop.my.backup.candidate.Candidate;

public abstract class AbstractDBHandler implements DBHandler {

	private String dbName;
	private String dbPort;

	protected Connection conn;

	/**
	 * read the database configuration the path of properties:
	 */
	public AbstractDBHandler() {

		// TODO move to initial method
		try {
			FileInputStream fis = new FileInputStream("..\\ObjectOrientation\\database\\oopDatabase\\server.properties");

			Properties properties = new Properties();
			properties.load(fis);

			this.dbName = properties.getProperty("server.dbname.0");
			this.dbPort = properties.getProperty("server.port");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public byte[] perform(Candidate candidate, byte[] target) {
		return null;
	}

	@Override
	public void openConnection() {
		try {
			if (conn != null) {
				if (conn.isClosed()) {
					createConnection();
				} else {
					System.out.println("the DB has been already connected and can be use directly !!");
					return;
				}
			} else {
				createConnection();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void createConnection() throws Exception {

		if (dbName == null || dbName.length() == 0 || dbPort == null || dbPort.length() == 0) {
			throw new Exception("the loading of DB configuration is not completed !!");
		}
		Class.forName(jdbcDriver.class.getName());

		StringBuffer dbUrlBuffer = new StringBuffer().append("jdbc:hsqldb:hsql://localhost:").append(dbPort).append("/").append(dbName);
		this.conn = DriverManager.getConnection(dbUrlBuffer.toString());
	}

	@Override
	public void closeConnection() {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
				conn = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
