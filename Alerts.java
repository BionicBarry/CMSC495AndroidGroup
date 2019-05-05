package com.example.samplechatapplication.utils;

import android.content.Context;

public class Alerts {
    //Morse Translate class
    MorseTranslate translator = new MorseTranslate();

    //Static vibrations patterns for urgent alerts
    final long[] SOS = translator.convertToMorse("sos");
    final long[] ATN = translator.convertToMorse("attn");
    //User Settings placed here because the Alerts class interacts with the
    //User setting class the most
    Context mContext;

    public Alerts(Context mContext) {
        this.mContext = mContext;
    }

    //switch method used for varying levels of urgency.
    public void findUrgency(int urg, String message) {
        //Changed the boolean values for the flash and vibrate morse relays
        //to avoid encapsulation, and centralize the user settings
        boolean vib = SharePreferenceData.INSTANCE.getBooleanPreference(mContext, UtilClass.INSTANCE.getSWITCH_VIBRATION(), false);
        boolean flash = SharePreferenceData.INSTANCE.getBooleanPreference(mContext, UtilClass.INSTANCE.getSWITCH_FLAG(), false);

        switch (urg) {
            //Standard Message
            case 0:
                morseRelay(vib, flash, "");
                break;
            //Urgent Message
            case 1:
                // UtilClass.INSTANCE.vibrateNow(mContext,ATN);//vibrate(ATN);
                morseRelay(vib, flash, "sos");
                break;
            //Emergency Message
            case 2:
                // UtilClass.INSTANCE.vibrateNow(mContext,SOS);//vibrate(SOS);
                morseRelay(vib, flash, "attn");
                break;

        }
    }

    //method used for checking user preferences for morse relay settings
    private void morseRelay(boolean vib, boolean flash, String message) {

        //if vibrate user setting is active
        if (vib && flash) {
            UtilClass.INSTANCE.vibrateNow(mContext, translator.convertToMorse(message));
            UtilClass.INSTANCE.flashLightPattern(mContext,translator.convertToMorseTorch(message));
            //if neither morse relay user setting is active
        } else if (vib) {
            UtilClass.INSTANCE.vibrateNow(mContext, translator.convertToMorse(message));//vibrate(translator.convertToMorse(message));
            //if flash user setting is active
        } else if (flash) {
            UtilClass.INSTANCE.flashLightPattern(mContext,translator.convertToMorseTorch(message));
            //if flash and vibrate user setting is active
        } else {
            return;
        }
    }
}
