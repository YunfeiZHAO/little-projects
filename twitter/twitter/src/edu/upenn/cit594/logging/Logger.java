package edu.upenn.cit594.logging;

import java.io.File;
import java.io.PrintWriter;

import edu.upenn.cit594.processor​.Processor;

public class Logger {
	private PrintWriter out;
	private static Logger instance;

	private Logger(String filename) {
		try{ out = new PrintWriter(new File(filename));}
		catch(Exception e){System.out.print("Logger problem");}
	}
	
	public static Logger getInstance(String path) {
		instance = new Logger(path);
		return instance;
	}
	
	public void log(String msg) {
		out.println(msg);
		out.flush();
	}
	public void log(Processor p) {
		for(String[] message : p.log) {
			out.println(message[0] + "\t" + message[1]);
			out.flush();
		}
	}
}
