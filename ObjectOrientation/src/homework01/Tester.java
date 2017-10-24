package homework01;

import homework02.MyBackupService;

public class Tester {

	public static void main(String[] args) {

		MyBackupService myBackupService = new MyBackupService();
		myBackupService.processJSONConfig();
		myBackupService.getManagers().forEach(manager -> manager.printListContent());
	}
}