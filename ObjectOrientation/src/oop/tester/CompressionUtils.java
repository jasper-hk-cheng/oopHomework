package oop.tester;

import java.io.ByteArrayOutputStream;

import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * notice: for testing and studying only, not a part of this homework.
 * if you are the supervisor of the homework, please ignore all the java code below...
 * 
 * @author jasper
 *
 */
public class CompressionUtils {

	public static byte[] compress(byte[] data) throws IOException {
		
		Deflater deflater = new Deflater();
		deflater.setInput(data);
		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		deflater.finish();
		byte[] buffer = new byte[1024];
		while (!deflater.finished()) {
			int count = deflater.deflate(buffer); // returns the generated code... index
			outputStream.write(buffer, 0, count);
		}
		outputStream.close();
		byte[] output = outputStream.toByteArray();
		return output;
	}

	public static byte[] decompress(byte[] data) throws IOException, DataFormatException {
		Inflater inflater = new Inflater();
		inflater.setInput(data);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		while (!inflater.finished()) {
			int count = inflater.inflate(buffer);
			outputStream.write(buffer, 0, count);
		}
		outputStream.close();
		byte[] output = outputStream.toByteArray();
		return output;
	}
	
	
	
	public static byte[] zipBytes(String filename, byte[] input) throws IOException {
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    ZipOutputStream zos = new ZipOutputStream(baos);
	    ZipEntry entry = new ZipEntry(filename);
	    entry.setSize(input.length);
	    zos.putNextEntry(entry);
	    zos.write(input);
	    zos.closeEntry();
	    zos.close();
	    return baos.toByteArray();
	}
}
