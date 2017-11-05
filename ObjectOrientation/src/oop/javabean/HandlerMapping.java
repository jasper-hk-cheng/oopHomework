package oop.javabean;

import org.json.simple.JSONObject;

public class HandlerMapping {

	private static JSONObject handlerMapping;

	public HandlerMapping(JSONObject handlerMappingJsonObject) {
		handlerMapping = handlerMappingJsonObject;
	}

	public static String getHandlerNameWith(String handlerMappingKey) {
		return (String) handlerMapping.get(handlerMappingKey);
	}

	/*
	 * getter
	 */
	public JSONObject getHandlerMapping() {
		return handlerMapping;
	}

	@Override
	public String toString() {
		return handlerMapping.toJSONString();
	}
}
