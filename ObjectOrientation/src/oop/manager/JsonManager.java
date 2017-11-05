package oop.manager;

import java.io.File;
import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public interface JsonManager {

	void processJsonConfig();

	Integer getCount();

	default public JSONObject getJsonObject(String path) {
		try {
			FileReader fileReader = new FileReader(new File(path));
			JSONParser jsonParser = new JSONParser();
			return (JSONObject) jsonParser.parse(fileReader);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * this is for checking the result of the execution of method "processJsonConfig"
	 */
	void printListContent();
}
