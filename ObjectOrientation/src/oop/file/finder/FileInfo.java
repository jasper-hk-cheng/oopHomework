package oop.file.finder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.sql.Timestamp;

public class FileInfo {

	private Path path;
	
	public FileInfo(String path) {
		this.path = Paths.get(path);
	}
	
	public Timestamp getCreationTime() {
		try {
			BasicFileAttributes basicFileAttributes = Files.readAttributes(this.path, BasicFileAttributes.class, LinkOption.NOFOLLOW_LINKS);
			FileTime fileTime = basicFileAttributes.creationTime();
			return new Timestamp(fileTime.toMillis());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public long getLength() {
		return this.path.toFile().length();
	}
}
