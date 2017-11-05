package oop.handler.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;

import oop.handler.AbstractHandler;
import oop.javabean.Candidate;

public class ZipHandler extends AbstractHandler {

	/*
	 * sington pattern
	 */
	private static ZipHandler handler;

	private ZipHandler() {

	}

	public static ZipHandler getInstance() {
		if (handler == null) {
			handler = new ZipHandler();
		}
		return handler;
	}

	@Override
	public byte[] perform(Candidate candidate, byte[] target) {
		byte[] result = target;
		super.perform(candidate, target);

		if (target != null) {
			result = zipData(candidate, target);
		}
		return result;
	}

	private byte[] zipData(Candidate candidate, byte[] target) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream(target.length);

			Deflater deflater = new Deflater();
			deflater.setInput(target);
			deflater.finish();

			byte[] buffer = new byte[1024 * 1024];

			while (!deflater.finished()) {
				int compressLen = deflater.deflate(buffer);
				baos.write(buffer, 0, compressLen);
			}
			baos.close();
			return baos.toByteArray();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
