package edu.upenn.cit594.processor​;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import edu.upenn.cit594.datamanagement​.Reader;
import edu.upenn.cit594.data​.Data;
import edu.upenn.cit594.data​.Message;

public abstract class Processor {
	protected Reader reader;
	Data data;
	Map<String, Integer> count = new HashMap<String, Integer>();
	public ArrayList<String[]> log = new ArrayList<String[]>();
	
	
	public Processor() {
		reader = createReader();
	}
	public void process(String path_message, String path_states) {
		data = reader.read(path_message, path_states);
		for(Message message : data.messages) {
			if(Search.search(message)) {
				String state = Search.search(data, message.location);
				if(count.containsKey(state)){
					Integer num = count.get(state);
					num++;
					count.put(state, num);
				} else {
					count.put(state, 1);
				}
				String log_message[] = new String[2];
				log_message[0] = state;
				log_message[1] = message.text;
				log.add(log_message);
			}
		}
		for(Map.Entry<String, Integer> state : count.entrySet()) {
			System.out.println(state.getKey() + ": " + state.getValue());
		}
	}
	
	protected abstract Reader createReader();
}
