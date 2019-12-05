package edu.upenn.cit594.datamanagement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.upenn.cit594.data.Property;

public class ResidentialTotalLivableAreaStrategy implements ResidentialStrategy {
	
	//implementing memoization
	private Map<String, Double> averageLivingAreaMap = new HashMap<String, Double>();
	

	@Override
	public double getAverageValue(String zip, List<Property> propertiesList) {
	
		// determine if average living area has been calculated for a zipcode
		// if so, return the appropriate value from the hashmap
		// else calculate the average living area
		
		if (averageLivingAreaMap.containsKey(zip)) {
			return averageLivingAreaMap.get(zip);
		} else {
			double averageLivingArea = calculateAverageLivingArea(zip, propertiesList);
			averageLivingAreaMap.put(zip, averageLivingArea);
			return averageLivingArea;
		}
		
	}
	
	private double calculateAverageLivingArea(String zip, List<Property> propertiesList) {
		int residenceCount = 0;
		double totalLivableArea = 0;
		
		for(Property p : propertiesList) {
			if(zip.equals(p.getZip_code())) {
				try {
					double livableArea = Double.parseDouble(p.getTotal_livable_area());
					totalLivableArea += livableArea;
					residenceCount++;
				}
				catch(Exception e) {
					continue;
				}
			}
		}
		if(residenceCount > 0 && totalLivableArea > 0) return totalLivableArea/residenceCount; 
		return 0;
	}

}
