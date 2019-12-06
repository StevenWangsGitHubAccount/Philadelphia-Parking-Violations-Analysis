package edu.upenn.cit594.processor;

import edu.upenn.cit594.data.UserInputArguments;

public class ArgumentProcessor {

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

}
