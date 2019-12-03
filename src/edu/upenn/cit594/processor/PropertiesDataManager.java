package edu.upenn.cit594.processor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.upenn.cit594.data.Property;
import edu.upenn.cit594.logging.Logger;

public class PropertiesDataManager {
	
	/*
	 * 12/2/19
	 * I made some significant modifications to this class.
	 * The project instructions state that we cannot take for granted 
	 * the order of the 3 relevant fields -- zip code, market value, and total livable area.
	 * Rather, we have to parse the first row of the file and determine which 
	 * columns contain these fields.
	 * 
	 * The original code did not handle situations where the ordering of these fields varied.
	 * The modifications below handle these situations by parsign the first row to 
	 * determine which columns contain the relevant fields.
	 * 
	 * I've also dropped the dozens of other fields that appear in the property data file.
	 * The project requires the use of only the 3 fields listed above.
	 */
	
	public List<Property> getPropertiesList(Logger _log, String fileName) {
		
		List<Property> pList = new ArrayList<Property>();
		boolean firstRow = true;
		int marketValueIndex = 0;
		int livableAreaIndex = 0;
		int zipcodeIndex = 0;
		
		// read in file
		File propertyData = new File(fileName);
		_log.log(System.currentTimeMillis() + " " + fileName);
		try {
			
			Scanner s = new Scanner(propertyData);
			while (s.hasNextLine()) {
				// for first row
				// determine which columns (indices) correspond to which attributes
				if (firstRow) {
					String[] firstLine = s.nextLine().split(",");
					int columnIndex = 0;
					for (String columnName : firstLine) {
						if (columnName.contentEquals("market_value")) {
							marketValueIndex = columnIndex;
						}
						if (columnName.contentEquals("total_livable_area")) {
							livableAreaIndex = columnIndex;
						}
						if (columnName.contentEquals("zip_code")) {
							zipcodeIndex = columnIndex;
						}
						columnIndex++;
					}
					firstRow = false;
				} else {
					// iterate through rest of data
					// if all 3 attributes are valid, construct Property objects and add to arraylist
					String[] data = s.nextLine().split(",");
					String zipcode;
					String marketValue;
					String livableArea;
					boolean allDataValid = true;
					try {
						zipcode = data[zipcodeIndex];
						zipcode = zipcode.substring(0, 5);
					} catch(Exception e) {
						zipcode = "";
						allDataValid = false;
					}
					
					try {
						marketValue = data[marketValueIndex];
					} catch(Exception e) {
						marketValue = "";
						allDataValid = false;
					}
					
					try {
						livableArea = data[livableAreaIndex];
					} catch(Exception e) {
						livableArea = "";
						allDataValid = false;
					}
					
					if (allDataValid) {
						Property prop = new Property(livableArea, zipcode, marketValue);
						pList.add(prop);
					}
					
				}
			}
				
				
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//debugging
//		for (int i = 0; i < 10; i++) {
//			System.out.println(pList.get(i).getZip_code());
//			System.out.println(pList.get(i).getMarket_value());
//			System.out.println(pList.get(i).getTotal_livable_area());
//			System.out.println();
//		}
		
		
		
		
		
//		String row = null;
//		try {
//			_log.log(System.currentTimeMillis() + " " + fileName);
//			BufferedReader csvReader = new BufferedReader(new FileReader(new File(fileName)));
//			csvReader.readLine();//ignore first row.
//			while ((row  = csvReader.readLine()) != null) {
//				String[] values = row.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
//				int i = 0;
//			    String number_of_rooms = values[i++].trim();
//			    String assessment_date = values[i++].trim();
//			    String beginning_point = values[i++].trim();
//			    String book_and_page = values[i++].trim();
//			    String building_code = values[i++].trim();
//			    String building_code_description = values[i++].trim();
//			    String category_code = values[i++].trim();
//			    String category_code_description = values[i++].trim();
//			    String census_tract = values[i++].trim();
//			    String central_air = values[i++].trim();
//			    String cross_reference = values[i++].trim();
//			    String date_exterior_condition = values[i++].trim();
//			    String depth = values[i++].trim();
//			    String exempt_building = values[i++].trim();
//			    String exempt_land = values[i++].trim();
//			    String exterior_condition = values[i++].trim();
//			    String fireplaces = values[i++].trim();
//			    String frontage = values[i++].trim();
//			    String fuel = values[i++].trim();
//			    String garage_spaces = values[i++].trim();
//			    String garage_type = values[i++].trim();
//			    String general_construction = values[i++].trim();
//			    String geographic_ward = values[i++].trim();
//			    String homestead_exemption = values[i++].trim();
//			    String house_extension = values[i++].trim();
//			    String house_number = values[i++].trim();
//			    String interior_condition = values[i++].trim();
//			    String location = values[i++].trim();
//			    String mailing_address_1 = values[i++].trim();
//			    String mailing_address_2 = values[i++].trim();
//			    String mailing_care_of = values[i++].trim();
//			    String mailing_city_state = values[i++].trim();
//			    String mailing_street = values[i++].trim();
//			    String mailing_zip = values[i++].trim();
//			    String market_value = values[i++].trim();
//			    String market_value_date = values[i++].trim();
//			    String number_of_bathrooms = values[i++].trim();
//			    String number_of_bedrooms = values[i++].trim();
//			    String basements = values[i++].trim();
//			    String number_stories = values[i++].trim();
//			    String off_street_open = values[i++].trim();
//			    String other_building = values[i++].trim();
//			    String owner_1 = values[i++].trim();
//			    String owner_2 = values[i++].trim();
//			    String parcel_number = values[i++].trim();
//			    String parcel_shape = values[i++].trim();
//			    String quality_grade = values[i++].trim();
//			    String recording_date = values[i++].trim();
//			    String registry_number = values[i++].trim();
//			    String sale_date = values[i++].trim();
//			    String sale_price = values[i++].trim();
//			    String separate_utilities = values[i++].trim();
//			    String sewer = values[i++].trim();
//			    String site_type = values[i++].trim();
//			    String state_code = values[i++].trim();
//			    String street_code = values[i++].trim();
//			    String street_designation = values[i++].trim();
//			    String street_direction = values[i++].trim();
//			    String street_name = values[i++].trim();
//			    String suffix = values[i++].trim();
//			    String taxable_building = values[i++].trim();
//			    String taxable_land = values[i++].trim();
//			    String topography = values[i++].trim();
//			    String total_area = values[i++].trim();
//			    String total_livable_area = values[i++].trim();
//			    String type_heater = values[i++].trim();
//			    String unfinished = values[i++].trim();
//			    String unit = values[i++].trim();
//			    String utility = values[i++].trim();
//			    String view_type = values[i++].trim();
//			    String year_built = values[i++].trim();
//			    String year_built_estimate = values[i++].trim();
//			    String zip_code = values[i++].trim();
//			    if(zip_code.length() > 5) {
//			    	zip_code = zip_code.substring(0, 5);
//			    }
//			    String zoning = values[i++].trim();
//			    String objectid = values[i++].trim();
//			    String lat = values[i++].trim();
//			    String lng = values[i++].trim();
//			    Property p = new Property(total_livable_area, zip_code, market_value);
//				pList.add(p);
//				}
//				csvReader.close();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
		return pList;
	}
	
	

}
