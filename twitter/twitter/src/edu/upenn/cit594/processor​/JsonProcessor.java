package edu.upenn.cit594.processor​;

import edu.upenn.cit594.datamanagement​.JsonReader;
import edu.upenn.cit594.datamanagement​.Reader;
import org.json.simple.*;

public class JsonProcessor extends Processor {
	@Override
	protected Reader createReader() {
		return new JsonReader();
	}
}
