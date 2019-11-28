package edu.upenn.cit594.logging;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import edu.upenn.cit594.processor.ArgumentValidatorProcessor;

public class Logger {
	
	private String log_file_name;
	
	private Logger() {
	}
	
	private Logger(String log_file_name) {
		this.log_file_name = log_file_name;
		if(!new ArgumentValidatorProcessor().isFileExist(log_file_name)) {
			File logFile = new File(log_file_name);
			try {
				logFile.createNewFile();
			} catch (IOException e) {
				System.err.println("Error: fail to write to log file.");
				System.exit(1);
			}
		}
		else {
			cleanLogFile(log_file_name);
		}
	}
	
	private static Logger _log = null;
	
	public static Logger getInstance(String log_file_name) {
		if(_log == null) {
			_log = new Logger(log_file_name);
		}
		return _log;
	}
	
	private void cleanLogFile(String log_file_name) {
		PrintWriter writer;
		try {
			writer = new PrintWriter(new FileWriter(log_file_name, false));
			writer.print("");
			writer.close();
		} catch (IOException e) {
			System.err.println("Error: fail to clean the old log file.");
			System.exit(1);
		}
	}
	
	public void log(String msg) {
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(log_file_name, true);
		} catch (IOException e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
	    PrintWriter printWriter = new PrintWriter(fileWriter);
	    printWriter.printf("%s\n", msg);
	    printWriter.close();
	}

}
