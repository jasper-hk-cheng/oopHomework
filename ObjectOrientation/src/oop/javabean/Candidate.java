package oop.javabean;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Candidate {

	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	private Config config;
	private Timestamp fileDateTime;
	private String name;
	private String processName;
	private Integer size;

	public Candidate(Config config, String fileDateTime, String name, String processName, Integer size) {
		try {
			this.config = config;
			this.fileDateTime = new Timestamp(dateFormat.parse(fileDateTime).getTime());
			this.name = name;
			this.processName = processName;
			this.size = size;

		} catch (ParseException e) {
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

	public Integer getSize() {
		return size;
	}
}
