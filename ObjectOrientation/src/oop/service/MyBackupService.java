package oop.service;

import java.util.ArrayList;
import java.util.List;

import oop.manager.JsonManager;
import oop.manager.impl.ConfigManager;
import oop.manager.impl.HandlerMappingManager;
import oop.manager.impl.ScheduleManager;
import oop.task.TaskDispatcher;

public class MyBackupService {

	private List<JsonManager> managers = new ArrayList<JsonManager>();
	private TaskDispatcher taskDispatcher;

	public MyBackupService() {
		managers.add(new ConfigManager());
		managers.add(new ScheduleManager());
		managers.add(new HandlerMappingManager());

		taskDispatcher = new TaskDispatcher();

		init();
	}

	private void init() {
		processJSONConfigs();
	}

	public void simpleBackup() {
		taskDispatcher.simpleTask(managers);
	}

	public void scheduledBackup() {
		taskDispatcher.scheduledTask(managers);
	}

	private void processJSONConfigs() {
		managers.forEach(manager -> {
			manager.processJsonConfig();
		});
	}

	/*
	 * getter
	 */
	public List<JsonManager> getManagers() {
		return managers;
	}
}
