package homework02;

import java.util.ArrayList;
import java.util.List;

import homework01.ConfigManager;
import homework01.ScheduleManager;

public class MyBackupService {

	private List<JsonManager> managers = new ArrayList<JsonManager>();

	public MyBackupService() {
		managers.add(new ConfigManager());
		managers.add(new ScheduleManager());
	}

	public void processJSONConfig() {
		managers.forEach(manager -> {
			manager.processJsonConfig();
		});
	}

	public void doBackUp() {
		
	}

	/*
	 * getter
	 */
	public List<JsonManager> getManagers() {
		return managers;
	}
}
