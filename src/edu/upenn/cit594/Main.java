package edu.upenn.cit594;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

import edu.upenn.cit594.data.UserInputArguments;
import edu.upenn.cit594.datamanagement.ResidentialMarketValueStrategy;
import edu.upenn.cit594.datamanagement.ResidentialTotalLivableAreaStrategy;
import edu.upenn.cit594.logging.Logger;
import edu.upenn.cit594.processor.ArgumentProcessor;
import edu.upenn.cit594.processor.CorrelationProcessor;
import edu.upenn.cit594.processor.ParkingFineProcessor;
import edu.upenn.cit594.processor.PopulationProcessor;
import edu.upenn.cit594.processor.PropertiesProcessor;
import edu.upenn.cit594.ui.CommandLineUserInterface;

public class Main {
	
	private static Scanner scanner;
	
	private static Scanner getScanner() {
		if(scanner == null) {
			scanner = new Scanner(System.in);
		}
		return scanner;
	}
	
	public static int getUserInput() {
		System.out.println();
		System.out.println("Please enter a number from 0 to 6 to perform the following action:");
		System.out.println("0: Exit.");
		System.out.println("1: show the total population for all ZIP Codes.");
		System.out.println("2: show the total parking fines per capita for each ZIP Code,.");
		System.out.println("3: show the average market value for residences in a specified ZIP Code.");
		System.out.println("4: show the average total livable area for residences in a specified ZIP Code.");
		System.out.println("5: show the total residential market value per capita for a specified ZIP Code.");
		System.out.println("6: show the correlation coefficient for total parking fines per capita and total residential market value per capita.");
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
	
	public static String getZipFromUser() {
		System.out.print("Please enter a zip code: ");
		String zip = getScanner().next();
		return zip;
	}
	
	public static void main(String[] args) throws IOException {
		
		CommandLineUserInterface ui = new CommandLineUserInterface();
		ArgumentProcessor ap = new ArgumentProcessor();
		
		UserInputArguments userArgs = ap.readRuntimeArgs(args);
		
		Logger _log = Logger.getInstance(userArgs.getLog_file_name());
		
		_log.log(System.currentTimeMillis() + " " + userArgs.getParking_violation_input_file_format() + " " + userArgs.getParking_violation_input_file_name() + " " + userArgs.getProperty_values_input_file_name() + " " + userArgs.getPopulation_input_file_name() + " " + userArgs.getLog_file_name());
		
		while(true) {
			int userInput = getUserInput();
			_log.log(System.currentTimeMillis() + " user selected " + userInput);
			switch(userInput) {
				case 0: {
					System.out.println("User select 0, program exit.");
					System.exit(0);
					break;
				}
				case 1: {
					PopulationProcessor p = new PopulationProcessor();
					long totalPopulationForAllZip = p.getTotalPopulationForAllZip(_log, userArgs.getPopulation_input_file_name()); 
					ui.printTotalPopulationForAllZip(totalPopulationForAllZip);
					break;
				}
				case 2: {
					ParkingFineProcessor pfp = new ParkingFineProcessor();
					Map<Integer, Double> zip_fine_map = pfp.getZipFineMap(_log, userArgs.getParking_violation_input_file_format(), userArgs.getParking_violation_input_file_name(), userArgs.getPopulation_input_file_name());
					ui.printFineForEachZip(zip_fine_map);
					break;
				}
				case 3: {
					String zip = getZipFromUser();
					_log.log(System.currentTimeMillis() + " user entered zip: " + zip);
					PropertiesProcessor pp = new PropertiesProcessor();
					double averageResidentialMarketValue = pp.getAverage(_log, zip, userArgs.getProperty_values_input_file_name(), new ResidentialMarketValueStrategy());
					ui.printAverageResidentialMarketValue(averageResidentialMarketValue);
					break;
				}
				case 4: {
					String zip = getZipFromUser();
					_log.log(System.currentTimeMillis() + " user entered zip: " + zip);
					PropertiesProcessor pp = new PropertiesProcessor();
					double averageResidentialTotalLivableArea = pp.getAverage(_log, zip, userArgs.getProperty_values_input_file_name(), new ResidentialTotalLivableAreaStrategy());
					ui.printAverageResidentialTotalLivableArea(averageResidentialTotalLivableArea);
					break;
				}
				case 5: {
					String zip = getZipFromUser();
					_log.log(System.currentTimeMillis() + " user entered zip: " + zip);
					PropertiesProcessor pp = new PropertiesProcessor();
					double totalResidentialMarketValuePerCapita = pp.getTotalResidentialMarketValuePerCapita(_log, zip, userArgs.getProperty_values_input_file_name(), userArgs.getPopulation_input_file_name());
					ui.printTotalResidentialMarketValuePerCapita(totalResidentialMarketValuePerCapita);
					break;
				}
				case 6: {
					
					// calculate correlation coefficient of total fines per capita and 
					// total residential market value per capita
					//
					// create ParkingFineProcessor object
					// get map of zipcodes to total fines per capita
					ParkingFineProcessor pfp = new ParkingFineProcessor();
					Map<Integer, Double> zip_fine_map = pfp.getZipFineMap(_log, userArgs.getParking_violation_input_file_format(), userArgs.getParking_violation_input_file_name(), userArgs.getPopulation_input_file_name());
					
					// create PropertiesProcessor object
					// get map of zipcodes to total residential market value per capita
					PropertiesProcessor pp = new PropertiesProcessor();
					Map<Integer, Double> zip_value_map = pp.getZipMarketValuePerCapitaMap(_log, userArgs.getProperty_values_input_file_name(), userArgs.getPopulation_input_file_name());
					
					// calculate correlation coefficient
					// print correlation coefficient
					CorrelationProcessor cp = new CorrelationProcessor();
					double correlationCoefficient = cp.calculateCorrelationCoefficient(zip_fine_map, zip_value_map);
					ui.printCorrelationCoefficient(correlationCoefficient);
				}
			}
		}
	}
	
}
