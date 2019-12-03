package edu.upenn.cit594.datamanagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import edu.upenn.cit594.data.Fine;
import edu.upenn.cit594.logging.Logger;

public class CsvFineDataManager extends FineDataManager {
	
	@Override
	public List<Fine> getFineList(Logger _log, String fileName) {
		
		List<Fine> fineList = new ArrayList<Fine>();
		
		try {
			_log.log(System.currentTimeMillis() + " " + fileName);
			BufferedReader csvReader = new BufferedReader(new FileReader(new File(fileName)));
			String row = null;
			while ((row  = csvReader.readLine()) != null) {
				String[] values = row.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
				int i = 0;
			    String date = values[i++].trim();
			    String fineString = values[i++].trim();
			    double fine = Double.parseDouble(fineString);
			    String violation = values[i++].trim();
			    String plate_id = values[i++].trim();
			    String state = values[i++].trim();
			    String ticket_number = values[i++].trim();
			    String zip_code = values[i++].trim();
			    if(zip_code.length() > 5) {
			    	zip_code = zip_code.substring(0, 5);
			    }
			    Fine f = createFineObj(plate_id, date, zip_code, violation, fine, state);
				if(f != null) {
					fineList.add(f);
				}
			}
			csvReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fineList;
	}

}
