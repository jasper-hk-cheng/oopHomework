package oop.handler.impl;

import java.util.Base64;

import oop.handler.AbstractHandler;
import oop.my.backup.candidate.Candidate;

public class EncodeHandler extends AbstractHandler {

	/*
	 * singleton pattern
	 */
	private static EncodeHandler handler;

	private EncodeHandler() {

	}

	public static EncodeHandler getInstance() {
		if (handler == null) {
			handler = new EncodeHandler();
		}
		return handler;
	}

	@Override
	public byte[] perform(Candidate candidate, byte[] target) {
		byte[] result = target;
		super.perform(candidate, target);

		if (target != null) {
			result = encodeData(candidate, target);
		}
		return result;
	}

	private byte[] encodeData(Candidate candidate, byte[] target) {
		byte[] result = target;

		if (target != null) {
			result = Base64.getEncoder().encode(target);
		}
		return result;
	}
}
