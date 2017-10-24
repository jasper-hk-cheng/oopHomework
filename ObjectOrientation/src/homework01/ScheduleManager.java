package homework01;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import homework02.JsonManager;

public class ScheduleManager implements JsonManager {

	private static final String PATH = "src\\homework01\\schedule.json";

	private List<Schedule> schedules = new ArrayList<Schedule>();

	private Integer count;

	@Override
	public void processJsonConfig() {
		schedules.clear();

		try {
			JSONArray schedulesJsonArray = (JSONArray) getJsonObject(PATH).get("schedules");
			for (int i = 0; i < schedulesJsonArray.size(); i++) {
				JSONObject scheduleJsonObj = (JSONObject) schedulesJsonArray.get(i);

				String ext = (String) scheduleJsonObj.get("ext");
				String time = (String) scheduleJsonObj.get("time");
				String interval = (String) scheduleJsonObj.get("interval");

				schedules.add(new Schedule(ext, time, interval));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Integer getCount() {
		if (count == null) {
			count = schedules.size();
		}
		return count;
	}

	@Override
	public void printListContent() {
		schedules.forEach(schedule -> System.out.println(schedule));
	}
}
