package oop.file.finder;

import oop.file.finder.impl.LocalFileFinder;
import oop.javabean.Config;
import oop.my.backup.candidate.Candidate;

public class FileFinderFactory {

	public static FileFinder<Candidate> create(String finder, Config config) {
		if ("file".equals(finder)) {
			return new LocalFileFinder(config);
		}
		return null;
	}
}
