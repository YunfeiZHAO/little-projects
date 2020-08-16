
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Analyzer {
	/*
	 * Implement this method in Part 1
	 */
	public static List<Sentence> readFile(String filename) {
		Path file = Paths.get(filename);
		List<Sentence> sentences = new LinkedList<Sentence>();
		try (BufferedReader reader = Files.newBufferedReader(file)) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				int i = line.indexOf(" ");
				if(i != -1) {
					int score = Integer.parseInt(line.substring(0, i));
					if(score >= -2 && score <= 2 ) {
						String text = line.substring(i+1);
						Sentence s = new Sentence(score, text);
						sentences.add(s);
					}
				}
			}
			return sentences;
		} catch (IOException x) {
			System.err.format("IOException: %s%n", x);
			return sentences;
		}
	}
	
	/*
	 * Implement this method in Part 2
	 */
	public static Set<Word> allWords(List<Sentence> sentences) {
		Set<Word> words = new HashSet<Word>();
		for(Sentence s : sentences){
			int score = s.score;
			String text = s.text;
			if(!text.equals(null) && !text.equals("")) {
				String[] wl = text.split("\\s");
				for (String w : wl) {
					String nw = w.toLowerCase();
					if(nw.charAt(0)>='a' && nw.charAt(0)<='z') {
						boolean exist = false;
						Iterator<Word> it = words.iterator();
						while (it.hasNext()) {
							Word word = it.next();
							if(word.text.equals(nw)) {
								it.remove();
								word.increaseTotal(score);
								words.add(word);
								exist = true;
								break;
							}
						}
						if(exist == false) {
							Word nwd = new Word(w);
							words.add(nwd);
						}
					}
				}
			}
		}
		return words;
	}
	
	/*
	 * Implement this method in Part 3
	 */
	public static Map<String, Double> calculateScores(Set<Word> words) {
		Map<String, Double> Scores = new HashMap<String, Double>();
		words.forEach((word)->{
			if(!word.equals(null) && !word.getText().equals("")) {
				Scores.put(word.text, word.calculateScore());
			}
		});
		return Scores;
	}
	
	/*
	 * Implement this method in Part 4
	 */
	public static double calculateSentenceScore(Map<String, Double> wordScores, String sentence) {
		if(wordScores.equals(null) ||
		   wordScores.size() == 0 ||
		   sentence.equals(null) ||
		   sentence.length() == 0) {
			return 0;
		} else {
			String[] words = sentence.split("\\s");
			double score = 0;
			int size = 0;
			for(String w : words) {
				w = w.toLowerCase();
				if(w.charAt(0) >= 'a' && w.charAt(0) <= 'z') {
					if(wordScores.containsKey(w)) {
						score += wordScores.get(w);
					}
				}
				size ++;
			}
			return score/size;
		}
	}

	/*
	 * You do not need to modify this code but can use it for testing your program!
	 */
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Please specify the name of the input file");
			System.exit(0);
		}
		String filename = args[0];
		System.out.print("Please enter a sentence: ");
		Scanner in = new Scanner(System.in);
		String sentence = in.nextLine();
		in.close();
		List<Sentence> sentences = Analyzer.readFile(filename);
		Set<Word> words = Analyzer.allWords(sentences);
		Map<String, Double> wordScores = Analyzer.calculateScores(words);
		double score = Analyzer.calculateSentenceScore(wordScores, sentence);
		System.out.println("The sentiment score is " + score);
	}
	@Test
	public void test() {
		List<Sentence> l = readFile("1.txt");
		/*Set<Word> w = allWords(l);
		Map<String, Double> m = calculateScores(w);*/
		System.out.println(l);
	}


}
