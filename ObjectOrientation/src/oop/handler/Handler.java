package oop.handler;

import oop.my.backup.candidate.Candidate;

public interface Handler {

	byte[] perform(Candidate candidate, byte[] target);
}
