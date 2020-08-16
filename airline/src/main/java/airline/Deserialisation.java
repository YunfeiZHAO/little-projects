package airline;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Deserialisation {
    public ArrayList<HashMap<String, String>> data;

    Deserialisation(String path) {
        this.data = new ArrayList<HashMap<String, String>>();
        try {
            read_CSV(path);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    private void read_CSV(String path) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(path));
        ArrayList<String> header = null;
        //get the header
        if(scanner.hasNextLine()) {
            header = getRecordFromLine(scanner.nextLine());
        }
        while (scanner.hasNextLine()) {
            HashMap<String, String> line = new HashMap<String, String>();
            ArrayList<String> arrayLine = getRecordFromLine(scanner.nextLine());
            for(int i = 0; i < arrayLine.size(); i++) {
                line.put(header.get(i), arrayLine.get(i));
            }
            data.add(line);
        }
    }

    private ArrayList<String> getRecordFromLine(String line) {
        ArrayList<String> values = new ArrayList<String>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(",");
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        return values;
    }

    public static void main(String[] args) {
        Deserialisation d = new Deserialisation("flights_small.csv");
        System.out.println(d.data.get(0).get("CancellationCode").equals(""));

    }
}
