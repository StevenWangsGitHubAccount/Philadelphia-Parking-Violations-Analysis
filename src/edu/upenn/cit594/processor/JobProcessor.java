package edu.upenn.cit594.processor;

import java.util.Map;

import edu.upenn.cit594.data.UserInputArguments;
import edu.upenn.cit594.datamanagement.ResidentialMarketValueStrategy;
import edu.upenn.cit594.datamanagement.ResidentialTotalLivableAreaStrategy;
import edu.upenn.cit594.logging.Logger;
import edu.upenn.cit594.ui.CommandLineUserInterface;

public class JobProcessor {

	public void processJob(String[] args) {
		
		CommandLineUserInterface ui = new CommandLineUserInterface();
		UserInputArguments userArgs = ui.readRuntimeArgs(args);
		
		Logger _log = Logger.getInstance(userArgs.getLog_file_name());
		
		_log.log(System.currentTimeMillis() + " " + userArgs.getParking_violation_input_file_format() + " " + userArgs.getParking_violation_input_file_name() + " " + userArgs.getProperty_values_input_file_name() + " " + userArgs.getPopulation_input_file_name() + " " + userArgs.getLog_file_name());
		
		while(true) {
			int userInput = ui.getUserInput();
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
					System.out.println("case 6");//TODO
					break;
				}
			}
		}
		
	}

}
