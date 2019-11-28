package edu.upenn.cit594.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import edu.upenn.cit594.data.UserInputArguments;
import edu.upenn.cit594.processor.ArgumentValidatorProcessor;

public class CommandLineUserInterface {

	public UserInputArguments readRuntimeArgs(String[] args) {
		
		ArgumentValidatorProcessor validator = new ArgumentValidatorProcessor();
		
		if(!validator.isNumberOfArgumentsValid(args)) {
			System.err.println("Error: The number of runtime arguments is incorrect.");
			System.exit(1);
		}
		
		String parking_violation_input_file_format = args[0];
		String parking_violation_input_file_name = args[1];
		String property_values_input_file_name = args[2];
		String population_input_file_name = args[3];
		String log_file_name = args[4];
		
		if(!validator.isParkingViolationInputFileFormatValid(parking_violation_input_file_format)) {
			System.err.println("Error: The parking violation input file format should be either \"csv\" or \"json\".");
			System.exit(1);
		}
		
		if(!validator.isFileExistAndReadable(parking_violation_input_file_name)) {
			System.err.println("Error: The parking violation input file is not exist or not readable.");
			System.exit(1);
		}
		
		if(!validator.isFileExistAndReadable(property_values_input_file_name)) {
			System.err.println("Error: The property values input file is not exist or not readable.");
			System.exit(1);
		}
		
		if(!validator.isFileExistAndReadable(population_input_file_name)) {
			System.err.println("Error: The population input file is not exist or not readable.");
			System.exit(1);
		}
		
		UserInputArguments uia = new UserInputArguments();
		uia.setParking_violation_input_file_format(parking_violation_input_file_format);
		uia.setParking_violation_input_file_name(parking_violation_input_file_name);
		uia.setProperty_values_input_file_name(property_values_input_file_name);
		uia.setPopulation_input_file_name(population_input_file_name);
		uia.setLog_file_name(log_file_name);
		
		return uia;
	}
	
	private Scanner scanner;
	
	private Scanner getScanner() {
		if(scanner == null) {
			scanner = new Scanner(System.in);
		}
		return scanner;
	}

	public int getUserInput() {
		System.out.println();
		System.out.println("Please enter a number from 0 to 6 to perform the following action:");
		System.out.println("0: Exit.");
		System.out.println("1: show the total population for all ZIP Codes.");
		System.out.println("2: show the total parking fines per capita for each ZIP Code,.");
		System.out.println("3: show the average market value for residences in a specified ZIP Code.");
		System.out.println("4: show the average total livable area for residences in a specified ZIP Code.");
		System.out.println("5: show the total residential market value per capita for a specified ZIP Code.");
		System.out.println("6: the program should show the results of your custom feature.");//TODO need discuss
		System.out.print("Please enter your selection: ");
		String userInput = getScanner().next();
		if(userInput.length() == 1) {
			int userInputInt = -1;
			try {
				userInputInt = Integer.parseInt(userInput);
			} catch(Exception e) {
				userInputInt = -1;
			}
			if(userInputInt >= 0 && userInputInt <= 6) {
				return userInputInt;
			}
		}
		
		System.err.println("Error: You can only enter a number from 0 to 6.");
		System.exit(1);
		return 0;
	}

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

	public String getZipFromUser() {
		System.out.print("Please enter a zip code: ");
		String zip = getScanner().next();
		return zip;
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

}
