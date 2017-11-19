package oop.task;

import java.util.ArrayList;
import java.util.List;

import oop.file.finder.FileFinder;
import oop.file.finder.FileFinderFactory;
import oop.handler.Handler;
import oop.handler.HandlerFactory;
import oop.javabean.Config;
import oop.javabean.Schedule;
import oop.my.backup.candidate.Candidate;

public abstract class AbstractTask implements Task{

	protected FileFinder<Candidate> fileFinder;
	
	@Override
	public void execute(Config config, Schedule schedule) {
		fileFinder = FileFinderFactory.create("file", config);
	}
	
	/**
	 * execute the handle method respectively
	 * 
	 * @param candidate
	 */
	protected void broadcastToHandlers(Candidate candidate) {
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
}
