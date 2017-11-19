package oop.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import oop.javabean.Schedule;
import oop.manager.JsonManager;

public class ScheduleManager implements JsonManager {

	private static final String PATH = "src\\oop\\json\\config\\schedule.json";

	private List<Schedule> schedules = new ArrayList<Schedule>();

	private Integer count;

	@Override
	public void processJsonConfig() {
		schedules.clear();

		try {
			JSONArray schedulesJsonArray = (JSONArray) getJsonObject(PATH).get("schedules");
			schedulesJsonArray.forEach(schedule -> {
				JSONObject scheduleJsonObject = (JSONObject) schedule;
				schedules.add(new Schedule(scheduleJsonObject));
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void printListContent() {
		schedules.forEach(System.out::println);
	}
	
	/*
	 * getter
	 */
	public List<Schedule> getSchedules() {
		return schedules;
	}
	
	@Override
	public Integer getCount() {
		if (count == null) {
			count = schedules.size();
		}
		return count;
	}
}
