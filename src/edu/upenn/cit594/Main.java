package edu.upenn.cit594;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

import edu.upenn.cit594.data.UserInputArguments;
import edu.upenn.cit594.datamanagement.ResidentialMarketValueStrategy;
import edu.upenn.cit594.datamanagement.ResidentialTotalLivableAreaStrategy;
import edu.upenn.cit594.logging.Logger;
import edu.upenn.cit594.processor.CorrelationProcessor;
import edu.upenn.cit594.processor.ParkingFineProcessor;
import edu.upenn.cit594.processor.PopulationProcessor;
import edu.upenn.cit594.processor.PropertiesProcessor;
import edu.upenn.cit594.ui.CommandLineUserInterface;

public class Main {
	
	public static void main(String[] args) throws IOException {
		
		CommandLineUserInterface ui = new CommandLineUserInterface();
		
		UserInputArguments userArgs = readRuntimeArgs(args);
		
		Logger _log = Logger.getInstance(userArgs.getLog_file_name());
		
		_log.log(System.currentTimeMillis() + " " + userArgs.getParking_violation_input_file_format() + " " + userArgs.getParking_violation_input_file_name() + " " + userArgs.getProperty_values_input_file_name() + " " + userArgs.getPopulation_input_file_name() + " " + userArgs.getLog_file_name());
		
		while(true) {
			int userInput = ui.getUserInput(_log);
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
					String zip = ui.getZipFromUser();
					_log.log(System.currentTimeMillis() + " user entered zip: " + zip);
					PropertiesProcessor pp = new PropertiesProcessor();
					double averageResidentialMarketValue = pp.getAverage(_log, zip, userArgs.getProperty_values_input_file_name(), new ResidentialMarketValueStrategy());
					ui.printAverageResidentialMarketValue(averageResidentialMarketValue);
					break;
				}
				case 4: {
					String zip = ui.getZipFromUser();
					_log.log(System.currentTimeMillis() + " user entered zip: " + zip);
					PropertiesProcessor pp = new PropertiesProcessor();
					double averageResidentialTotalLivableArea = pp.getAverage(_log, zip, userArgs.getProperty_values_input_file_name(), new ResidentialTotalLivableAreaStrategy());
					ui.printAverageResidentialTotalLivableArea(averageResidentialTotalLivableArea);
					break;
				}
				case 5: {
					String zip = ui.getZipFromUser();
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
	
	public static UserInputArguments readRuntimeArgs(String[] args) {
		
		if(!isNumberOfArgumentsValid(args)) {
			System.err.println("Error: The number of runtime arguments is incorrect.");
			System.exit(1);
		}
		
		String parking_violation_input_file_format = args[0];
		String parking_violation_input_file_name = args[1];
		String property_values_input_file_name = args[2];
		String population_input_file_name = args[3];
		String log_file_name = args[4];
		
		if(!isParkingViolationInputFileFormatValid(parking_violation_input_file_format)) {
			System.err.println("Error: The parking violation input file format should be either \"csv\" or \"json\".");
			System.exit(1);
		}
		
		if(!isFileExistAndReadable(parking_violation_input_file_name)) {
			System.err.println("Error: The parking violation input file is not exist or not readable.");
			System.exit(1);
		}
		
		if(!isFileExistAndReadable(property_values_input_file_name)) {
			System.err.println("Error: The property values input file is not exist or not readable.");
			System.exit(1);
		}
		
		if(!isFileExistAndReadable(population_input_file_name)) {
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
	
	public static boolean isNumberOfArgumentsValid(String[] args) {
		return args != null && args.length == 5;
	}
	
	public static boolean isParkingViolationInputFileFormatValid(String tweets_input_file_format) {
		return tweets_input_file_format.equals("csv") 
				|| tweets_input_file_format.equals("json");
	}
	
	public static boolean isFileExistAndReadable(String filename) {
		return isFileExist(filename) && isFileReadable(filename);
	}
	
	public static boolean isFileExist(String filename) {
		File tempFile = new File(filename);
		return tempFile.exists();
	}
	
	private static boolean isFileReadable(String filename) {
		File tempFile = new File(filename);
		return Files.isReadable(tempFile.toPath());
	}
	
}
