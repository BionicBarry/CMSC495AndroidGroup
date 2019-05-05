package com.example.samplechatapplication.utils;

import java.util.ArrayList;

class MorseTranslate {
    int dot = 200; // Length of a Morse Code "dot" in milliseconds
    int dash = 500; // Length of a Morse Code "dash" in milliseconds
    int short_gap = 200; // Length of Gap Between dots/dashes
    int medium_gap = 500; // Length of Gap Between Letters
    int long_gap = 1000; // Length of Gap Between Words

   public long[] convertToMorse(String s){
       long[] longs = new long[0];
       if(s.equals("attn")){
           return new long[]{400, 200};
       }else if(s.equals("sos")){
            return new long[]{dot, short_gap, dot, short_gap, dot, // s
                    medium_gap, dash, short_gap, dash, short_gap, dash, // o
                    medium_gap, dot, short_gap, dot, short_gap, dot, // s
                    long_gap};
       }
       return longs;
   }

   public ArrayList<Integer> convertToMorseTorch(String s){
       ArrayList<Integer> arrayList=new ArrayList<>();
       if(s.equals("attn")){
           arrayList.add(0);
           arrayList.add(1);
           arrayList.add(0);
           arrayList.add(1);
       }else if(s.equals("sos")){
           arrayList.add(0);
           arrayList.add(1);
           arrayList.add(0);
           arrayList.add(1);
           arrayList.add(0);
           arrayList.add(1);
           arrayList.add(0);
           arrayList.add(1);
           arrayList.add(0);
           arrayList.add(1);
           arrayList.add(0);
           arrayList.add(1);
           arrayList.add(0);
           arrayList.add(1);
           arrayList.add(0);
           arrayList.add(1);
       }else{
           arrayList.add(0);
           arrayList.add(1);
       }
       return arrayList;
   }
}