package oop.handler;

import oop.my.backup.candidate.Candidate;

public interface Handler {

	//why do we need the candidate object as input parameter here ??	
	byte[] perform(Candidate candidate, byte[] target);
}
