package edu.upenn.cit594.processor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class CorrelationProcessor {
	
	public double calculateCorrelationCoefficient(Map<Integer, Double> zipFineMap, Map<Integer, Double> zipValueMap) {
		
		/*
		 *  This method calculates the correlation coefficient for total fines per capita
		 *  (total aggregate fines for each zip code / population for each zip code) and
		 *  total residential market value per capita (total aggregate market value for
		 *  each zip code / population for each zip code).
		 */
		
		
		if ((zipFineMap == null) || (zipValueMap == null)) {
			return 0;
		}
		
		ArrayList<Double> finesPerCapita = new ArrayList<Double>();
		ArrayList<Double> valuesPerCapita = new ArrayList<Double>();

		
		
		// get lists of per capita fines and per capita market values for each zip code
		// iterate through each zip code in the zipFineMap keyset
		for (Integer zip : zipFineMap.keySet()) {
			// make sure that the zip code also appears in the zipValueMap keyset
			if (zipValueMap.containsKey(zip)) {
				//debugging
//				System.out.println("zip code: " + zip);
//				System.out.println("Per capita fine: " + zipFineMap.get(zip));
//				System.out.println("Per capita market value: " + zipValueMap.get(zip));
//				System.out.println();
				
				finesPerCapita.add(zipFineMap.get(zip));
				valuesPerCapita.add(zipValueMap.get(zip));
			}
		}
		//debugging
		//System.out.println(finesPerCapita.isEmpty());
		//System.out.println(valuesPerCapita.isEmpty());
		//System.out.println(finesPerCapita.size());
		//System.out.println(valuesPerCapita.size());
		
		int numElements = finesPerCapita.size();
		double meanFinePerCapita = calculateMean(finesPerCapita);
		double meanValuePerCapita = calculateMean(valuesPerCapita);
		double standardDeviationFines = calculateStandardDeviation(finesPerCapita);
		double standardDeviationValues = calculateStandardDeviation(valuesPerCapita);
		double numerator = 0;
		for (Integer zip : zipFineMap.keySet()) {
			if (zipValueMap.containsKey(zip)) {
				double fine = zipFineMap.get(zip);
				double value = zipValueMap.get(zip);
				double diffFine = fine - meanFinePerCapita;
				double diffValue = value - meanValuePerCapita;
				numerator += (diffFine * diffValue);
			}
		}
		double denominator = standardDeviationFines * standardDeviationValues * (numElements - 1);
		if (denominator == 0) {
			return 0;
		}
		
		// debugging
//		System.out.println("Mean fine per capita: " + meanFinePerCapita);
//		System.out.println("SD fine: " + standardDeviationFines);
//		System.out.println();
//		System.out.println("Mean value per capita: " + meanValuePerCapita);
//		System.out.println("SD value: " + standardDeviationValues);
//		System.out.println();
//		System.out.println("Numerator: " + numerator);
//		System.out.println("Denominator: " + denominator);
		
		
		for (Integer zip : zipFineMap.keySet()) {
			if (zipValueMap.containsKey(zip)) {
				double differenceFine = zipFineMap.get(zip) - meanFinePerCapita;
				double differenceValue = zipValueMap.get(zip) - meanValuePerCapita;
				denominator += differenceFine * differenceValue;
			}
		}
		
		return numerator / denominator;
		
	}
	
	public double calculateMean(List<Double> valuesList) {
		// helper function to calculate mean value of a list of doubles
		int numElements = valuesList.size();
		if (numElements == 0) {
			return 0;
		}
		double aggregate = 0;
		for (Double d : valuesList) {
			aggregate += d;
		}
		return aggregate / numElements;
	}
	
	public double calculateStandardDeviation(List<Double> valuesList) {
		// helper function to calculate standard deviation of a list of doubles
		int numElements = valuesList.size() - 1;
		if (numElements <= 0) {
			return 0;
		}
		
		double sumOfSquares = 0;
		double mean = calculateMean(valuesList);
		for (Double d : valuesList) {
			double difference = d - mean;
			sumOfSquares += Math.pow(difference, 2);
		}
		return Math.sqrt(sumOfSquares / numElements);
		
	}

}
