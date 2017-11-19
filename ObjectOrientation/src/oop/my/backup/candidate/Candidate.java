package oop.my.backup.candidate;

import java.sql.Timestamp;

import oop.javabean.Config;

public class Candidate {

	private Config config;
	private Timestamp fileDateTime;
	private String name;
	private String processName;
	private long size;
	
	/**
	 * for candidateFactory only, so be the default constructor 
	 * @param config
	 * @param fileDateTime
	 * @param name
	 * @param size
	 */
	Candidate(Config config, Timestamp fileDateTime, String name, long size) {
		try {
			this.config = config;
			this.fileDateTime = fileDateTime;
			this.name = name;
			this.size = size;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * getter
	 */
	public Config getConfig() {
		return config;
	}

	public Timestamp getFileDateTime() {
		return fileDateTime;
	}

	public String getName() {
		return name;
	}

	public String getProcessName() {
		return processName;
	}

	public long getSize() {
		return size;
	}
}
