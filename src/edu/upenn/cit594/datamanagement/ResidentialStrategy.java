package edu.upenn.cit594.datamanagement;

import java.util.List;

import edu.upenn.cit594.data.Property;

public interface ResidentialStrategy {
	
	public double getAverageValue(String zip, List<Property> propertiesList);

}
