package homework01;

import java.util.List;

public class Tester {

	public static void main(String[] args) {
		
		ConfigManager configManager = new ConfigManager();
		List<Config> configs = configManager.processConfigs();

		for (Config config : configs) {
			System.out.println(config);
		}

		ScheduleManager scheduleManager = new ScheduleManager();
		List<Schedule> schedules = scheduleManager.processSchedules();
		
		for (Schedule schedule : schedules) {
			System.out.println(schedule);
		}
	}
}