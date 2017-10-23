package homework01;

import java.util.HashMap;

import org.json.simple.JSONObject;

public class Config {

	private String ext;
	private String location;
	private Boolean subDirectory;
	private String unit;
	private Boolean remove;
	private String handler;
	private String dir;
	private String connectionString;

	// enumeration for unit
	public static final String UNIT_FILE = "file";
	public static final String UNIT_DIRECTORY = "directory";

	// enumeration for handler
	public static final String ZIP = "zip";
	public static final String ENCODE = "encode";

	// enumeration for destination
	public static final String DESTINATION_DIRECTORY = "directory";
	public static final String DESTINATION_DB = "db";

	/*
	 * constructor
	 */
	public Config(String ext, String location, Boolean subDirectory, String unit, Boolean remove, String handler,
			String dir, String connectionString) {
		this.ext = ext;
		this.location = location;
		this.subDirectory = subDirectory;
		this.unit = unit;
		this.remove = remove;
		this.handler = handler;
		this.dir = dir;
		this.connectionString = connectionString;
	}

	/*
	 * getter
	 */
	public String getExt() {
		return ext;
	}

	public String getLocation() {
		return location;
	}

	public Boolean getSubDirectory() {
		return subDirectory;
	}

	public String getUnit() {
		return unit;
	}

	public Boolean getRemove() {
		return remove;
	}

	public String getHandler() {
		return handler;
	}

	public String getDir() {
		return dir;
	}

	public String getConnectionString() {
		return connectionString;
	}

	/**
	 * override toString method
	 */
	@Override
	public String toString() {
		JSONObject thisConfig = new JSONObject(new HashMap<String, Object>() {
			{
				put("ext", ext);
				put("location", location);
				put("subDirectory", subDirectory);
				put("unit", unit);
				put("remove", remove);
				put("handler", handler);
				put("dir", dir);
				put("connectionString", connectionString);
			}
		});
		return thisConfig.toJSONString();
	}
}
