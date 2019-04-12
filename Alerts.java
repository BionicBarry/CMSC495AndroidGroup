
//Class that tells the device to vibrate
public class Alerts {
    //Morse Translate class
    MorseTranslate translator = new MorseTranslate();
    
    //Static vibrations patterns for urgent alerts
    long[] SOS = translator.convertToMorse("sos");
    long[] ATN = translator.convertToMorse("attn");
    
    public Alerts(){
        
    }
    
    //switch method used for varying levels of urgency.  
    public void findUrgency(int urg, String message){
        switch(urg){
                //Standard Message
                case 0:
                    vibrate(translator.convertToMorse(message));
                    break;
                //Urgent Message
                case 1:
                    vibrate(ATN);
                    vibrate(translator.convertToMorse(message));
                    break;
                //Emergency Message
                case 2:
                    vibrate(SOS);
                    vibrate(translator.convertToMorse(message));
                    break;
                    
        }
    }
}