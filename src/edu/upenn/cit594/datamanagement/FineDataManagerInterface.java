package edu.upenn.cit594.datamanagement;

import java.util.List;

import edu.upenn.cit594.data.Fine;
import edu.upenn.cit594.logging.Logger;

public interface FineDataManagerInterface {

	List<Fine> getFineList(Logger _log, String parking_violation_input_file_name);

}
