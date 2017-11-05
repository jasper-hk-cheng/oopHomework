package oop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
		List<Candidate> candidates = findFiles();
		candidates.forEach(this::broadcastToHandlers);
	}

	/**
	 * the scope of homework 4. at present, the method returns some of dummy
	 * candidates...
	 * 
	 * @return dummy candidate list...
	 */
	private List<Candidate> findFiles() {
		// FIXME notice: dummy return here!!
		List<Candidate> candidates = new ArrayList<Candidate>();
		
		List<JsonManager> managerList = managers.stream().filter(jsonManager -> jsonManager instanceof ConfigManager).collect(Collectors.toList());
		if(managerList!=null && managerList.size() == 1) {
			ConfigManager configManager = (ConfigManager)managerList.get(0);
			List<Config> configs = configManager.getConfigs();
			
			configs.forEach(config -> {
				String ext = config.getExt();
				
				switch(ext) {
				case "pdf":
					candidates.add(new Candidate(config, "2017/12/31 23:59:59", "k375", null, null)); 
					break;
				
				case "txt":
					candidates.add(new Candidate(config, "2017/12/31 23:59:59", "cache", null, null)); 
					break;
				
				case "png":
					candidates.add(new Candidate(config, "2017/12/31 23:59:59", "duke", null, null)); 
					break;
					
				default:
					System.out.println("candidates error");
				}
			});
			return candidates;
		}
		return candidates;
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

		// target... to be continue here ??
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
