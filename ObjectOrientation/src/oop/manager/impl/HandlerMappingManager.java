package oop.manager.impl;

import oop.javabean.HandlerMapping;
import oop.manager.JsonManager;

public class HandlerMappingManager implements JsonManager {

	private static final String PATH = "src\\oop\\json\\config\\handlerMapping.json";

	private HandlerMapping handlerMapping;

	private Integer count;

	@Override
	public void processJsonConfig() {
		this.handlerMapping = new HandlerMapping(getJsonObject(PATH));
	}

	@Override
	public Integer getCount() {
		if (count == null) {
			count = handlerMapping.getHandlerMapping().size();
		}
		return count;
	}

	@Override
	public void printListContent() {
		System.out.println(handlerMapping.getHandlerMapping().toJSONString());
	}
}
