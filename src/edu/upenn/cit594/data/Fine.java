package edu.upenn.cit594.data;

public class Fine {
	
	/*
	 * 12/2/19
	 * I removed the "ticket_number" field from this class and its constructor.
	 * This is because the JsonFineDataManager was throwing an exception when I ran the code.
	 * Evidently, the JSONarray treated "ticket_number" as a long and refused to cast it as a string.
	 * Given that ticket numbers are not used anywhere in the project, the most expedient
	 * solution seemed to do away with the field in this class. Other classes that depend on 
	 * the Fine class have also been modified so that "ticket_number" is no longer an issue.
	 */
	
	//private String ticket_number;
	private String plate_id;
	private String date;
	private String zip_code;
	private String violation;
	private double fine;
	private String state;
	
	public Fine(String plate_id, String date,
			String zip_code, String violation, double fine, String state) {
		super();
		//this.ticket_number = ticket_number;
		this.plate_id = plate_id;
		this.date = date;
		this.zip_code = zip_code;
		this.violation = violation;
		this.fine = fine;
		this.state = state;
	}
//	public String getTicket_number() {
//		return ticket_number;
//	}
//	public void setTicket_number(String ticket_number) {
//		this.ticket_number = ticket_number;
//	}
	public String getPlate_id() {
		return plate_id;
	}
	public void setPlate_id(String plate_id) {
		this.plate_id = plate_id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getZip_code() {
		return zip_code;
	}
	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	}
	public String getViolation() {
		return violation;
	}
	public void setViolation(String violation) {
		this.violation = violation;
	}
	public double getFine() {
		return fine;
	}
	public void setFine(double fine) {
		this.fine = fine;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	

}
