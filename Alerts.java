

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
    public void findUrgency(int urg, String message, boolean vib, boolean flash){
        switch(urg){
                //Standard Message
                case 0:
                    morseRelay(vib, flash, message);
                    break;
                //Urgent Message
                case 1:
                    vibrate(ATN);
                    morseRelay(vib, flash, message);
                    break;
                //Emergency Message
                case 2:
                    vibrate(SOS);
                    morseRelay(vib, flash, message);
                    break;
                    
        }
    }
    
    //method used for checking user preferences for morse relay settings
    private void morseRelay(boolean vib, boolean flash, String message){
        if(vib){
            vibrate(translator.convertToMorse(message));
        }else if(flash){
            flashlight(translator.convertToMorse(message));
        }else if (vib && flash){
            vibrate(translator.convertToMorse(message));
            flashlight(translator.convertToMorse(message));
        }else{
            return;
        }
    }
}
