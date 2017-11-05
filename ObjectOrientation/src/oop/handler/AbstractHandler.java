package oop.handler;

import oop.javabean.Candidate;

public abstract class AbstractHandler implements Handler {

	/**
	 * for commonly initial all the handler implements
	 */
	@Override
	public byte[] perform(Candidate candidate, byte[] target) {

		return null;
	}
}
