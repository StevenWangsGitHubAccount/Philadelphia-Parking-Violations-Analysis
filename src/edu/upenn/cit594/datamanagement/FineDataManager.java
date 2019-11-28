package edu.upenn.cit594.datamanagement;

import java.util.List;

import edu.upenn.cit594.data.Fine;
import edu.upenn.cit594.logging.Logger;

public abstract class FineDataManager implements FineDataManagerInterface {

	Fine createFineObj(String ticket_number, String plate_id,
			String date, String zip_code, String violation, String fine,
			String state) {
		if(zip_code == null || "".equalsIgnoreCase(zip_code.trim())) {
			return null;
		}
		if(!"PA".equalsIgnoreCase(state)) {
			return null;
		}
		Fine f = new Fine(ticket_number, plate_id, date, zip_code, violation, fine, state);
		return f;
	}

	public abstract List<Fine> getFineList(Logger _log, String parking_violation_input_file_name);
}
