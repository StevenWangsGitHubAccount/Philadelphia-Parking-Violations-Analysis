package edu.upenn.cit594.processor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.upenn.cit594.data.Property;
import edu.upenn.cit594.datamanagement.PopulationDataManager;
import edu.upenn.cit594.datamanagement.ResidentialStrategy;
import edu.upenn.cit594.logging.Logger;

public class PropertiesProcessor {
	
	//Cache the result, file is large, save List for reuse
	private static List<Property> propertiesList;
	// implementing memoization
	private Map<String, Double> zipTotalMarketValueMap = new HashMap<String, Double>();
	
	private List<Property> getPropertiesList(Logger _log, String fileName) {
		if(propertiesList == null || propertiesList.isEmpty()) {
			PropertiesDataManager pdm = new PropertiesDataManager();
			propertiesList = pdm.getPropertiesList(_log, fileName);
		}
		return propertiesList;
	}

	public double getAverage(Logger _log, String zip, String fileName,
			ResidentialStrategy residentialStrategy) {
		if(!zip.matches("^\\d{5}$")) {
			return 0;
		}
		return residentialStrategy.getAverageValue(zip, getPropertiesList(_log, fileName));
	}

	public double getTotalResidentialMarketValuePerCapita(Logger _log, String zip, String property_values_input_file_name,
			String population_input_file_name) {
		
		if(!zip.matches("^\\d{5}$")) {
			return 0;
		}
		
		// if total residential market value per capita has been calculated for 
		// the zip code, return the appropriate value from the hashmap
		// else, calculate it
		
		if (zipTotalMarketValueMap.containsKey(zip)) {
			return zipTotalMarketValueMap.get(zip);
		} else {
			double totalMarketValuePerCapita = calculateTotalMarketValuePerCapita(_log, zip, 
					property_values_input_file_name, population_input_file_name);
			zipTotalMarketValueMap.put(zip, totalMarketValuePerCapita);
			return totalMarketValuePerCapita;
		}
		
		
	}
	
	private double calculateTotalMarketValuePerCapita(Logger _log, String zip, String property_values_input_file_name, 
			String population_input_file_name) {
		// helper function to calculate the total market value per capita for a zip code
		PopulationDataManager pdm = new PopulationDataManager();
		Map<Integer, Long> zipPopulationMap = pdm.getZipPopulationMap(_log, population_input_file_name);
		double population = 0;
		try {
			Integer zipInt = Integer.parseInt(zip);
			if(!zipPopulationMap.containsKey(zipInt)) {
				return 0;
			}
			population = zipPopulationMap.get(zipInt);
		}
		catch(Exception e) {
			return 0;
		}
		
		double totalResidentialmarketValueInZip = 0;
		
		for(Property p : getPropertiesList(_log, property_values_input_file_name)) {
			if(zip.equals(p.getZip_code())) {
				try {
					double marketValue = Double.parseDouble(p.getMarket_value());
					totalResidentialmarketValueInZip += marketValue;
				}
				catch(Exception e) {
					continue;
				}
			}
		}
		
		if(totalResidentialmarketValueInZip > 0 && population > 0) {
			return totalResidentialmarketValueInZip / population;
		}
		return 0;
	}
	
	public Map<Integer, Double> getZipMarketValuePerCapitaMap (Logger _log, String property_values_input_file_name,
			String population_input_file_name){
		
		// This function returns a map of zip codes to total market values per capita.
		// The map will be used to calculate the correlation coefficient (choice 6).
		
		HashMap<Integer, Double> zipValueMap = new HashMap<Integer, Double>();
		
		
		PopulationDataManager pdm = new PopulationDataManager();
		Map<Integer, Long> zipPopulationMap = pdm.getZipPopulationMap(_log, population_input_file_name);
		
		//iterate through each zip code
		// retrieve zip code's market value per capita
		// put each zip code and market value per capita in hashmap
		for (int zip : zipPopulationMap.keySet()) {
			String zipString = Integer.toString(zip);
			double marketValuePerCapita = getTotalResidentialMarketValuePerCapita(_log, zipString, property_values_input_file_name,
					population_input_file_name);
			zipValueMap.put(zip, marketValuePerCapita);
		}
		
		return zipValueMap;
	}

}
