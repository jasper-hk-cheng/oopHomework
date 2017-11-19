package oop.task;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import oop.manager.JsonManager;
import oop.manager.impl.ConfigManager;
import oop.manager.impl.ScheduleManager;

public class TaskDispatcher {

	private Task task;

	public void simpleTask(List<JsonManager> managers) {
		/*
		 * getting the configuration object for testing the newly code
		 */
		List<JsonManager> managerList = managers.stream().filter(manager -> manager instanceof ConfigManager).collect(Collectors.toList());
		if (managerList != null && managerList.size() == 1) {
			ConfigManager configManager = (ConfigManager) managerList.get(0);

			configManager.getConfigs().forEach(config -> {
				task = TaskFactory.create("simple");
				task.execute(config, null);
			});
		}
	}

	public void scheduledTask(List<JsonManager> managers) {
		/*
		 * getting the configuration and schedule manager respectively
		 */
		Function<JsonManager, String> getKey = jsonManager -> jsonManager.getClass().getSimpleName();
		Function<JsonManager, JsonManager> getValue = jsonManager -> jsonManager;
		Map<String, JsonManager> managersMap = managers.stream().collect(Collectors.toMap(getKey, getValue));
		ConfigManager configManager = (ConfigManager) managersMap.get("ConfigManager");
		ScheduleManager scheduleManager = (ScheduleManager) managersMap.get("ScheduleManager");

		scheduleManager.getSchedules().forEach(schedule -> {
			configManager.getConfigs().forEach(config -> {
				if (schedule.getExt().equalsIgnoreCase(config.getExt())) {
					task = TaskFactory.create("scheduled");
					task.execute(config, schedule);
				}
			});
		});
	}
}
