package edu.upenn.cit594.processor;

import java.util.List;

import edu.upenn.cit594.logging.Logger;

public class LogProcessor {

	public void writeLog(String log_file_name, List<String> fluTweetList) {
		Logger logger = Logger.getInstance(log_file_name);
		for(String msg : fluTweetList) {
			logger.log(msg);
		}
	}
	
}
