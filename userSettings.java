
//Class used to store user setting pertaining to the morse relay function
public class userSettings {
    private boolean vibrateMorse;
    private boolean flashMorse;
    
    public userSettings(){
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
