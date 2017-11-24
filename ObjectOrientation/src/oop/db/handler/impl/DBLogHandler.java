package oop.db.handler.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

import oop.db.handler.AbstractDBHandler;
import oop.my.backup.candidate.Candidate;

public class DBLogHandler extends AbstractDBHandler {

	@Override
	public void openConnection() {
		super.openConnection();
	}

	@Override
	public void closeConnection() {
		super.closeConnection();
	}

	/**
	 * 實際將 byte[] 存進 MyLog table
	 */
	@Override
	public byte[] perform(Candidate candidate, byte[] target) {
		PreparedStatement insertPstmt;
		try {
			insertPstmt = conn.prepareStatement("insert into MyLog(log_content, log_date, log_user) values (?, ?, ?)");

			insertPstmt.setString(1, "backup data name: " + candidate.getName() + " size: " + target.length + " byte successfully");

			Calendar now = Calendar.getInstance();
			insertPstmt.setTimestamp(2, new Timestamp(now.getTimeInMillis()));

			insertPstmt.setString(3, "Jasper Cheng");

			int rowCount = insertPstmt.executeUpdate();
			if (rowCount == 1) {
				insertPstmt.close();
				System.out.println("log save success !!");
			} else {
				System.out.println("log save fail !!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return super.perform(candidate, target);
	}
}
