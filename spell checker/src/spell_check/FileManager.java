package spell_check;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FileManager {
    private BufferedReader inFile;
    public  Map<Integer, HashSet<String>> hash_dict = new HashMap<Integer, HashSet<String>>();

    public FileManager(String path){
        try{
            inFile = new BufferedReader(new FileReader(path));
            creatHash_dict();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public BufferedReader getInFile() {
        return inFile;
    }

    private void creatHash_dict() {
        try {
            System.out.println("\ncreating dictionary");
            long startTime = System.currentTimeMillis();
            while(inFile.ready()) {
                String word = inFile.readLine();
                int len = word.length();
                if(hash_dict.containsKey(len)) {
                    HashSet<String> set = hash_dict.get(len);
                    set.add(word);
                    hash_dict.put(len, set);
                } else {
                    HashSet<String> set = new HashSet<String>();
                    set.add(word);
                    hash_dict.put(len, set);
                }
            }
            long endTime = System.currentTimeMillis();
            System.out.print("Done. Time Taken: " + (endTime - startTime) + " milliseconds\n");
            closeInFile();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void closeInFile(){
        try{
            getInFile().close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

}
