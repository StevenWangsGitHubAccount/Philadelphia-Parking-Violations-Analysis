package edu.upenn.cit594;

import java.io.IOException;

import edu.upenn.cit594.processor.JobProcessor;

public class Main {
	
	public static void main(String[] args) throws IOException {
		
		JobProcessor tc = new JobProcessor();
		tc.processJob(args);
	}
	
}
