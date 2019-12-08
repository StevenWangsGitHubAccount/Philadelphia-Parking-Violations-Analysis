package edu.upenn.cit594.datamanagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import edu.upenn.cit594.logging.Logger;

public class PopulationDataManager {
	
	private static Map<Integer, Long> zipPolulationMap;

	public Map<Integer, Long> getZipPopulationMap(Logger _log, String population_input_file_name) {
		
		if(zipPolulationMap != null) return zipPolulationMap; 
		
		zipPolulationMap = new HashMap<Integer, Long>();
		
		try {
			_log.log(System.currentTimeMillis() + " " + population_input_file_name);
			BufferedReader txtReader = new BufferedReader(new FileReader(new File(population_input_file_name)));
			String row = null;
			while ((row  = txtReader.readLine()) != null) {
			    String[] rowArray = row.split("\\s");
			    String zipString = rowArray[0];
			    String populationString = rowArray[1];
			    
			    int zip = -1;
			    long population = -1;
			    try{
			    	zip = Integer.parseInt(zipString);
			    	population = Long.parseLong(populationString);
			    }
			    catch(Exception e) {
			    	zip = -1;
			    	population = -1;
			    }
			    if(zip >= 0 && population >= 0) {
			    	zipPolulationMap.put(zip, population);
			    }
			    
			}
			txtReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return zipPolulationMap;
	}

}
