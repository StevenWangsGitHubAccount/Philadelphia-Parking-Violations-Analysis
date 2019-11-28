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

public class JsonFineDataManager extends FineDataManager {

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
		    String fine = (String) fineObj.get("fine");
		    String violation = (String) fineObj.get("violation");
		    String plate_id = (String) fineObj.get("plate_id");
		    String state = (String) fineObj.get("state");
		    String ticket_number = (String) fineObj.get("ticket_number");
		    String zip_code = (String) fineObj.get("zip_code");
		    Fine f = createFineObj(ticket_number, plate_id, date, zip_code, violation, fine, state);
			if(f != null) {
				fineList.add(f);
			}
		}
		return fineList;
	}

}
