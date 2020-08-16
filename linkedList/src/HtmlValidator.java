

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.util.*;

public class HtmlValidator {
	
	/* 
	 * Implement this method!
	 */
	public static Stack<HtmlTag> isValidHtml(Queue<HtmlTag> tags) {
		Stack<HtmlTag> s = new Stack<HtmlTag>();
		HtmlTag cur = null;
		while(!tags.isEmpty()) {
			 cur = tags.poll();
			 if(!cur.isSelfClosing()){
			 	if(cur.isOpenTag()) {
			 		s.push(cur);
				} else {
			 		if(s.isEmpty()) { return null;}
			 		else {
						HtmlTag top = s.peek();
						if(top.matches(cur)) {
							s.pop();
						} else { return s;}
					}
				}
			 }
		}
		return s;
	}
	
	/*
	 * Instructor-provided code:
	 * This function reads an HTML file and outputs its structure as the HtmlTags only.
	 * You do not need to modify this code for this assignment but can use it for your testing!
	 */
	public static Queue<HtmlTag> getTagsFromHtmlFile(String filename) throws IOException {
	     InputStream stream = new FileInputStream(filename);
	     StringBuffer buffer = new StringBuffer();
	     int ch;
	     while ((ch = stream.read()) > 0) {
	         buffer.append((char) ch);
	     }
	     stream.close();
	     String htmlFileString = buffer.toString();
	     return HtmlTag.tokenize(htmlFileString);
	}

	public static void main(String[] args) {
		try {
			Queue<HtmlTag> q = getTagsFromHtmlFile("./test.html");
			System.out.println(isValidHtml(q));
		} catch (IOException e) {
			System.out.println(e);
		}
	}

}

