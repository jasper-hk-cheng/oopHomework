package oop.javabean;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Config {

	private JSONObject config;

	/*
	 * constructor
	 */
	public Config(JSONObject configJsonObject) {
		this.config = configJsonObject;
	}

	/*
	 * getter
	 */
	public String getExt() {
		return (String) config.get("ext");
	}

	public String getLocation() {
		return (String) config.get("location");
	}

	public Boolean getSubDirectory() {
		return (Boolean) config.get("subDirectory");
	}

	public String getUnit() {
		return (String) config.get("unit");
	}

	public Boolean getRemove() {
		return (Boolean) config.get("remove");
	}

	public JSONArray getHandlers() {
		return (JSONArray) config.get("handlers");
	}

	public String getDestination() {
		return (String) config.get("destination");
	}

	public String getDir() {
		return (String) config.get("dir");
	}

	public String getConnectionString() {
		return (String) config.get("connectionString");
	}

	/**
	 * override toString method
	 */
	@Override
	public String toString() {
		return config.toJSONString();
	}
}
