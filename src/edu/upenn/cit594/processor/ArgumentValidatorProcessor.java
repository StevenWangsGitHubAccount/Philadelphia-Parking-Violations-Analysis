package edu.upenn.cit594.processor;

import java.io.File;
import java.nio.file.Files;

public class ArgumentValidatorProcessor {
	
	public boolean isNumberOfArgumentsValid(String[] args) {
		return args != null && args.length == 5;
	}
	
	public boolean isParkingViolationInputFileFormatValid(String tweets_input_file_format) {
		return tweets_input_file_format.equals("csv") 
				|| tweets_input_file_format.equals("json");
	}
	
	public boolean isFileExistAndReadable(String filename) {
		return isFileExist(filename) && isFileReadable(filename);
	}
	
	public boolean isFileExist(String filename) {
		File tempFile = new File(filename);
		return tempFile.exists();
	}
	
	private boolean isFileReadable(String filename) {
		File tempFile = new File(filename);
		return Files.isReadable(tempFile.toPath());
	}

}
