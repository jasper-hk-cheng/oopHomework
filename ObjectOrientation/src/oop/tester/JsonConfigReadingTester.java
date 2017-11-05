package oop.tester;

import oop.manager.JsonManager;
import oop.service.MyBackupService;

public class JsonConfigReadingTester {

	public static void main(String[] args) {

		MyBackupService myBackupService = new MyBackupService();
		myBackupService.processJSONConfig();
		myBackupService.getManagers().forEach(JsonManager::printListContent);
		myBackupService.getManagers().forEach(mamager -> {
			System.out.println(mamager.getCount());
		});
		
		myBackupService.doBackUp();
	}
}