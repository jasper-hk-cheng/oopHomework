package oop.file.finder.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import oop.file.finder.AbstractFileFinder;
import oop.file.finder.FileInfo;
import oop.javabean.Config;
import oop.my.backup.candidate.Candidate;
import oop.my.backup.candidate.CandidateFactory;

public class LocalFileFinder extends AbstractFileFinder {

	private StringBuffer subDirPath = new StringBuffer();

	// public LocalFileFinder() {
	//
	// }

	public LocalFileFinder(Config config) {
		super(config);

		/*
		 * files
		 */
		List<String> fileNameList = new ArrayList<String>();
		File rootDir = new File(config.getLocation());
		fileNameList = getFiles(rootDir, fileNameList, config.getExt(), config.getSubDirectory());
		this.files = fileNameList.toArray(new String[fileNameList.size()]);
	}

	/**
	 * get all the file names in the sub directory by recursive invocation
	 * 
	 * @param parentDir
	 * @param fileNames
	 * @return
	 */
	private List<String> getFiles(File parentDir, List<String> fileNameList, String fileExtName, boolean isSubDirectory) {

		Arrays.asList(parentDir.listFiles()).forEach(file -> {
			if (file.isFile()) {
				String name = file.getName();
				String[] nameArray = name.split("\\.");
				String fileName = nameArray[0];
				String eachFileExtName = nameArray[1];

				if (eachFileExtName.equalsIgnoreCase(fileExtName)) {
					fileName = new StringBuffer(subDirPath).append(fileName).toString();
					fileNameList.add(fileName);
				}
			} else if (file.isDirectory() && isSubDirectory) {
				/*
				 * fill the directory path buffer with the new directory name and prepare new
				 * directory under the goal directory
				 */
				subDirPath.append(file.getName());
				String newDirPath = new StringBuffer(this.config.getDir()).append("\\").append(subDirPath).toString();
				File newGoalSubDir = new File(newDirPath);
				if (!newGoalSubDir.exists()) {
					newGoalSubDir.mkdirs();
				}
				subDirPath.append("\\");

				getFiles(file, fileNameList, fileExtName, isSubDirectory);
			}
		});

		// clear the string buffer
		subDirPath = new StringBuffer();

		return fileNameList;
	}

	@Override
	protected Candidate createCandidate(String fileName) {
		FileInfo fileInfo;
		Candidate candidate = null;
		
		String path = config.getLocation() + "\\" + fileName + "." + config.getExt();
		File targetFile = new File(path);
		if (targetFile.exists() && targetFile.isFile()) {
			fileInfo = new FileInfo(path);
			candidate = CandidateFactory.create(this.config, fileInfo.getCreationTime(), fileName, fileInfo.getLength());
		}
		return candidate;
	}
}
