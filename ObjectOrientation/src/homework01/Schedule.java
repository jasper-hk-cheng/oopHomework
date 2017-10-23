package homework01;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import org.json.simple.JSONObject;

public class Schedule {

	private String ext;
	private Timestamp time;
	private String interval;

	private static final DateFormat dateFormat = new SimpleDateFormat("HH:mm");

	/**
	 * constructor the unit of interval is minute...(i guess so...)
	 */
	public Schedule(String ext, String time, String interval) {
		try {
			this.ext = ext;
			long longTime = dateFormat.parse(time).getTime();
			this.time = new Timestamp(longTime);
			this.interval = interval;
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * getter
	 */
	public String getExt() {
		return ext;
	}

	public Timestamp getTime() {
		return time;
	}

	public String getInterval() {
		return interval;
	}
	
	/**
	 * override toString method
	 */
	@Override
	public String toString() {
		JSONObject thisSchedule = new JSONObject(new HashMap<String, Object>() {
			{
				put("ext", ext);
				put("time", dateFormat.format(time));
				put("interval", interval);
			}
		});
		return thisSchedule.toJSONString();
	} 
}
