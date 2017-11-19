package oop.task.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Consumer;

import oop.javabean.Config;
import oop.javabean.Schedule;
import oop.my.backup.candidate.Candidate;
import oop.task.AbstractTask;

public class ScheduledTask extends AbstractTask {

	private final Consumer<Candidate> consumer = super::broadcastToHandlers;

	private static final Map<String, Integer> weekdays = new HashMap<String, Integer>() {
		{
			put("sunday", Calendar.SUNDAY);
			put("monday", Calendar.MONDAY);

			// the other week is ignored...
		}
	};

	@Override
	public void execute(Config config, Schedule schedule) {
		super.execute(config, schedule);

		Calendar now = Calendar.getInstance();

		Calendar startTime = Calendar.getInstance();
		startTime.set(Calendar.HOUR_OF_DAY, schedule.getHour());
		startTime.set(Calendar.MINUTE, schedule.getMinute());

		String interval = schedule.getInterval();
		long period = 0;
		if ("everyday".equalsIgnoreCase(interval)) {
			period = 24 * 60 * 60 * 1000L;
		} else if (weekdays.containsKey(interval)) {
			startTime.set(Calendar.DAY_OF_WEEK, weekdays.get(interval));
			period = 7 * 24 * 60 * 60 * 1000L;
		}

		if (now.after(startTime)) { // not reached yet
			if ("everyday".equalsIgnoreCase(interval)) {
				startTime.add(Calendar.DATE, 1);

			} else if (weekdays.containsKey(interval)) {
				startTime.add(Calendar.DAY_OF_WEEK, 1);
			}
		}
		
		System.out.println("schedule process completed !!");
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				System.out.println("start backup...");
				fileFinder.forEach(consumer);
				timer.cancel();
			}
		}, startTime.getTime(), period);
	}
}
