package edu.upenn.cit594.datamanagement;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import edu.upenn.cit594.data.Fine;
import edu.upenn.cit594.logging.Logger;

public class JsonFineDataManager implements FineDataManagerInterface {
	/*
	 * 12/2/19
	 * I removed the "ticket_number" field from the Fine class and its constructor.
	 * This is because the JsonFineDataManager class was throwing an exception when I ran the code.
	 * Evidently, the JSONarray treated "ticket_number" as a long and refused to cast it as a string.
	 * Given that ticket numbers are not used anywhere in the project, the most expedient
	 * solution seemed to do away with the field in this class. Other classes that depend on 
	 * the Fine class have also been modified so that "ticket_number" is no longer an issue.
	 */

	@Override
	public List<Fine> getFineList(Logger _log, String fileName) {
		
		List<Fine> fineList = new ArrayList<Fine>();
		
		JSONParser parser = new JSONParser();
		JSONArray fineJsonArray = null;
		try {
			_log.log(System.currentTimeMillis() + " " + fileName);
			fineJsonArray = (JSONArray)parser.parse(new FileReader(fileName));
		} catch (Exception e) {
			return null;
		}
		@SuppressWarnings("rawtypes")
		Iterator iter = fineJsonArray.iterator();
		while (iter.hasNext()) {
			JSONObject fineObj = (JSONObject) iter.next();
			String date = (String) fineObj.get("date");
		    long fineL = (long) fineObj.get("fine");
		    double fine = (double) fineL;
		    String violation = (String) fineObj.get("violation");
		    String plate_id = (String) fineObj.get("plate_id");
		    String state = (String) fineObj.get("state");
		    //String ticket_number = (String) fineObj.get("ticket_number");
		    String zip_code = (String) fineObj.get("zip_code");
		    if(zip_code == null || "".equalsIgnoreCase(zip_code.trim())) {
				continue;
			}
			if(!"PA".equalsIgnoreCase(state)) {
				continue;
			}
		    Fine f = new Fine(plate_id, date, zip_code, violation, fine, state);
			if(f != null) {
				fineList.add(f);
			}
		}
		return fineList;
	}

}
