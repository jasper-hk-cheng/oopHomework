package oop.handler.impl;

import oop.db.handler.DBHandler;
import oop.db.handler.impl.DBBackupHandler;
import oop.db.handler.impl.DBLogHandler;
import oop.handler.AbstractHandler;
import oop.my.backup.candidate.Candidate;

public class DBAdapter extends AbstractHandler {

	/*
	 * singleton pattern
	 */
	private static DBAdapter dbAdapter;

	private DBAdapter() {

	}

	public static DBAdapter getInstance() {
		if (dbAdapter == null) {
			dbAdapter = new DBAdapter();
		}
		return dbAdapter;
	}

	private DBHandler backupHandler = new DBBackupHandler();
	private DBHandler logHandler = new DBLogHandler();

	@Override
	public byte[] perform(Candidate candidate, byte[] target) {
		super.perform(candidate, target);
		saveBackupToDB(candidate, target);
		saveLogToDB(candidate, target);
		return target;
	}

	private void saveBackupToDB(Candidate candidate, byte[] target) {
		backupHandler.openConnection();
		backupHandler.perform(candidate, target);
		backupHandler.closeConnection();
	}

	private void saveLogToDB(Candidate candidate, byte[] target) {
		logHandler.openConnection();
		logHandler.perform(candidate, target);
		logHandler.closeConnection();
	}

}
