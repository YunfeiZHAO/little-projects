package edu.upenn.cit594.datamanagement​;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import edu.upenn.cit594.data​.Data;


public class JsonReader implements Reader {

	public JsonReader() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Data read(String path_message, String path_states) {
		Data data = new Data(path_states);
		JSONParser parser = new JSONParser();
		File f = new File(path_message);
		if(f.exists()) {
			if(f.canRead()) {
				try {
					JSONArray messages = (JSONArray)parser.parse(new FileReader(path_message));
					Iterator iter = messages.iterator();
					while(iter.hasNext()) {
						JSONObject message = (JSONObject) iter.next();
						data.add(message);
					}
					//JSONObject message = (JSONObject) iter.next();
					//System.out.println(message.to.getClass().getName());
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				System.out.print("Can't read file:" + path_message);
			}
		} else {
			System.out.print("File no found");
		}
		return data;
	}

}
