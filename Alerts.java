//Class that tells the device to vibrate
public class Alerts {
    //Morse Translate class
    MorseTranslate translator = new MorseTranslate();
    
    //Static vibrations patterns for urgent alerts
    final long[] SOS = translator.convertToMorse("sos");
    final long[] ATN = translator.convertToMorse("attn");
    //User Settings placed here because the Alerts class interacts with the 
    //User setting class the most
    UserSettings settings = new UserSettings();
    
    public Alerts(){
        
    }
    
    //switch method used for varying levels of urgency.  
    public void findUrgency(int urg, String message){
        //Changed the boolean values for the flash and vibrate morse relays
        //to avoid encapsulation, and centralize the user settings
        boolean vib = settings.getFlash();
        boolean flash = settings.getFlash();
        
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
        
        //if vibrate user setting is active
        if(vib){
            vibrate(translator.convertToMorse(message));
        //if flash user setting is active
        }else if(flash){
            flashlight(translator.convertToMorse(message));
        //if flash and vibrate user setting is active
        }else if (vib && flash){
            vibrate(translator.convertToMorse(message));
            flashlight(translator.convertToMorse(message));
        //if neither morse relay user setting is active
        }else{
            return;
        }
    }
}
