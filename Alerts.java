package com.example.bionicbarry.smorse;
//Class that tells the device to vibrate
import android.os.Vibrator;

public class Alerts{
    //Morse Translate class
    MorseTranslate translator = new MorseTranslate();
    
    //Static vibrations patterns for urgent alerts
    final long[] SOS = translator.convertToMorse("sos");
    final long[] ATN = translator.convertToMorse("attn");
    //User Settings placed here because the Alerts class interacts with the 
    //User setting class the most
    UserSettings settings = new UserSettings();
    //Added Vibrator Object for proper functionality
    Vibrator viber = new Vibrator();
    //Flash Class
    Flash flasher = new Flash();


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
                    viber.vibrate(ATN, 0);
                    morseRelay(vib, flash, message);
                    break;
                //Emergency Message
                case 2:
                    viber.vibrate(SOS, 0);
                    morseRelay(vib, flash, message);
                    break;
                    
        }
    }

    //method used for checking user preferences for morse relay settings
    private void morseRelay(boolean vib, boolean flash, String message){

        //if vibrate user setting is active
        if(vib){
            viber.vibrate(translator.convertToMorse(message),0);
        //if flash user setting is active
        }else if(flash){
            //5-11-19 new flash class being used
            flasher.flashMessage(translator.convertToMorse(message));
        //if flash and vibrate user setting is active
        }else if (vib && flash){
            viber.vibrate(translator.convertToMorse(message),0);
            //5-11-19 new flash class being used
            flasher.flashMessage(translator.convertToMorse(message));
        //if neither morse relay user setting is active
        }else{
            return;
        }
    }
}
