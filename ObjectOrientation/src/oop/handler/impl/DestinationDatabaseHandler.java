package oop.handler.impl;

import oop.handler.Handler;
import oop.javabean.Candidate;

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
		// TODO Auto-generated method stub
		return null;
	}

}
