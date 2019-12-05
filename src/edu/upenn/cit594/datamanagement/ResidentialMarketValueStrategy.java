package edu.upenn.cit594.datamanagement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.upenn.cit594.data.Property;

public class ResidentialMarketValueStrategy implements ResidentialStrategy {

	// implementing memoization
	private Map<String, Double> averageValueMap = new HashMap<String, Double>();
	
	@Override
	public double getAverageValue(String zip, List<Property> propertiesList) {
		
		//check if average market value has been calculated for the zipcode
		// if so, return appropriate value from the hashmap
		// else calculate average market value for zipcode
		if (averageValueMap.containsKey(zip)) {
			return averageValueMap.get(zip);
		} else {
			double averageValue = calculateAverageValue(zip, propertiesList);
			averageValueMap.put(zip, averageValue);
			return averageValue;
		}
	}
	
	private double calculateAverageValue(String zip, List<Property> propertiesList) {
		// helper function to calculate average market value of properties for a zipcode
		int residenceCount = 0;
		double totalPrice = 0;
		
		for(Property p : propertiesList) {
			if(zip.equals(p.getZip_code())) {
				try {
					double price = Double.parseDouble(p.getMarket_value());
					totalPrice += price;
					residenceCount++;
				}
				catch(Exception e) {
					continue;
				}
			}
		}
		if(residenceCount > 0 && totalPrice > 0) {
			return totalPrice/residenceCount; 
		} else {
			return 0;
		}
	}

}
