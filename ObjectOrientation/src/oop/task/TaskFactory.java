package oop.task;

import oop.task.impl.ScheduledTask;
import oop.task.impl.SimpleTask;

public class TaskFactory {

	public static Task create(String task) {
		if ("simple".equals(task)) {
			return new SimpleTask();
		} else if ("scheduled".equals(task)) {
			return new ScheduledTask();
		} else {
			return null;
		}
	}
}
