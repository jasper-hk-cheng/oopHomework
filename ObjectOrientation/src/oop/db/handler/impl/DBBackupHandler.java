package oop.db.handler.impl;

import java.io.ByteArrayInputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

import oop.db.handler.AbstractDBHandler;
import oop.my.backup.candidate.Candidate;

public class DBBackupHandler extends AbstractDBHandler {

	@Override
	public void openConnection() {
		super.openConnection();
	}

	@Override
	public void closeConnection() {
		super.closeConnection();
	}

	/**
	 * persist the byte array into the MyBackup table
	 */
	@Override
	public byte[] perform(Candidate candidate, byte[] target) {
		try {
			PreparedStatement insertPStmt = conn.prepareStatement("insert into MyBackup(binary_data, backup_date, backup_user) values (?, ?, ?)");

			ByteArrayInputStream bias = new ByteArrayInputStream(target);
			insertPStmt.setBinaryStream(1, bias);

			Calendar now = Calendar.getInstance();
			insertPStmt.setTimestamp(2, new Timestamp(now.getTimeInMillis()));

			insertPStmt.setString(3, "Jasper Cheng");

			int rowCount = insertPStmt.executeUpdate();
			if (rowCount == 1) {
				System.out.println("effect " + rowCount + " row(s)... backup data name: " + candidate.getName() + " size: " + target.length + " byte successfully");
				insertPStmt.close();
			} else {
				System.out.println("backup into the database is fail...");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return target;
	}
}
