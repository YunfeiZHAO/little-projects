package spell_check;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class spell_checker {
    static WordRecommender wr = new WordRecommender("engDictionary.txt");
    static String name = "shopping";
    static String fileToCheck = name + ".txt";
    static String outputFile = name + "_chk.txt";
    public static void main(String[] args) throws IOException {
        File file = new File(fileToCheck);
        Scanner user = new Scanner(System.in);
        Scanner input = new Scanner(file);
        FileWriter writer = new FileWriter(outputFile);
        while (input.hasNext()) {
            String w  = input.next();
            int l = w.length();
            HashSet<String> words = wr.hash_dict.get(l);
            if(words!=null && !words.contains(w)) {
                System.out.println("The word \"" + w + "\" is misspelled.");
                System.out.println("The following suggestions are available");
                ArrayList<String> candidate = wr.getWordSuggestions(w,3, 0.5, 5);
                System.out.println(wr.prettyPrint(candidate));
                System.out.println("Press ‘r’ for replace, ‘a’ for accept as is, ‘t’ for type in manually.");
                String operation = user.nextLine();
                while(!operation.equals("r") && !operation.equals("a") && !operation.equals("t")) {
                    System.out.println("please try again!");
                    operation = user.nextLine();
                }
                if(operation.equals("r")) {
                    System.out.println("Your word will now be replaced with one of the suggestions\n" +
                            "Enter the number corresponding to the word that you want to use for replacement.");
                    int num = Integer.parseInt(user.nextLine());
                    w = candidate.get(num - 1);
                }  else if(operation.equals("t")) {
                    System.out.println("you can write here:");
                    w = user.nextLine();
                }
            }
            writer.write(w + " ");
        }
        input.close();
        writer.close();
    }
}
