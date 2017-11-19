package oop.tester;

import oop.manager.JsonManager;
import oop.service.MyBackupService;

public class JsonConfigReadingTester {

	public static void main(String[] args) {

		MyBackupService myBackupService = new MyBackupService();
		
		// check the result of processJSONConfig method
		myBackupService.getManagers().forEach(JsonManager::printListContent);
		myBackupService.getManagers().forEach(mamager -> {
			System.out.println(mamager.getCount());
		});

		myBackupService.simpleBackup();
		myBackupService.scheduledBackup();
		/*
		 * notice: the backup file will be generated at package "oop.backup.file.goal"
		 * after execute the main method, please press the F5 to refresh this workspace to check the *.bak files.
		 */
	}
}
