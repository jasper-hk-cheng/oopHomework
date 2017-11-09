package oop.file.finder;

import oop.file.finder.impl.LocalFileFinder;
import oop.javabean.Candidate;
import oop.javabean.Config;

public class FileFinderFactory {

	public static FileFinder<Candidate> create(String finder, Config config) {
		if ("file".equals(finder)) {
			return new LocalFileFinder(config);
		}
		return null;
	}
}
