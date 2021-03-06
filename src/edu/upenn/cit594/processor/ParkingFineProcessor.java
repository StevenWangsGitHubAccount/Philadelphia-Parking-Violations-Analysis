package edu.upenn.cit594.processor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.upenn.cit594.data.Fine;
import edu.upenn.cit594.datamanagement.CsvFineDataManager;
import edu.upenn.cit594.datamanagement.FineDataManagerInterface;
import edu.upenn.cit594.datamanagement.JsonFineDataManager;
import edu.upenn.cit594.datamanagement.PopulationDataManager;
import edu.upenn.cit594.logging.Logger;

public class ParkingFineProcessor {
	
	// implementing memoization
	private Map<String, Map<Integer, Double>> fineMap = new HashMap<String, Map<Integer, Double>>();
	

	public Map<Integer, Double> getZipFineMap(Logger _log, 
			String parking_violation_input_file_format,
			String parking_violation_input_file_name, 
			String population_input_file_name) {
		
		// if a map of zipcodes to total fines per capita exists for the input file, return it
		// else construct the map
		if (fineMap.containsKey(parking_violation_input_file_name)) {
			return fineMap.get(parking_violation_input_file_name);
		} else {
			Map<Integer, Double> zipFineMap = constructZipFineMap(_log, parking_violation_input_file_format, 
					parking_violation_input_file_name, population_input_file_name);
			fineMap.put(parking_violation_input_file_name, zipFineMap);
			return zipFineMap;
		}
	}
	
	private Map<Integer, Double> constructZipFineMap(Logger _log, 
			String parking_violation_input_file_format,
			String parking_violation_input_file_name, 
			String population_input_file_name) {
		// helper function to construct a map of zip codes to total fines per capita
		FineDataManagerInterface fdm = null;
		
		if("csv".equals(parking_violation_input_file_format)) {
			fdm = new CsvFineDataManager();
		}
		else if("json".equals(parking_violation_input_file_format)) {
			fdm = new JsonFineDataManager();
		}
		
		List<Fine> fineList =  fdm.getFineList(_log, parking_violation_input_file_name);
		
		Map<Integer, Double> zipFineMap = processFineList(fineList);
		
		zipFineMap = calculateFinePerCapita(_log, population_input_file_name, zipFineMap);

		return zipFineMap;
	}

	private Map<Integer, Double> calculateFinePerCapita(Logger _log, String population_input_file_name, Map<Integer, Double> zipFineMap) {

		PopulationDataManager pdm = new PopulationDataManager();
		Map<Integer, Long> zipPopulationMap = pdm.getZipPopulationMap(_log, population_input_file_name);
		zipFineMap.entrySet().removeIf(e -> (e.getValue() == 0 || !zipPopulationMap.containsKey(e.getKey()) || zipPopulationMap.get(e.getKey()) == 0));
		zipFineMap.entrySet().forEach(e -> e.setValue(e.getValue() / zipPopulationMap.get(e.getKey())));
		return zipFineMap;
	}

	private Map<Integer, Double> processFineList(List<Fine> fineList) {
		
		Map<Integer, Double> zipFineMap = new HashMap<Integer, Double>();
		fineList.forEach(f -> {
			String zipString = f.getZip_code();
			int zip = Integer.parseInt(zipString);
			Double fine = f.getFine();
			if(zipFineMap.containsKey(zip)) {
				zipFineMap.put(zip, zipFineMap.get(zip) + fine);
			}
			else {
				zipFineMap.put(zip, fine);
			}
		});
		
		return zipFineMap;
	}
	
	

}
