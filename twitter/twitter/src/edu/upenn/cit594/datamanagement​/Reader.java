package edu.upenn.cit594.datamanagement​;

import edu.upenn.cit594.data​.Data;

public interface Reader {
	public Data read(String path_message, String path_states);
}
