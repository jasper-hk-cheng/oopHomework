package oop.handler.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import oop.handler.AbstractHandler;
import oop.javabean.Candidate;
import oop.javabean.Config;

public class UnitFileHandler extends AbstractHandler {

	/*
	 * singleton pattern
	 */
	private static UnitFileHandler handler;

	private UnitFileHandler() {

	}

	public static UnitFileHandler getInstance() {
		if (handler == null) {
			handler = new UnitFileHandler();
		}
		return handler;
	}

	@Override
	public byte[] perform(Candidate candidate, byte[] target) {
		byte[] result = target;
		super.perform(candidate, target);

		if (target == null) {
			result = convertFileToByteArray(candidate);
		} else {
			convertByteArrayToFile(candidate, target);
		}
		return result;
	}

	private byte[] convertFileToByteArray(Candidate candidate) {
		try {
			Config config = candidate.getConfig();

			String location = config.getLocation();
			String name = candidate.getName();
			String ext = config.getExt();
			String filePath = new StringBuffer().append(location).append("\\").append(name).append(".").append(ext).toString();

			Path path = Paths.get(filePath);
			return Files.readAllBytes(path);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private void convertByteArrayToFile(Candidate candidate, byte[] target) {
		try {
			Config config = candidate.getConfig();

			String dir = config.getDir();
			String name = candidate.getName();
			String ext = config.getExt();
			String filePath = new StringBuffer().append(dir).append("\\").append(name).append(".").append(ext).toString();

			Path path = Paths.get(filePath);
			Files.write(path, target, StandardOpenOption.CREATE);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
