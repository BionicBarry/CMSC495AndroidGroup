
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author BionicBarry
 */
public class MorseTranslate {
    
    //The Vibration API uses long arrays to determine length of vibrating alerts.  Therefore, a dot is half as much as a dash to properly reflect morse code  
    static long dot = 100;
    static long dash = 200;
    
    //Hashmap used to store and reference each letter for the merssage parser
    HashMap<String, long[]> morseMap = new HashMap<String, long[]>(); 
    
    
    // Static references for each letter of the morse alphabet
    static long[] letterA = {dot, dash};
    static long[] letterB = {dash, dot, dot, dot};
    static long[] letterC = {dash, dot, dash, dot};
    static long[] letterD = {dash, dot, dot};
    static long[] letterE = {dot};
    static long[] letterF = {dot, dot, dash, dot};
    static long[] letterG = {dash, dash, dot};
    static long[] letterH = {dot, dot, dot, dot};
    static long[] letterI = {dot, dot};
    static long[] letterJ = {dot, dash, dash, dash};
    static long[] letterK = {dash, dot, dash};
    static long[] letterL = {dot, dash, dot, dot};
    static long[] letterM = {dash, dash};
    static long[] letterN = {dash, dot};
    static long[] letterO = {dash, dash, dash};
    static long[] letterP = {dot, dash, dash, dot};
    static long[] letterQ = {dash, dash, dot, dash};
    static long[] letterR = {dot, dash, dot};
    static long[] letterS = {dot, dot, dot};
    static long[] letterT = {dash};
    static long[] letterU = {dot, dot, dash};
    static long[] letterV = {dot, dot, dot, dash};
    static long[] letterW = {dot, dash, dash};
    static long[] letterX = {dash, dot, dot, dash};
    static long[] letterY = {dash, dot, dash, dash};
    static long[] letterZ = {dash, dash, dot, dot};
    
    
                  

}
