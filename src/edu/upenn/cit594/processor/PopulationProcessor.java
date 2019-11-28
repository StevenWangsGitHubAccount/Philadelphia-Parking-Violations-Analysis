package edu.upenn.cit594.processor;

import java.util.Map;

import edu.upenn.cit594.datamanagement.PopulationDataManager;
import edu.upenn.cit594.logging.Logger;

public class PopulationProcessor {

	public long getTotalPopulationForAllZip(Logger _log, String population_input_file_name) {
		PopulationDataManager pdm = new PopulationDataManager(); 
		Map<Integer, Long> zipPopulationMap = pdm.getZipPopulationMap(_log, population_input_file_name);
		long total = 0;
		for(long population : zipPopulationMap.values()) {
			total += population;
		}
		return total;
	}

}
