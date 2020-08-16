package edu.upenn.cit594.processor​;

import edu.upenn.cit594.datamanagement​.Reader;
import edu.upenn.cit594.datamanagement​.TxtReader;

public class TxtProcessor extends Processor {

	public TxtProcessor() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Reader createReader() {
		// TODO Auto-generated method stub
		return new TxtReader();
	}

}
