package edu.upenn.cit594.datamanagement;

import java.util.List;

import edu.upenn.cit594.data.Property;

public class ResidentialTotalLivableAreaStrategy implements ResidentialStrategy {

	@Override
	public double getAverageValue(String zip, List<Property> propertiesList) {
		
		int residentceCount = 0;
		double totalLivableArea = 0;
		
		for(Property p : propertiesList) {
			if(zip.equals(p.getZip_code())) {
				try {
					double livableArea = Double.parseDouble(p.getTotal_livable_area());
					totalLivableArea += livableArea;
					residentceCount++;
				}
				catch(Exception e) {
					continue;
				}
			}
		}
		if(residentceCount > 0 && totalLivableArea > 0) return totalLivableArea/residentceCount; 
		return 0;
	}

}
