package oop.task;

import oop.javabean.Config;
import oop.javabean.Schedule;

public interface Task {

	void execute(Config config, Schedule schedule);
}
