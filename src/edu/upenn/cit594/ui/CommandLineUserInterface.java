package edu.upenn.cit594.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class CommandLineUserInterface {

	public void printTotalPopulationForAllZip(long totalPopulationForAllZip) {
		System.out.println(totalPopulationForAllZip);
	}

	public void printFineForEachZip(Map<Integer, Double> zip_fine_map) {
		
		List<Integer> keyList = new ArrayList<>();
		keyList.addAll(zip_fine_map.keySet());
		Collections.sort(keyList);
		for(Integer zip : keyList) {
			String fine = zip_fine_map.get(zip).toString();
			fine = fine.substring(0, fine.indexOf(".") + 5);
			System.out.println(zip + " " + fine);
		}
		
	}

	public void printAverageResidentialMarketValue(
			double averageResidentialMarketValue) {
		System.out.println(truncToIntegerString(averageResidentialMarketValue));
	}

	private String truncToIntegerString(double averageResidentialMarketValue) {
		String value = String.valueOf(averageResidentialMarketValue);
		int dotIndex = value.indexOf(".");
		if(dotIndex != -1) {
			value = value.substring(0, dotIndex);
		}
		return value;
	}

	public void printAverageResidentialTotalLivableArea(double averageResidentialTotalLivableArea) {
		System.out.println(truncToIntegerString(averageResidentialTotalLivableArea));
	}

	public void printTotalResidentialMarketValuePerCapita(double totalResidentialMarketValuePerCapita) {
		System.out.println(truncToIntegerString(totalResidentialMarketValuePerCapita));
	}
	
	public void printCorrelationCoefficient(double correlationCoefficient) {
		System.out.printf("The correlation coefficient for "
				+ "total fines per capita and total residential"
				+ " market value per capita is %.4f", correlationCoefficient);
	}

}
