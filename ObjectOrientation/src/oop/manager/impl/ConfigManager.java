package oop.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import oop.javabean.Config;
import oop.manager.JsonManager;

public class ConfigManager implements JsonManager {

	private static final String PATH = "src\\oop\\json\\config\\config.json";

	private List<Config> configs = new ArrayList<Config>();

	private Integer count;

	@Override
	public void processJsonConfig() {
		configs.clear();

		try {
			JSONArray configsJsonArray = (JSONArray) getJsonObject(PATH).get("configs");
			configsJsonArray.forEach(config -> {
				JSONObject configJsonObject = (JSONObject) config;
				configs.add(new Config(configJsonObject));
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void printListContent() {
		configs.forEach(System.out::println);
	}

	/*
	 * getter
	 */
	//FIXME notice: for simulating MyBackupService.findFiles() only, not really be necessary to create the getter... 
	public List<Config> getConfigs() {
		return configs;
	}

	@Override
	public Integer getCount() {
		if (count == null) {
			count = configs.size();
		}
		return count;
	}
}
