package oop.handler.impl;

import oop.handler.Handler;
import oop.javabean.Candidate;

public class UnitDirectoryHandler implements Handler {

	/*
	 * singleton pattern
	 */
	private static UnitDirectoryHandler handler;

	private UnitDirectoryHandler() {

	}

	public static UnitDirectoryHandler getInstance() {
		if (handler == null) {
			handler = new UnitDirectoryHandler();
		}
		return handler;
	}

	@Override
	public byte[] perform(Candidate candidate, byte[] target) {
		// TODO Auto-generated method stub
		return null;
	}

}
