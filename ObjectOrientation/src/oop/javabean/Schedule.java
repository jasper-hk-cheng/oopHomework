package oop.javabean;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.json.simple.JSONObject;

public class Schedule {

	private JSONObject schedule;
	private Timestamp time;

	private static final DateFormat dateFormat = new SimpleDateFormat("HH:mm");
	private static final DateFormat dateFormatHour = new SimpleDateFormat("HH");
	private static final DateFormat dateFormatMinute = new SimpleDateFormat("mm");

	/**
	 * constructor the unit of interval is minute...(i guess so...)
	 */
	public Schedule(JSONObject scheduleJsonObject) {
		/*
		 * parse the timelike string as timestamp object
		 */
		try {
			String strTime = (String) scheduleJsonObject.get("time");
			scheduleJsonObject.remove("time");

			this.time = new Timestamp(dateFormat.parse(strTime).getTime());
			this.schedule = scheduleJsonObject;

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public int getHour() {
		return Integer.parseInt(dateFormatHour.format(time));
	}
	
	public int getMinute() {
		return Integer.parseInt(dateFormatMinute.format(time));
	}

	/*
	 * getter
	 */
	public String getExt() {
		return (String) schedule.get("ext");
	}

	public Timestamp getTime() {
		return time;
	}

	public String getInterval() {
		return (String) schedule.get("interval");
	}

	/**
	 * override toString method
	 */
	@Override
	public String toString() {
		JSONObject scheduleToShow = new JSONObject(this.schedule);
		scheduleToShow.put("time", dateFormat.format(this.time));
		return scheduleToShow.toJSONString();
	}
}
