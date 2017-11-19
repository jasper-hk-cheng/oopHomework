package oop.task.impl;

import oop.javabean.Config;
import oop.javabean.Schedule;
import oop.task.AbstractTask;

public class SimpleTask extends AbstractTask {

	@Override
	public void execute(Config config, Schedule schedule) {
		super.execute(config, schedule);
		
		fileFinder.forEach(this::broadcastToHandlers);
	}
}
