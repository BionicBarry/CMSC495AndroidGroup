package com.example.SMorSe495.utils;

import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.camera2.*;
import android.hardware.Camera.*;
import java.util.*;

//Class used to adapt long morse Array for camera flashlight
public class Flash{

    //Camera
    static Camera cam;
    Camera.Parameters param;
    //boolean hasFlash = false;
    //Static Delay Variable
    private static long DELAY = 50;

    //Constructor initializing flash option if the device allows it
    public Flash(){
            cam = Camera.open();
            param = cam.getParameters();
    }

    //Function converting long array into flashes
    public void flashMessage(long[] morse){

        for(int i = 0; i < morse.length; i++){
            try {
                turnLightOn();
                Thread.sleep(morse[i]);
                turnLightOff();
                Thread.sleep(DELAY);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //Private method for turning on the light
    private void turnLightOn(){
        this.param.setFlashMode(Parameters.FLASH_MODE_TORCH);
        cam.setParameters(param);
        cam.startPreview();
    }

    //private method for turning off the light
    private void turnLightOff(){
        cam.stopPreview();
        cam.release();
        cam = null;
    }
}
