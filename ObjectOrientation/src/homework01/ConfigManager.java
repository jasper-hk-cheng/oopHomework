package homework01;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import homework02.JsonManager;

public class ConfigManager implements JsonManager {

	private static final String PATH = "src\\homework01\\config.json";

	private List<Config> configs = new ArrayList<Config>();

	private Integer count;

	@Override
	public void processJsonConfig() {
		configs.clear();

		try {
			JSONArray configsJsonArray = (JSONArray) getJsonObject(PATH).get("configs");
			for (int i = 0; i < configsJsonArray.size(); i++) {
				JSONObject configJsonObj = (JSONObject) configsJsonArray.get(i);

				String ext = (String) configJsonObj.get("ext");
				String location = (String) configJsonObj.get("location");
				Boolean subDirectory = (Boolean) configJsonObj.get("subDirectory");
				String unit = (String) configJsonObj.get("unit");
				Boolean remove = (Boolean) configJsonObj.get("remove");
				String handler = (String) configJsonObj.get("handler");
				String dir = (String) configJsonObj.get("dir");
				String connectionString = (String) configJsonObj.get("connectionString");

				configs.add(new Config(ext, location, subDirectory, unit, remove, handler, dir, connectionString));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Integer getCount() {
		if (count == null) {
			count = configs.size();
		}
		return count;
	}

	@Override
	public void printListContent() {
		configs.forEach(config -> System.out.println(config));
	}
}
