package edu.upenn.cit594.processor;

import java.util.HashMap;
import java.util.Map;

import edu.upenn.cit594.datamanagement.PopulationDataManager;
import edu.upenn.cit594.logging.Logger;

public class PopulationProcessor {
	
	// implementing memoization
	Map<String, Long> totalPopMap = new HashMap<String, Long>();

	public long getTotalPopulationForAllZip(Logger _log, String population_input_file_name) {
		
		// if total population for all zipcodes has been calculated for this file,
		// return the appropriate value from the hashmap
		// else calculate total population for all zipcodes
		if (totalPopMap.containsKey(population_input_file_name)) {
			return totalPopMap.get(population_input_file_name);
		} else {
			long totalPop = calculateTotalPopulationForAllZip(_log, population_input_file_name);
			totalPopMap.put(population_input_file_name, totalPop);
			return totalPop;
		}
	}
	
	private long calculateTotalPopulationForAllZip(Logger _log, String population_input_file_name) {
		// helper function to calculate total population for all zip codes
		PopulationDataManager pdm = new PopulationDataManager(); 
		Map<Integer, Long> zipPopulationMap = pdm.getZipPopulationMap(_log, population_input_file_name);
		long total = 0;
		for(long population : zipPopulationMap.values()) {
			total += population;
		}
		return total;
	}

}
