package edu.upenn.cit594.processor​;

import java.util.Map;
import java.util.regex.Pattern;

import edu.upenn.cit594.data​.Data;
import edu.upenn.cit594.data​.Message;

public class Search {
	static boolean search(Message message) {
		String text = message.text;
		text = text.toLowerCase();
		return Pattern.matches(".* flu .*", text)
				|| Pattern.matches("^flu .*", text)
				|| Pattern.matches(".* flu$", text)
				|| Pattern.matches(".* #flu .*", text)
				|| Pattern.matches("^#flu .*", text)
				|| Pattern.matches(".* #flu$", text)
				|| Pattern.matches(".* flu[^a-z] .*", text)
				|| Pattern.matches("^flu[^a-z] .*", text)
				|| Pattern.matches(".* flu[^a-z]$", text);
		
	}
	
	static public String search(Data data, Float[] position) {
		Map<String, Float[]> states = data.states;
		Float dist = Float.MAX_VALUE;
		Float n_dist;
		String s = "";
		for(Map.Entry<String, Float[]> state : states.entrySet()) {
			Float[] pos = state.getValue();
			n_dist = (float)(Math.pow((pos[0] - position[0]), 2) + Math.pow((pos[1] - position[1]), 2)); 
			if(n_dist < dist) {
				dist = n_dist;
				s = state.getKey();
			}
		}
		return s;
	}
}
