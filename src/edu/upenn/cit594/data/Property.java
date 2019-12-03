package edu.upenn.cit594.data;

public class Property {
	
	/*
	 * 12/2/19
	 * I simplified the Property class so that it has only 3 attributes--market value,
	 * total livable area, and zip code. The project only uses these 3 attributes.
	 * 
	 * Furthermore, the PropertiesDataManager class has been modified so that it 
	 * reads the first row and determines which column contains each of those
	 * attributes.
	 */
	
	private String market_value;
	private String total_livable_area;
	private String zip_code;
	
//	private String number_of_rooms;
//	private String assessment_date;
//	private String beginning_point;
//	private String book_and_page;
//	private String building_code;
//	private String building_code_description;
//	private String category_code;
//	private String category_code_description;
//	private String census_tract;
//	private String central_air;
//	private String cross_reference;
//	private String date_exterior_condition;
//	private String depth;
//	private String exempt_building;
//	private String exempt_land;
//	private String exterior_condition;
//	private String fireplaces;
//	private String frontage;
//	private String fuel;
//	private String garage_spaces;
//	private String garage_type;
//	private String general_construction;
//	private String geographic_ward;
//	private String homestead_exemption;
//	private String house_extension;
//	private String house_number;
//	private String interior_condition;
//	private String location;
//	private String mailing_address_1;
//	private String mailing_address_2;
//	private String mailing_care_of;
//	private String mailing_city_state;
//	private String mailing_street;
//	private String mailing_zip;
//	private String market_value_date;
//	private String number_of_bathrooms;
//	private String number_of_bedrooms;
//	private String basements;
//	private String number_stories;
//	private String off_street_open;
//	private String other_building;
//	private String owner_1;
//	private String owner_2;
//	private String parcel_number;
//	private String parcel_shape;
//	private String quality_grade;
//	private String recording_date;
//	private String registry_number;
//	private String sale_date;
//	private String sale_price;
//	private String separate_utilities;
//	private String sewer;
//	private String site_type;
//	private String state_code;
//	private String street_code;
//	private String street_designation;
//	private String street_direction;
//	private String street_name;
//	private String suffix;
//	private String taxable_building;
//	private String taxable_land;
//	private String topography;
//	private String total_area;
//	private String type_heater;
//	private String unfinished;
//	private String unit;
//	private String utility;
//	private String view_type;
//	private String year_built;
//	private String year_built_estimate;
//	private String zoning;
//	private String objectid;
//	private String lat;
//	private String lng;
//	public String getNumber_of_rooms() {
//		return number_of_rooms;
//	}
//	public void setNumber_of_rooms(String number_of_rooms) {
//		this.number_of_rooms = number_of_rooms;
//	}
//	public String getAssessment_date() {
//		return assessment_date;
//	}
//	public void setAssessment_date(String assessment_date) {
//		this.assessment_date = assessment_date;
//	}
//	public String getBeginning_point() {
//		return beginning_point;
//	}
//	public void setBeginning_point(String beginning_point) {
//		this.beginning_point = beginning_point;
//	}
//	public String getBook_and_page() {
//		return book_and_page;
//	}
//	public void setBook_and_page(String book_and_page) {
//		this.book_and_page = book_and_page;
//	}
//	public String getBuilding_code() {
//		return building_code;
//	}
//	public void setBuilding_code(String building_code) {
//		this.building_code = building_code;
//	}
//	public String getBuilding_code_description() {
//		return building_code_description;
//	}
//	public void setBuilding_code_description(String building_code_description) {
//		this.building_code_description = building_code_description;
//	}
//	public String getCategory_code() {
//		return category_code;
//	}
//	public void setCategory_code(String category_code) {
//		this.category_code = category_code;
//	}
//	public String getCategory_code_description() {
//		return category_code_description;
//	}
//	public void setCategory_code_description(String category_code_description) {
//		this.category_code_description = category_code_description;
//	}
//	public String getCensus_tract() {
//		return census_tract;
//	}
//	public void setCensus_tract(String census_tract) {
//		this.census_tract = census_tract;
//	}
//	public String getCentral_air() {
//		return central_air;
//	}
//	public void setCentral_air(String central_air) {
//		this.central_air = central_air;
//	}
//	public String getCross_reference() {
//		return cross_reference;
//	}
//	public void setCross_reference(String cross_reference) {
//		this.cross_reference = cross_reference;
//	}
//	public String getDate_exterior_condition() {
//		return date_exterior_condition;
//	}
//	public void setDate_exterior_condition(String date_exterior_condition) {
//		this.date_exterior_condition = date_exterior_condition;
//	}
//	public String getDepth() {
//		return depth;
//	}
//	public void setDepth(String depth) {
//		this.depth = depth;
//	}
//	public String getExempt_building() {
//		return exempt_building;
//	}
//	public void setExempt_building(String exempt_building) {
//		this.exempt_building = exempt_building;
//	}
//	public String getExempt_land() {
//		return exempt_land;
//	}
//	public void setExempt_land(String exempt_land) {
//		this.exempt_land = exempt_land;
//	}
//	public String getExterior_condition() {
//		return exterior_condition;
//	}
//	public void setExterior_condition(String exterior_condition) {
//		this.exterior_condition = exterior_condition;
//	}
//	public String getFireplaces() {
//		return fireplaces;
//	}
//	public void setFireplaces(String fireplaces) {
//		this.fireplaces = fireplaces;
//	}
//	public String getFrontage() {
//		return frontage;
//	}
//	public void setFrontage(String frontage) {
//		this.frontage = frontage;
//	}
//	public String getFuel() {
//		return fuel;
//	}
//	public void setFuel(String fuel) {
//		this.fuel = fuel;
//	}
//	public String getGarage_spaces() {
//		return garage_spaces;
//	}
//	public void setGarage_spaces(String garage_spaces) {
//		this.garage_spaces = garage_spaces;
//	}
//	public String getGarage_type() {
//		return garage_type;
//	}
//	public void setGarage_type(String garage_type) {
//		this.garage_type = garage_type;
//	}
//	public String getGeneral_construction() {
//		return general_construction;
//	}
//	public void setGeneral_construction(String general_construction) {
//		this.general_construction = general_construction;
//	}
//	public String getGeographic_ward() {
//		return geographic_ward;
//	}
//	public void setGeographic_ward(String geographic_ward) {
//		this.geographic_ward = geographic_ward;
//	}
//	public String getHomestead_exemption() {
//		return homestead_exemption;
//	}
//	public void setHomestead_exemption(String homestead_exemption) {
//		this.homestead_exemption = homestead_exemption;
//	}
//	public String getHouse_extension() {
//		return house_extension;
//	}
//	public void setHouse_extension(String house_extension) {
//		this.house_extension = house_extension;
//	}
//	public String getHouse_number() {
//		return house_number;
//	}
//	public void setHouse_number(String house_number) {
//		this.house_number = house_number;
//	}
//	public String getInterior_condition() {
//		return interior_condition;
//	}
//	public void setInterior_condition(String interior_condition) {
//		this.interior_condition = interior_condition;
//	}
//	public String getLocation() {
//		return location;
//	}
//	public void setLocation(String location) {
//		this.location = location;
//	}
//	public String getMailing_address_1() {
//		return mailing_address_1;
//	}
//	public void setMailing_address_1(String mailing_address_1) {
//		this.mailing_address_1 = mailing_address_1;
//	}
//	public String getMailing_address_2() {
//		return mailing_address_2;
//	}
//	public void setMailing_address_2(String mailing_address_2) {
//		this.mailing_address_2 = mailing_address_2;
//	}
//	public String getMailing_care_of() {
//		return mailing_care_of;
//	}
//	public void setMailing_care_of(String mailing_care_of) {
//		this.mailing_care_of = mailing_care_of;
//	}
//	public String getMailing_city_state() {
//		return mailing_city_state;
//	}
//	public void setMailing_city_state(String mailing_city_state) {
//		this.mailing_city_state = mailing_city_state;
//	}
//	public String getMailing_street() {
//		return mailing_street;
//	}
//	public void setMailing_street(String mailing_street) {
//		this.mailing_street = mailing_street;
//	}
//	public String getMailing_zip() {
//		return mailing_zip;
//	}
//	public void setMailing_zip(String mailing_zip) {
//		this.mailing_zip = mailing_zip;
//	}
//
//	public String getMarket_value_date() {
//		return market_value_date;
//	}
//	public void setMarket_value_date(String market_value_date) {
//		this.market_value_date = market_value_date;
//	}
//	public String getNumber_of_bathrooms() {
//		return number_of_bathrooms;
//	}
//	public void setNumber_of_bathrooms(String number_of_bathrooms) {
//		this.number_of_bathrooms = number_of_bathrooms;
//	}
//	public String getNumber_of_bedrooms() {
//		return number_of_bedrooms;
//	}
//	public void setNumber_of_bedrooms(String number_of_bedrooms) {
//		this.number_of_bedrooms = number_of_bedrooms;
//	}
//	public String getBasements() {
//		return basements;
//	}
//	public void setBasements(String basements) {
//		this.basements = basements;
//	}
//	public String getNumber_stories() {
//		return number_stories;
//	}
//	public void setNumber_stories(String number_stories) {
//		this.number_stories = number_stories;
//	}
//	public String getOff_street_open() {
//		return off_street_open;
//	}
//	public void setOff_street_open(String off_street_open) {
//		this.off_street_open = off_street_open;
//	}
//	public String getOther_building() {
//		return other_building;
//	}
//	public void setOther_building(String other_building) {
//		this.other_building = other_building;
//	}
//	public String getOwner_1() {
//		return owner_1;
//	}
//	public void setOwner_1(String owner_1) {
//		this.owner_1 = owner_1;
//	}
//	public String getOwner_2() {
//		return owner_2;
//	}
//	public void setOwner_2(String owner_2) {
//		this.owner_2 = owner_2;
//	}
//	public String getParcel_number() {
//		return parcel_number;
//	}
//	public void setParcel_number(String parcel_number) {
//		this.parcel_number = parcel_number;
//	}
//	public String getParcel_shape() {
//		return parcel_shape;
//	}
//	public void setParcel_shape(String parcel_shape) {
//		this.parcel_shape = parcel_shape;
//	}
//	public String getQuality_grade() {
//		return quality_grade;
//	}
//	public void setQuality_grade(String quality_grade) {
//		this.quality_grade = quality_grade;
//	}
//	public String getRecording_date() {
//		return recording_date;
//	}
//	public void setRecording_date(String recording_date) {
//		this.recording_date = recording_date;
//	}
//	public String getRegistry_number() {
//		return registry_number;
//	}
//	public void setRegistry_number(String registry_number) {
//		this.registry_number = registry_number;
//	}
//	public String getSale_date() {
//		return sale_date;
//	}
//	public void setSale_date(String sale_date) {
//		this.sale_date = sale_date;
//	}
//	public String getSale_price() {
//		return sale_price;
//	}
//	public void setSale_price(String sale_price) {
//		this.sale_price = sale_price;
//	}
//	public String getSeparate_utilities() {
//		return separate_utilities;
//	}
//	public void setSeparate_utilities(String separate_utilities) {
//		this.separate_utilities = separate_utilities;
//	}
//	public String getSewer() {
//		return sewer;
//	}
//	public void setSewer(String sewer) {
//		this.sewer = sewer;
//	}
//	public String getSite_type() {
//		return site_type;
//	}
//	public void setSite_type(String site_type) {
//		this.site_type = site_type;
//	}
//	public String getState_code() {
//		return state_code;
//	}
//	public void setState_code(String state_code) {
//		this.state_code = state_code;
//	}
//	public String getStreet_code() {
//		return street_code;
//	}
//	public void setStreet_code(String street_code) {
//		this.street_code = street_code;
//	}
//	public String getStreet_designation() {
//		return street_designation;
//	}
//	public void setStreet_designation(String street_designation) {
//		this.street_designation = street_designation;
//	}
//	public String getStreet_direction() {
//		return street_direction;
//	}
//	public void setStreet_direction(String street_direction) {
//		this.street_direction = street_direction;
//	}
//	public String getStreet_name() {
//		return street_name;
//	}
//	public void setStreet_name(String street_name) {
//		this.street_name = street_name;
//	}
//	public String getSuffix() {
//		return suffix;
//	}
//	public void setSuffix(String suffix) {
//		this.suffix = suffix;
//	}
//	public String getTaxable_building() {
//		return taxable_building;
//	}
//	public void setTaxable_building(String taxable_building) {
//		this.taxable_building = taxable_building;
//	}
//	public String getTaxable_land() {
//		return taxable_land;
//	}
//	public void setTaxable_land(String taxable_land) {
//		this.taxable_land = taxable_land;
//	}
//	public String getTopography() {
//		return topography;
//	}
//	public void setTopography(String topography) {
//		this.topography = topography;
//	}
//	public String getTotal_area() {
//		return total_area;
//	}
//	public void setTotal_area(String total_area) {
//		this.total_area = total_area;
//	}
//
//	public String getType_heater() {
//		return type_heater;
//	}
//	public void setType_heater(String type_heater) {
//		this.type_heater = type_heater;
//	}
//	public String getUnfinished() {
//		return unfinished;
//	}
//	public void setUnfinished(String unfinished) {
//		this.unfinished = unfinished;
//	}
//	public String getUnit() {
//		return unit;
//	}
//	public void setUnit(String unit) {
//		this.unit = unit;
//	}
//	public String getUtility() {
//		return utility;
//	}
//	public void setUtility(String utility) {
//		this.utility = utility;
//	}
//	public String getView_type() {
//		return view_type;
//	}
//	public void setView_type(String view_type) {
//		this.view_type = view_type;
//	}
//	public String getYear_built() {
//		return year_built;
//	}
//	public void setYear_built(String year_built) {
//		this.year_built = year_built;
//	}
//	public String getYear_built_estimate() {
//		return year_built_estimate;
//	}
//	public void setYear_built_estimate(String year_built_estimate) {
//		this.year_built_estimate = year_built_estimate;
//	}
//
//	public String getZoning() {
//		return zoning;
//	}
//	public void setZoning(String zoning) {
//		this.zoning = zoning;
//	}
//	public String getObjectid() {
//		return objectid;
//	}
//	public void setObjectid(String objectid) {
//		this.objectid = objectid;
//	}
//	public String getLat() {
//		return lat;
//	}
//	public void setLat(String lat) {
//		this.lat = lat;
//	}
//	public String getLng() {
//		return lng;
//	}
//	public void setLng(String lng) {
//		this.lng = lng;
//	}
	//
	//
	//
	public String getTotal_livable_area() {
		return total_livable_area;
	}
	public void setTotal_livable_area(String total_livable_area) {
		this.total_livable_area = total_livable_area;
	}
	
	public String getZip_code() {
		return zip_code;
	}
	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	}
	
	public String getMarket_value() {
		return market_value;
	}
	public void setMarket_value(String market_value) {
		this.market_value = market_value;
	}
	
	// new constructor method
	// keep only the attributes that are used for the project
	public Property(String total_livable_area, String zip_code, String market_value) {
		super();
		this.market_value = market_value;
		this.total_livable_area = total_livable_area;
		this.zip_code = zip_code;
	}
	
//	public Property(String number_of_rooms, String assessment_date, String beginning_point, String book_and_page,
//			String building_code, String building_code_description, String category_code,
//			String category_code_description, String census_tract, String central_air, String cross_reference,
//			String date_exterior_condition, String depth, String exempt_building, String exempt_land,
//			String exterior_condition, String fireplaces, String frontage, String fuel, String garage_spaces,
//			String garage_type, String general_construction, String geographic_ward, String homestead_exemption,
//			String house_extension, String house_number, String interior_condition, String location,
//			String mailing_address_1, String mailing_address_2, String mailing_care_of, String mailing_city_state,
//			String mailing_street, String mailing_zip, String market_value, String market_value_date,
//			String number_of_bathrooms, String number_of_bedrooms, String basements, String number_stories,
//			String off_street_open, String other_building, String owner_1, String owner_2, String parcel_number,
//			String parcel_shape, String quality_grade, String recording_date, String registry_number, String sale_date,
//			String sale_price, String separate_utilities, String sewer, String site_type, String state_code,
//			String street_code, String street_designation, String street_direction, String street_name, String suffix,
//			String taxable_building, String taxable_land, String topography, String total_area,
//			String total_livable_area, String type_heater, String unfinished, String unit, String utility,
//			String view_type, String year_built, String year_built_estimate, String zip_code, String zoning,
//			String objectid, String lat, String lng) {
//		super();
//		this.number_of_rooms = number_of_rooms;
//		this.assessment_date = assessment_date;
//		this.beginning_point = beginning_point;
//		this.book_and_page = book_and_page;
//		this.building_code = building_code;
//		this.building_code_description = building_code_description;
//		this.category_code = category_code;
//		this.category_code_description = category_code_description;
//		this.census_tract = census_tract;
//		this.central_air = central_air;
//		this.cross_reference = cross_reference;
//		this.date_exterior_condition = date_exterior_condition;
//		this.depth = depth;
//		this.exempt_building = exempt_building;
//		this.exempt_land = exempt_land;
//		this.exterior_condition = exterior_condition;
//		this.fireplaces = fireplaces;
//		this.frontage = frontage;
//		this.fuel = fuel;
//		this.garage_spaces = garage_spaces;
//		this.garage_type = garage_type;
//		this.general_construction = general_construction;
//		this.geographic_ward = geographic_ward;
//		this.homestead_exemption = homestead_exemption;
//		this.house_extension = house_extension;
//		this.house_number = house_number;
//		this.interior_condition = interior_condition;
//		this.location = location;
//		this.mailing_address_1 = mailing_address_1;
//		this.mailing_address_2 = mailing_address_2;
//		this.mailing_care_of = mailing_care_of;
//		this.mailing_city_state = mailing_city_state;
//		this.mailing_street = mailing_street;
//		this.mailing_zip = mailing_zip;
//		this.market_value = market_value;
//		this.market_value_date = market_value_date;
//		this.number_of_bathrooms = number_of_bathrooms;
//		this.number_of_bedrooms = number_of_bedrooms;
//		this.basements = basements;
//		this.number_stories = number_stories;
//		this.off_street_open = off_street_open;
//		this.other_building = other_building;
//		this.owner_1 = owner_1;
//		this.owner_2 = owner_2;
//		this.parcel_number = parcel_number;
//		this.parcel_shape = parcel_shape;
//		this.quality_grade = quality_grade;
//		this.recording_date = recording_date;
//		this.registry_number = registry_number;
//		this.sale_date = sale_date;
//		this.sale_price = sale_price;
//		this.separate_utilities = separate_utilities;
//		this.sewer = sewer;
//		this.site_type = site_type;
//		this.state_code = state_code;
//		this.street_code = street_code;
//		this.street_designation = street_designation;
//		this.street_direction = street_direction;
//		this.street_name = street_name;
//		this.suffix = suffix;
//		this.taxable_building = taxable_building;
//		this.taxable_land = taxable_land;
//		this.topography = topography;
//		this.total_area = total_area;
//		this.total_livable_area = total_livable_area;
//		this.type_heater = type_heater;
//		this.unfinished = unfinished;
//		this.unit = unit;
//		this.utility = utility;
//		this.view_type = view_type;
//		this.year_built = year_built;
//		this.year_built_estimate = year_built_estimate;
//		this.zip_code = zip_code;
//		this.zoning = zoning;
//		this.objectid = objectid;
//		this.lat = lat;
//		this.lng = lng;
//	}
	
}
