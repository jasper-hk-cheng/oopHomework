package oop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import oop.file.finder.FileFinder;
import oop.file.finder.FileFinderFactory;
import oop.handler.Handler;
import oop.handler.HandlerFactory;
import oop.javabean.Candidate;
import oop.javabean.Config;
import oop.manager.JsonManager;
import oop.manager.impl.ConfigManager;
import oop.manager.impl.HandlerMappingManager;
import oop.manager.impl.ScheduleManager;

public class MyBackupService {

	private List<JsonManager> managers = new ArrayList<JsonManager>();

	public MyBackupService() {
		managers.add(new ConfigManager());
		managers.add(new ScheduleManager());
		managers.add(new HandlerMappingManager());
	}

	public void processJSONConfig() {
		managers.forEach(manager -> {
			manager.processJsonConfig();
		});
	}

	public void doBackUp() {

		/*
		 * getting the configuration object for testing the newly code
		 */
		List<JsonManager> managerList = managers.stream().filter(manager -> manager instanceof ConfigManager).collect(Collectors.toList());
		if (managerList != null && managerList.size() == 1) {
			ConfigManager configManager = (ConfigManager) managerList.get(0);

			configManager.getConfigs().forEach(config -> {
				FileFinder<Candidate> fileFinder = FileFinderFactory.create("file", config);

				fileFinder.forEach(this::broadcastToHandlers);
			});
		}
	}

	/**
	 * execute the handle method respectively
	 * 
	 * @param candidate
	 */
	private void broadcastToHandlers(Candidate candidate) {
		List<Handler> handlers = findHandlers(candidate);

		byte[] target = null;
		for (Handler handler : handlers) {
			target = handler.perform(candidate, target);
		}
	}

	/**
	 * find all the handlers of this candidate
	 * 
	 * @param candidate
	 * @return
	 */
	private List<Handler> findHandlers(Candidate candidate) {
		List<Handler> handlers = new ArrayList<Handler>();
		Config config = candidate.getConfig();

		handlers.add(HandlerFactory.create(config.getUnit()));

		config.getHandlers().forEach(handler -> {
			String strHandler = (String) handler;
			handlers.add(HandlerFactory.create(strHandler));
		});

		handlers.add(HandlerFactory.create(config.getDestination()));

		return handlers;
	}

	/*
	 * getter
	 */
	public List<JsonManager> getManagers() {
		return managers;
	}
}
