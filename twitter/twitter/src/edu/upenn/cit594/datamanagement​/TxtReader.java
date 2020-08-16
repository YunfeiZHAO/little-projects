package edu.upenn.cit594.datamanagement​;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.json.simple.parser.JSONParser;
import edu.upenn.cit594.data​.Data;

public class TxtReader implements Reader{

	public TxtReader() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Data read(String path_message, String path_states) {
		Data data = new Data(path_states);
		File f = new File(path_message);
		if(f.exists()) {
			if(f.canRead()) {
		        try {
		        	String line;
		        	BufferedReader br = new BufferedReader(new FileReader(path_message));
		            while ((line = br.readLine()) != null) {
		               String[] elements = line.split("\t");
		               data.add(elements);
		               
		            }       
		         } catch(Exception e) {
		        	 e.printStackTrace();
		         }
			}
		}
		return data;
	}

}
