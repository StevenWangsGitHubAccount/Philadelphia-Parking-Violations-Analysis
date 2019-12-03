package edu.upenn.cit594.datamanagement;

import java.util.List;

import edu.upenn.cit594.data.Property;

public class ResidentialMarketValueStrategy implements ResidentialStrategy {

	@Override
	public double getAverageValue(String zip, List<Property> propertiesList) {
		
		int residentceCount = 0;
		double totalPrice = 0;
		
		for(Property p : propertiesList) {
			if(zip.equals(p.getZip_code())) {
				try {
					double price = Double.parseDouble(p.getMarket_value());
					totalPrice += price;
					residentceCount++;
					// debugging
//					System.out.println("Price: " + price);
//					System.out.println("Residence count: " + residentceCount);
//					System.out.println();
				}
				catch(Exception e) {
					continue;
				}
			}
		}
		if(residentceCount > 0 && totalPrice > 0) {
			return totalPrice/residentceCount; 
		} else {
			return 0;
		}
	}

}
