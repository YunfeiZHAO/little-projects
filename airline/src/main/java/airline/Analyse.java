package airline;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Analyse {
    public ArrayList<HashMap<String, String>> data;
    public ArrayList<HashMap<String, String>> cancelledFlight;
    public FormattedOutput output = new FormattedOutput();
    Analyse() {
        data = new Deserialisation("flights.csv").data;
        cancelledFlight = new ArrayList<HashMap<String, String>>();
        removeCancelledFlight();

    }

    public void removeCancelledFlight() {
        Iterator<HashMap<String, String>> it = data.iterator();
        while(it.hasNext()) {
            HashMap<String, String> row = it.next();
            if(row.get("Cancelled").equals("1")) {
                this.cancelledFlight.add(row);
                it.remove();
            }
        }
    }

    public void removeErroneousData() {
        Iterator<HashMap<String, String>> it = data.iterator();
        while(it.hasNext()) {
            HashMap<String, String> row = it.next();
            if(row.get("Cancelled").equals("0")) {
                for(Map.Entry<String, String> map : row.entrySet()) {
                    if(map.getKey().equals("CancellationCode") && !map.getValue().equals("") || !map.getKey().equals("CancellationCode") && map.getValue().equals("")) {
                        it.remove();
                        break;
                    }
                }

            }
        }
    }

    public HashMap<String, Integer> getStastic(ArrayList<HashMap<String, String>> data, String name) {
        HashMap<String, Integer> count = new HashMap<String, Integer>();
        Iterator<HashMap<String, String>> it = data.iterator();
        while(it.hasNext()) {
            HashMap<String, String> row = it.next();
            String type = row.get(name);
            if(count.containsKey(type)) {
                count.put(type, count.get(type) + 1);
            } else {
                count.put(type, 1);
            }
        }
        return count;
    }

    public HashMap<String, Integer> aggregate(ArrayList<HashMap<String, String>> data, String name, String valueName) {
        HashMap<String, Integer> count = new HashMap<String, Integer>();
        Iterator<HashMap<String, String>> it = data.iterator();
        while(it.hasNext()) {
            HashMap<String, String> row = it.next();
            String type = row.get(name);
            if(count.containsKey(type)) {
                count.put(type, count.get(type) + Integer.valueOf(row.get(valueName)));
            } else {
                count.put(type, Integer.valueOf(row.get(valueName)));
            }
        }
        return count;
    }

    public void q1() {
        HashMap<String, Integer> countCarrierUncancelled = getStastic(this.data, "UniqueCarrier");
        HashMap<String, Integer> countCarrierCancelled = getStastic(this.cancelledFlight, "UniqueCarrier");
        HashMap<String, Double> rate = new HashMap<String, Double>();
        for(Map.Entry<String, Integer> map : countCarrierUncancelled.entrySet()) {
            String key = map.getKey();
            Integer n = map.getValue();
            if(countCarrierCancelled.get(key) == null) {
                rate.put(key, 0.0);
            } else {
                rate.put(key, (double) countCarrierCancelled.get(key)/(countCarrierCancelled.get(key) + n));
            }
        }
        double rmax = 0;
        String key = new String();
        for(Map.Entry<String, Double> map : rate.entrySet()) {
            double r = map.getValue();
            if(r > rmax) {
                rmax = r;
                key = map.getKey();
            }
        }
        output.addAnswer(1, key + "," + rmax*100 + "%");
    }

    public void q2() {
        HashMap<String, Integer> countCancelledCode = getStastic(this.cancelledFlight, "CancellationCode");
        int rmax = 0;
        String key = new String();
        for(Map.Entry<String, Integer> map : countCancelledCode.entrySet()) {
           int r = map.getValue();
            if(r > rmax) {
                rmax = r;
                key = map.getKey();
            }
        }
        output.addAnswer(2, key);
    }

    public void q3() {
        HashMap<String, Integer> dist = aggregate(this.data, "TailNum", "Distance");
        int rmax = 0;
        String key = new String();
        for(Map.Entry<String, Integer> map : dist.entrySet()) {
            int r = map.getValue();
            if(r > rmax) {
                rmax = r;
                key = map.getKey();
            }
        }
        output.addAnswer(3, key);
    }

    public void q4() {
        HashMap<String, Integer> out = getStastic(this.data, "OriginAirportID");
        HashMap<String, Integer> in = getStastic(this.data, "DestAirportID");
        for(Map.Entry<String, Integer> map : in.entrySet()) {
            String key = map.getKey();
            Integer n = map.getValue();
            if(out.get(key) == null) {
                out.put(key, 0);
            } else {
                out.put(key, out.get(key) + n);
            }
        }
        String key = new String();
        int rmax = 0;
        for(Map.Entry<String, Integer> map : out.entrySet()) {
            int r = map.getValue();
            if(r > rmax) {
                rmax = r;
                key = map.getKey();
            }
        }
        output.addAnswer(4, key);
    }

    public void q5() {
        HashMap<String, Integer> out = getStastic(this.data, "OriginAirportID");
        HashMap<String, Integer> in = getStastic(this.data, "DestAirportID");
        for(Map.Entry<String, Integer> map : in.entrySet()) {
            String key = map.getKey();
            Integer n = map.getValue();
            if(out.get(key) == null) {
                out.put(key, -n);
            } else {
                out.put(key, out.get(key) - n);
            }
        }
        String key = new String();
        int rmax = 0;
        for(Map.Entry<String, Integer> map : out.entrySet()) {
            int r = map.getValue();
            if(r > rmax) {
                rmax = r;
                key = map.getKey();
            }
        }
        output.addAnswer(5, key);
    }

    public void q6() {
        HashMap<String, Integer> out = getStastic(this.data, "OriginAirportID");
        HashMap<String, Integer> in = getStastic(this.data, "DestAirportID");
        for(Map.Entry<String, Integer> map : out.entrySet()) {
            String key = map.getKey();
            Integer n = map.getValue();
            if(in.get(key) == null) {
                in.put(key, -n);
            } else {
                in.put(key, in.get(key) - n);
            }
        }
        String key = new String();
        int rmax = 0;
        for(Map.Entry<String, Integer> map : in.entrySet()) {
            int r = map.getValue();
            if(r > rmax) {
                rmax = r;
                key = map.getKey();
            }
        }
        output.addAnswer(6, key);
    }

    public  void q7() {
        Iterator<HashMap<String, String>> it = data.iterator();
        int count = 0;
        while(it.hasNext()) {
            HashMap<String, String> row = it.next();
            if(row.get("UniqueCarrier").equals("AA") &&
                    (Integer.valueOf(row.get("DepDelay")) >= 60 ||
                            (Integer.valueOf(row.get("ArrDelay")) >= 60))){
                count += 1;
            }
        }
        output.addAnswer(7, count);
    }

    public  void q8() {
        Iterator<HashMap<String, String>> it = data.iterator();
        int count = 0;
        int index = 0;
        int delay = 0;
        while(it.hasNext()) {
            HashMap<String, String> row = it.next();
            if(Integer.valueOf(row.get("ArrDelay")) <= 0 &&
                    Integer.valueOf(row.get("DepDelay"))> delay){
                delay = Math.abs(Integer.valueOf(row.get("DepDelay")));
                index = count;
            }
            count += 1;
        }
        HashMap<String, String> p = data.get(index);
        output.addAnswer(8, p.get("DayofMonth") + "," + p.get("DepDelay") + "," + p.get("TailNum"));
    }

    public static void main(String[] args) {
        Analyse a = new Analyse();
        a.removeCancelledFlight();
        a.removeErroneousData();
        a.q1();
        a.q2();
        a.q3();
        a.q4();
        a.q5();
        a.q6();
        a.q7();
        a.q8();
        a.output.addAnswer(9, "This ia the answer of no question");
        System.out.println(a.output.answers);
        a.output.writeAnswers();
    }
}
