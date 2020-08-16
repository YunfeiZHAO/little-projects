package airline;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Class that is used for validation testing and grading
 * Pass answers to each question into this Class
 * The bundled JUnit tests will use the output from this Class to check answers
 * Don't forget to call writeAnswers() at the end!
 * @author brianhardy
 *
 */
public class FormattedOutput {

	// ArrayList for holding the answers to each question.  Each index corresponds to question number minus 1
	// i.e. answers.get(2) will correspond to Question 3
	public ArrayList<String> answers;
	private int numQuestions = 9;
	
	public FormattedOutput() {
		
		answers = new ArrayList<>();
		
		// Initializes the answers ArrayList with null Strings
		for( int ii = 0; ii < numQuestions; ii++) {
			answers.add("No answer given");
		}
	}
	
	
	/**
	 * Method for adding an answer to each question 
	 * @param question The Question number (1, 2, 3...)
	 * @param answer The answer
	 */
	public void addAnswer(int question, String answer ) {
		
		// Catches question numbers outside of the allowable range
		if( question < 0 || question > numQuestions ) {
			throw new IllegalArgumentException("Invalid question number, please try again" );
		}
		
		answers.set(question - 1, answer);
	}
	
	/**
	 * Method for adding an answer to each question
	 * @param question the Question number (1, 2, 3...)
	 * @param answer the answer
	 */
	public void addAnswer(int question, int answer) {
		addAnswer(question, Integer.toString(answer) );
	}
	
	
	
	/**
	 * Writes a .txt of the answers
	 */
	public void writeAnswers(){
		File out = new File("answers.txt");
		
		try( PrintWriter pw = new PrintWriter(out) ) {
			
			// Prints each line in answers
			for( String s : answers ) {
				pw.println(s);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Could not write the File out.  Check permissions, or contact course staff for help");
		}
	}

}
