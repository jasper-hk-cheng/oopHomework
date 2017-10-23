package homework01;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ScheduleManager {

	private List<Schedule> schedules = new ArrayList<Schedule>();

	private Integer count;

	public List<Schedule> processSchedules() {
		schedules.clear();

		try {
			FileReader fileReader = new FileReader(new File("src\\homework01\\schedule.json"));
			JSONParser jsonParser = new JSONParser();
			JSONObject wholeJson = (JSONObject) jsonParser.parse(fileReader);

			JSONArray schedulesJsonArray = (JSONArray) wholeJson.get("schedules");
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
		return schedules;
	}

	public Integer getCount() {
		if (count == null) {
			count = schedules.size();
		}
		return count;
	}
}
