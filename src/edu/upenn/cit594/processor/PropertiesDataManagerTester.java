package edu.upenn.cit594.processor;

import java.util.ArrayList;

import edu.upenn.cit594.data.Property;
import edu.upenn.cit594.logging.Logger;

public class PropertiesDataManagerTester {

	public static void main(String[] args) {
		PropertiesDataManager pdm = new PropertiesDataManager();
		Logger l = Logger.getInstance("log.txt");
		String fileName = "properties.csv";
		
		ArrayList<Property> pList = (ArrayList<Property>) pdm.getPropertiesList(l, fileName);
		System.out.println(pList.isEmpty());
		
		for (int i = 0; i < 10; i++) {
			System.out.println(pList.get(i).getZip_code());
			System.out.println(pList.get(i).getMarket_value());
			System.out.println(pList.get(i).getTotal_livable_area());
			System.out.println();
		}
	}
}
