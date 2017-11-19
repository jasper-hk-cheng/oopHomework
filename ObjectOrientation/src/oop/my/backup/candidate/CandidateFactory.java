package oop.my.backup.candidate;

import java.sql.Timestamp;

import oop.javabean.Config;

public class CandidateFactory {

	public static Candidate create(Config config, Timestamp fileDateTime, String name, long size) {
		return new Candidate(config, fileDateTime, name, size);
	}
}
