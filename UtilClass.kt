package com.example.samplechatapplication.utils

import android.content.Context
import android.widget.Toast
import android.content.pm.PackageManager
import android.hardware.Camera
import android.os.Handler
import android.support.v4.content.ContextCompat.getSystemService
import android.os.Vibrator
import java.util.*
import kotlin.concurrent.thread


object UtilClass {
    private val DELAY_MS:Long=400
    private val PERIOD_MS:Long=400
    private var mTimerIntro: Timer?=null
    val SWITCH_FLAG = "SWITCH_FLAG"
    val SWITCH_VIBRATION = "SWITCH_VIBRATION"


    fun flashLightPattern(context: Context, listArray: ArrayList<Int>) {
        /* for (x in 0 until listArray.size) {
             if (listArray[x]==0) {
                 Handler().postDelayed({ turnOnFlashLight(context)},300)

             }


         }*/
        var i=0
        try {
            mTimerIntro = Timer()
            mTimerIntro?.schedule(object : TimerTask() {

                override fun run() {
                    try {
                        if (listArray[i] == 0) {
                            turnOnFlashLight(context)
                        } else {
                            turnOffFlashLight(context)
                        }
                    }catch (e:java.lang.Exception){
                        mTimerIntro?.cancel()
                        e.printStackTrace()
                    }
                    i++
                }


            }, DELAY_MS,PERIOD_MS )

        } catch (e: Exception) {
            e.printStackTrace()
        }
       /* turnOnFlashLight(context)
        val tread=Handler().postDelayed({
            turnOffFlashLight(context)
        }, 300)*/


    }

    var cam: Camera? = null
    fun turnOnFlashLight(context: Context) {
        try {
            if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
                cam = Camera.open()
                val p = cam!!.getParameters()
                p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH)
                cam?.setParameters(p)
                cam?.startPreview()
            }

        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "Exception throws in turning on flashlight.", Toast.LENGTH_SHORT).show()
        }

    }

    fun turnOffFlashLight(context: Context) {
        try {
            if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
                cam?.stopPreview()
                cam?.release()
                cam = null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "Exception throws in turning off flashlight.", Toast.LENGTH_SHORT).show()
        }

    }

    var vibr: Vibrator? = null
    fun vibrateNow(context: Context, long: LongArray) {
        vibr = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator?

        vibr?.vibrate(500) // Vibrate for half a second (500 milli seconds)

        vibr?.vibrate(long, -1)
    }

    fun vibrationCancel() {
        vibr?.cancel()
    }
}