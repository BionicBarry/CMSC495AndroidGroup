import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author BionicBarry
 */
public class MorseTranslate {
    
    //The Vibration API uses long arrays to determine length of vibrating alerts.  Therefore, a dot is half as much as a dash to properly reflect morse code  
    static long DOT = 100;
    static long DASH = 200;
    static long DELAY = 50;
    
    //Hashmap used to store and reference each letter for the merssage parser
    HashMap<String, long[]> morseMap = new HashMap<String, long[]>(); 
    
    
    // Static references for each letter of the morse alphabet
    static long[] letterA = {DOT, DASH};
    static long[] letterB = {DASH, DOT, DOT, DOT};
    static long[] letterC = {DASH, DOT, DASH, DOT};
    static long[] letterD = {DASH, DOT, DOT};
    static long[] letterE = {DOT};
    static long[] letterF = {DOT, DOT, DASH, DOT};
    static long[] letterG = {DASH, DASH, DOT};
    static long[] letterH = {DOT, DOT, DOT, DOT};
    static long[] letterI = {DOT, DOT};
    static long[] letterJ = {DOT, DASH, DASH, DASH};
    static long[] letterK = {DASH, DOT, DASH};
    static long[] letterL = {DOT, DASH, DOT, DOT};
    static long[] letterM = {DASH, DASH};
    static long[] letterN = {DASH, DOT};
    static long[] letterO = {DASH, DASH, DASH};
    static long[] letterP = {DOT, DASH, DASH, DOT};
    static long[] letterQ = {DASH, DASH, DOT, DASH};
    static long[] letterR = {DOT, DASH, DOT};
    static long[] letterS = {DOT, DOT, DOT};
    static long[] letterT = {DASH};
    static long[] letterU = {DOT, DOT, DASH};
    static long[] letterV = {DOT, DOT, DOT, DASH};
    static long[] letterW = {DOT, DASH, DASH};
    static long[] letterX = {DASH, DOT, DOT, DASH};
    static long[] letterY = {DASH, DOT, DASH, DASH};
    static long[] letterZ = {DASH, DASH, DOT, DOT};
    //Numbers
    static long[] number1 ={DOT, DASH, DASH, DASH, DASH};
    static long[] number2 ={DOT, DOT, DASH, DASH, DASH};
    static long[] number3 ={DOT, DOT, DOT, DASH, DASH};
    static long[] number4 ={DOT, DOT, DOT, DOT, DASH};
    static long[] number5 ={DOT, DOT, DOT, DOT, DOT};
    static long[] number6 ={DASH, DOT, DOT, DOT, DOT};
    static long[] number7 ={DASH, DASH, DOT, DOT, DOT};
    static long[] number8 ={DASH, DASH, DASH, DOT, DOT};
    static long[] number9 ={DASH, DASH, DASH, DASH, DOT};
    static long[] number0 ={DASH, DASH, DASH, DASH, DASH};
    
    //Void method to populate hashmap using characters as the key in reference to the long arrays of morse
    void populateMap(){
        this.morseMap.put("a", letterA);
        this.morseMap.put("b", letterB);
        this.morseMap.put("c", letterC);
        this.morseMap.put("d", letterD);
        this.morseMap.put("e", letterE);
        this.morseMap.put("f", letterF);
        this.morseMap.put("g", letterG);
        this.morseMap.put("h", letterH);
        this.morseMap.put("i", letterI);
        this.morseMap.put("j", letterJ);
        this.morseMap.put("k", letterK);
        this.morseMap.put("l", letterL);
        this.morseMap.put("m", letterM);
        this.morseMap.put("n", letterN);
        this.morseMap.put("o", letterO);
        this.morseMap.put("p", letterP);
        this.morseMap.put("q", letterQ);
        this.morseMap.put("r", letterR);
        this.morseMap.put("s", letterS);
        this.morseMap.put("t", letterT);
        this.morseMap.put("u", letterU);
        this.morseMap.put("v", letterV);
        this.morseMap.put("w", letterW);
        this.morseMap.put("x", letterX);
        this.morseMap.put("y", letterY);
        this.morseMap.put("z", letterZ);
        this.morseMap.put("1", number1);
        this.morseMap.put("2", number2);
        this.morseMap.put("3", number3);
        this.morseMap.put("4", number4);
        this.morseMap.put("5", number5);
        this.morseMap.put("6", number6);
        this.morseMap.put("7", number7);
        this.morseMap.put("8", number8);
        this.morseMap.put("9", number9);
        this.morseMap.put("0", number0);
     
    }
    
    public MorseTranslate(){
        this.populateMap();
    }
    
    //Method used to parse the string from the sender into morse code
    private ArrayList<long[]> parseMessage(String input){
        
        //String being tokenized into a character array for easy parsing
        char[] tokenizedInput = input.toCharArray();
        //Array List containing the morse values of the String
        ArrayList<long[]> morseMessage = new ArrayList<long[]>();
        
        //For loop parsing through the character array
        for (int i = 0; i > tokenizedInput.length; i++){
            //parser finds the long array according to the key of the character.  
            morseMessage.add(morseMap.get(tokenizedInput[i]));
        }
        
        //returning the completed long Array
        return morseMessage;
    }
    
    public long[] convertToMorse(String input){
        //Final Morse message array
        //4-13-19 ----- I changed the name of the variable to 'morseValues' because the same method can be used
        // for the light, since they both use long arrays for activation intervals  
        long[] morseValues; 
        //PlaceHolder Array List
        ArrayList<long[]> coolVibes = parseMessage(input);
        
        //for loop looping throuigh the array list
        for(int i = 0; i < coolVibes.size(); i++){
            //Index for each individual long array letter
            int index = 0;
            //Nested For Loop for getting the main long array populated with the morse message
            for(int lon = 0; lon < coolVibes.get(i).length; lon++){
                morseValues[index] = coolVibes.get(i)[lon];
                index++;
            }
        }
        return morseValues;
    }
}
