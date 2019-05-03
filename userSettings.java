
//Class used to store user setting pertaining to the morse relay function
public class UserSettings {
    private boolean vibrateMorse;
    private boolean flashMorse;
    
    //Both of these setting determine which device on the phone will relay the
    //SMS message in morse code.  The choices are the flash light, vibrator,
    //Both, or neither.  
    public UserSettings(){
        this.vibrateMorse = true;
        this.flashMorse = true;
    }
    
    //toggle vibrate option
    public void toggleVibrate(boolean t){
        this.vibrateMorse = t;
    }
    //toggle flash
    public void toggleFlash(boolean t){
        this.flashMorse = t;
    }
    //get vibrate
    public boolean getVibrate(){
        return this.vibrateMorse;
    }
    //get flash
    public boolean getFlash(){
        return this.flashMorse;
    }
    
}
