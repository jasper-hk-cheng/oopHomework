package oop.handler.impl;

import oop.handler.Handler;
import oop.my.backup.candidate.Candidate;

public class DestinationDatabaseHandler implements Handler {

	/*
	 * singleton pattern
	 */
	private static DestinationDatabaseHandler handler;

	private DestinationDatabaseHandler() {

	}

	public static DestinationDatabaseHandler getInstance() {
		if (handler == null) {
			handler = new DestinationDatabaseHandler();
		}
		return handler;
	}

	@Override
	public byte[] perform(Candidate candidate, byte[] target) {
		return null;
	}

}
