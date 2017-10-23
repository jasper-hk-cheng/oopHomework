package homework01;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ConfigManager {

	private List<Config> configs = new ArrayList<Config>();

	private Integer count;

	public List<Config> processConfigs() {
		configs.clear();

		try {
			FileReader fileReader = new FileReader(new File("src\\homework01\\config.json"));
			JSONParser jsonParser = new JSONParser();
			JSONObject wholeJson = (JSONObject) jsonParser.parse(fileReader);

			JSONArray configsJsonArray = (JSONArray) wholeJson.get("configs");
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
		return configs;
	}

	public Integer getCount() {
		if (count == null) {
			count = configs.size();
		}
		return count;
	}
}
