package spell_check;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

public class WordRecommender {
    Map<Integer, HashSet<String>> hash_dict;

    public WordRecommender(String fileName) {
        FileManager fm = new FileManager("engDictionary.txt");
        this.hash_dict = fm.hash_dict;
    }
    /**
     * Function 1
     * */
    public double getSimilarityMetric(String word1, String word2) {
        int rightSimilarity = 0;
        int leftSimilarity = 0;
        int len1 = word1.length();
        int len2 = word2.length();
        int len = Math.min(len1, len2);
        for(int i = 0; i < len; i++) {
            if(word1.charAt(i) == word2.charAt(i)) {
                rightSimilarity++;}
            if(word1.charAt(len1 - i -1) == word2.charAt(len2 - i - 1)) {
                leftSimilarity++;}
        }
        return  (rightSimilarity + leftSimilarity)/2.0;
    }

    private static double calculateCommonPercent(String word1, String word2) {
        HashSet<Character> S_inter = new HashSet<Character>();
        HashSet<Character> S_uni = new HashSet<Character>();
        for(int i = 0; i < word1.length(); i++) {
            char c = word1.charAt(i);
            if(word2.indexOf(c) != -1) {
                S_inter.add(word1.charAt(i));
            }
            S_uni.add(c);
        }
        for(int i = 0; i < word2.length(); i++) {
            S_uni.add(word2.charAt(i));
        }
        return ((double)S_inter.size())/S_uni.size();
     }
    /**
     * Function 2
     * */
    public ArrayList<String> getWordSuggestions(String word, int n, double commonPercent, int topN) {
        int len = word.length();
        HashSet<String> candidatesL = new HashSet<String>();
        ArrayList<String> candidateF = new ArrayList<String>();
        for(int l = len - n; l <= len + n; l++) {
            if(hash_dict.get(l) != null) {
                candidatesL.addAll(hash_dict.get(l));}
        }
        for(String s : candidatesL) {
            if(calculateCommonPercent(word, s) >= commonPercent) {
                candidateF.add(s);
            }
        }
        boolean key;
        do{
          key = false;
          for(int i = 0; i < candidateF.size() - 1; i++) {
              if(getSimilarityMetric(candidateF.get(i), word) < getSimilarityMetric(candidateF.get(i + 1), word)) {
                  String S = candidateF.get(i);
                  candidateF.set(i, candidateF.get(i + 1));
                  candidateF.set(i + 1, S);
                  key = true;
              }
          }
        } while(key);
        if(candidateF.size() < topN) {
            return candidateF;
        } else {
            return new ArrayList<String>(candidateF.subList(0, topN));
        }
    }
    /**
     * Function 3
     * */
    public ArrayList<String> getWordsWithCommonLetters(String word, ArrayList<String> listOfWords, int n) {
        HashSet<Character> w_hash = new HashSet<Character>();
        ArrayList<String> wc = new ArrayList<String>();
        for(int i = 0; i < word.length(); i++) {
            w_hash.add(word.charAt(i));
        }
        for(String w : listOfWords) {
            HashSet<Character> w_h = new HashSet<Character>();
            for(int i = 0; i < w.length(); i++) {
                w_h.add(w.charAt(i));
            }
            int common = 0;
            for (char c : w_h) {
                if (w_hash.contains(c)) {
                    common++;
                }
            }
            if(common >= n) {
                wc.add(w);
            }
        }
        return wc;
    }
    /**
     * Function 4
     * */
    public String prettyPrint(ArrayList<String> list) {
        String output = new String();
        int i = 1;
        for(String s : list) {
            output = output + String.valueOf(i) + ".\t" + s + "\n";
            i++;
        }
        return output;
    }
}
