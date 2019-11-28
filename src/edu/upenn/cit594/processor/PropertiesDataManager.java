package edu.upenn.cit594.processor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import edu.upenn.cit594.data.Property;
import edu.upenn.cit594.logging.Logger;

public class PropertiesDataManager {
	
	public List<Property> getPropertiesList(Logger _log, String fileName) {
		
		List<Property> pList = new ArrayList<Property>();
		String row = null;
		try {
			_log.log(System.currentTimeMillis() + " " + fileName);
			BufferedReader csvReader = new BufferedReader(new FileReader(new File(fileName)));
			csvReader.readLine();//ignore first row.
			while ((row  = csvReader.readLine()) != null) {
				String[] values = row.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
				int i = 0;
			    String number_of_rooms = values[i++].trim();
			    String assessment_date = values[i++].trim();
			    String beginning_point = values[i++].trim();
			    String book_and_page = values[i++].trim();
			    String building_code = values[i++].trim();
			    String building_code_description = values[i++].trim();
			    String category_code = values[i++].trim();
			    String category_code_description = values[i++].trim();
			    String census_tract = values[i++].trim();
			    String central_air = values[i++].trim();
			    String cross_reference = values[i++].trim();
			    String date_exterior_condition = values[i++].trim();
			    String depth = values[i++].trim();
			    String exempt_building = values[i++].trim();
			    String exempt_land = values[i++].trim();
			    String exterior_condition = values[i++].trim();
			    String fireplaces = values[i++].trim();
			    String frontage = values[i++].trim();
			    String fuel = values[i++].trim();
			    String garage_spaces = values[i++].trim();
			    String garage_type = values[i++].trim();
			    String general_construction = values[i++].trim();
			    String geographic_ward = values[i++].trim();
			    String homestead_exemption = values[i++].trim();
			    String house_extension = values[i++].trim();
			    String house_number = values[i++].trim();
			    String interior_condition = values[i++].trim();
			    String location = values[i++].trim();
			    String mailing_address_1 = values[i++].trim();
			    String mailing_address_2 = values[i++].trim();
			    String mailing_care_of = values[i++].trim();
			    String mailing_city_state = values[i++].trim();
			    String mailing_street = values[i++].trim();
			    String mailing_zip = values[i++].trim();
			    String market_value = values[i++].trim();
			    String market_value_date = values[i++].trim();
			    String number_of_bathrooms = values[i++].trim();
			    String number_of_bedrooms = values[i++].trim();
			    String basements = values[i++].trim();
			    String number_stories = values[i++].trim();
			    String off_street_open = values[i++].trim();
			    String other_building = values[i++].trim();
			    String owner_1 = values[i++].trim();
			    String owner_2 = values[i++].trim();
			    String parcel_number = values[i++].trim();
			    String parcel_shape = values[i++].trim();
			    String quality_grade = values[i++].trim();
			    String recording_date = values[i++].trim();
			    String registry_number = values[i++].trim();
			    String sale_date = values[i++].trim();
			    String sale_price = values[i++].trim();
			    String separate_utilities = values[i++].trim();
			    String sewer = values[i++].trim();
			    String site_type = values[i++].trim();
			    String state_code = values[i++].trim();
			    String street_code = values[i++].trim();
			    String street_designation = values[i++].trim();
			    String street_direction = values[i++].trim();
			    String street_name = values[i++].trim();
			    String suffix = values[i++].trim();
			    String taxable_building = values[i++].trim();
			    String taxable_land = values[i++].trim();
			    String topography = values[i++].trim();
			    String total_area = values[i++].trim();
			    String total_livable_area = values[i++].trim();
			    String type_heater = values[i++].trim();
			    String unfinished = values[i++].trim();
			    String unit = values[i++].trim();
			    String utility = values[i++].trim();
			    String view_type = values[i++].trim();
			    String year_built = values[i++].trim();
			    String year_built_estimate = values[i++].trim();
			    String zip_code = values[i++].trim();
			    if(zip_code.length() > 5) {
			    	zip_code = zip_code.substring(0, 5);
			    }
			    String zoning = values[i++].trim();
			    String objectid = values[i++].trim();
			    String lat = values[i++].trim();
			    String lng = values[i++].trim();
			    Property p = new Property(number_of_rooms,assessment_date,beginning_point,book_and_page,building_code,building_code_description,category_code,category_code_description,census_tract,central_air,cross_reference,date_exterior_condition,depth,exempt_building,exempt_land,exterior_condition,fireplaces,frontage,fuel,garage_spaces,garage_type,general_construction,geographic_ward,homestead_exemption,house_extension,house_number,interior_condition,location,mailing_address_1,mailing_address_2,mailing_care_of,mailing_city_state,mailing_street,mailing_zip,market_value,market_value_date,number_of_bathrooms,number_of_bedrooms,basements,number_stories,off_street_open,other_building,owner_1,owner_2,parcel_number,parcel_shape,quality_grade,recording_date,registry_number,sale_date,sale_price,separate_utilities,sewer,site_type,state_code,street_code,street_designation,street_direction,street_name,suffix,taxable_building,taxable_land,topography,total_area,total_livable_area,type_heater,unfinished,unit,utility,view_type,year_built,year_built_estimate,zip_code,zoning,objectid,lat,lng);
				if(p != null) {
					pList.add(p);
				}
			}
			csvReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pList;
	}
	
	

}
