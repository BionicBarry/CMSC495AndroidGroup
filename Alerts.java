package com.example.SMorSe495.utils;

import android.content.Context;
import android.os.Vibrator;
public class Alerts {
    //Morse Translate class
    MorseTranslate translator;
    Flash flasher;

    //Static vibrations patterns for urgent alerts
    final long[] SOS = translator.convertToMorse("sos");
    final long[] ATN = translator.convertToMorse("attn");
    //User Settings placed here because the Alerts class interacts with the
    //User setting class the most
    Context mContext;
    Vibrator viber;

    public Alerts(Context mContext) {
        this.mContext = mContext;
        this.translator = new MorseTranslate();
        this.flasher = new Flash();
    }

    //switch method used for varying levels of urgency.
    public void findUrgency(int urg, String message) {
        //Changed the boolean values for the flash and vibrate morse relays
        //to avoid encapsulation, and centralize the user settings
        boolean vib = SharePreferenceData.INSTANCE.getBooleanPreference(mContext, UtilClass.INSTANCE.getSWITCH_VIBRATION(), false);
        boolean flash = SharePreferenceData.INSTANCE.getBooleanPreference(mContext, UtilClass.INSTANCE.getSWITCH_FLAG(), false);

        switch (urg) {
            //Standard Message
            default:
                morseRelay(vib, flash, message);
                break;
            //Urgent Message
            case 1:
                viber.vibrate(ATN, -1);
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
    private void morseRelay(boolean vib, boolean flash, String message) {

        //if vibrate user setting is active
        if (vib && flash) {
            viber.vibrate(translator.convertToMorse(message),-1);
            flasher.flashMessage(translator.convertToMorse(message));
            //if neither morse relay user setting is active
        } else if (vib) {
            viber.vibrate(translator.convertToMorse(message),-1);
            //if flash user setting is active
        } else if (flash) {
            flasher.flashMessage(translator.convertToMorse(message));
            //if flash and vibrate user setting is active
        } else {
            return;
        }
    }
}
