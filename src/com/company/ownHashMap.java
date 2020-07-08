package com.company;

import java.lang.reflect.Array;
import java.util.*;



public class ownHashMap {
    private static boolean lastSymbolCheck (String s){
        return s.charAt(s.length() - 1) == ',' || s.charAt(s.length() - 1) == '.' || s.charAt(s.length() - 1) == ':';
    }
    public static ArrayList<String> isk(String Text){
        Map<String, Integer> hashMap = new SimpleHashMap<>(10);
        Scanner sc = new Scanner(Text);
        String [] help2;
        String help;
        while (sc.hasNextLine()){
            help = sc.nextLine();
            help2 = help.split(" ");
            String helpy;
            for (int i = 0; i < help2.length-1; i++) {
                if (lastSymbolCheck(help2[i+1])){
                    if (i == (help2.length-2)){
                        helpy = (help2[i].toLowerCase()+" " +(help2[i+1].substring(0,help2[i+1].length()-1).toLowerCase()));
                        if (!(hashMap.containsKey(helpy))){
                            hashMap.put(helpy,1);
                        } else {
                            hashMap.put(helpy, hashMap.get(helpy)+1);
                        }
                        continue;
                    }
                    helpy = (help2[i].toLowerCase()+" " +(help2[i+1].substring(0,help2[i+1].length()-1).toLowerCase()));
                    if (!(hashMap.containsKey(helpy))){
                        hashMap.put(helpy,1);
                    } else {
                        hashMap.put(helpy, hashMap.get(helpy)+1);
                    }
                    i++;
                } else{
                    helpy = help2[i].toLowerCase()+" " + help2[i+1].toLowerCase();
                    if (!(hashMap.containsKey(helpy))){
                        hashMap.put(helpy,1);
                    } else {
                        hashMap.put(helpy, hashMap.get(helpy)+1);
                    }
                }
            }
        }
        int times = 0;
        ArrayList<String> helpy3 = new ArrayList<>();
        for (Map.Entry s: hashMap.entrySet()){
            if ((Integer)s.getValue()>times){
                times = (Integer) s.getValue();
            }
        }
        for (Map.Entry s: hashMap.entrySet()){
            if ((Integer)s.getValue()==times){
                helpy3.add((String) s.getKey());
            }
        }
        return helpy3;
    }
}
