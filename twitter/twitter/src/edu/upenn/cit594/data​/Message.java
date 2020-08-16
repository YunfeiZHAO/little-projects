package edu.upenn.cit594.data​;

import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Message {
	public Float[] location = new Float[2];
	public String time;
	public String text;
	
	public Message(JSONObject o) {
		String locationS = o.get("location").toString();
		String[] loc = locationS.substring(1,locationS.length() - 1).split(",");
		time = o.get("time").toString();
		text = o.get("text").toString();
		location[0] = Float.parseFloat(loc[0]);
		location[1] = Float.parseFloat(loc[1]);
	}
	
	public Message(String[] o) {
		String[] loc = o[0].substring(1,o[0].length() - 1).split(",");
		time = o[2];
		text = o[3];
		location[0] = Float.parseFloat(loc[0]);
		location[1] = Float.parseFloat(loc[1]);
	}
}
