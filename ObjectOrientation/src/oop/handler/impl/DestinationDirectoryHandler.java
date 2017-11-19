package oop.handler.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

import oop.handler.AbstractHandler;
import oop.javabean.Config;
import oop.my.backup.candidate.Candidate;

public class DestinationDirectoryHandler extends AbstractHandler {

	/*
	 * singleton pattern
	 */
	private static DestinationDirectoryHandler handler;

	private DestinationDirectoryHandler() {

	}

	public static DestinationDirectoryHandler getInstance() {
		if (handler == null) {
			handler = new DestinationDirectoryHandler();
		}
		return handler;
	}

	@Override
	public byte[] perform(Candidate candidate, byte[] target) {
		byte[] result = target;
		super.perform(candidate, target);

		if (target != null) {
			result = copyToDirectory(candidate, target);
		}
		return result;
	}

	/**
	 * if the byte array is copied successful, return null, or return the origin byte array...
	 * @param candidate
	 * @param target
	 * @return
	 */
	private byte[] copyToDirectory(Candidate candidate, byte[] target) {
		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(target);
			InputStreamReader isr = new InputStreamReader(bais);
			BufferedReader bufferedReader = new BufferedReader(isr);

			Config config = candidate.getConfig();

			String dir = config.getDir();
			String name = candidate.getName();
			String destinationFilePath = new StringBuffer().append(dir).append("\\").append(name).append(".bak").toString();

			FileWriter fileWriter = new FileWriter(destinationFilePath);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			String strRead;
			while ((strRead = bufferedReader.readLine()) != null) {
				bufferedWriter.write(strRead);
			}
			bais.close();
			fileWriter.close();

			return null;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return target;
	}
}
