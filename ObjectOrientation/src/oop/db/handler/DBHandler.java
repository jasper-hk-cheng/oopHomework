package oop.db.handler;

import oop.handler.Handler;

public interface DBHandler extends Handler {

	void openConnection();
	
	void closeConnection();
}
