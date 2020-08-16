package airline;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Validation tests for HW5
 * Ensure your answers are stored in a file called answers.txt
 * See the provided .txt document for example formatting
 * @author brianhardy
 *
 */

public class ValidationTest {

	static ArrayList<String> answers;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		answers = new ArrayList<>();
		File inputFile = new File("answers.txt");

		// Reads in the answers from answers.txt
		try( Scanner in = new Scanner(inputFile) ){

			while(in.hasNextLine()) {
				answers.add(in.nextLine());
			}

		} catch (IOException e ) {
			e.printStackTrace();
			System.out.println("Check that your file is being written properly!");
		}

	}

	@Test
	public void allQuestionsAnsweredTest() {
		assertEquals( 9, answers.size(),  "Check that you are answering all 9 questions!" );
	}

	@Test
	public void questionOneCancelledFlights() {
		String answer = answers.get(0);
		String carrier = answer.split(",")[0];
		Double percent = Double.parseDouble( answer.split(",")[1].substring(0, 4) );	// Answer to the nearest 0.1 %
		
		assertEquals( "AA", carrier.toUpperCase(),  "Carrier is wrong, should be AA");
		assertEquals( 1.29, percent,  0.1, "Percent cancelled is wrong; should be around 1.29%");
	}
	
	@Test
	public void questionTwoMostCommonCancellation() {
		String answer = answers.get(1);
		assertEquals( "B", answer.toUpperCase(),  "Cancellation code is wrong, should be 'B'");
	}
	
	@Test
	public void questionThreeFurthestTailNum() {
		String answer = answers.get(2);
		assertEquals( "N789AA", answer.toUpperCase(), "Not the correct tail number.  Was expected N789AA");
	}
	
	@Test
	public void questionFourBusiestAirport() {
		String answer = answers.get(3);
		assertEquals( 11292, Integer.parseInt(answer),  "Incorrect busiest airport; Expected 11292");
	}
	
	@Test
	public void questionFiveSource() {
		String answer = answers.get(4);
		assertEquals( 11292, Integer.parseInt(answer),  "Incorrect airport; Expected 11292");
	}
	
	@Test
	public void questionSixSink() {
		String answer = answers.get(5);
		assertEquals( 13232, Integer.parseInt(answer),  "Incorrect airport; Expected 13232");
	}
	
	@Test
	public void questionSevenDelays() {
		String answer = answers.get(6);
		assertEquals( 6, Integer.parseInt(answer),  "Incorrect answer; Expected 6");
	}
	
	@Test
	public void questionEightMadeupDelay() {
		String answer = answers.get(7);
		String parsed[] = answer.split(",");
		assertEquals( 10, Integer.parseInt(parsed[0]), "Expected the day of month to be 10" );
		assertEquals( 27, Integer.parseInt(parsed[1]),  "Expected the departure delay to be 27");
		assertEquals( "N7843A", parsed[2].toUpperCase(),  "Expected the tail number to be N7843A");
	}

}
