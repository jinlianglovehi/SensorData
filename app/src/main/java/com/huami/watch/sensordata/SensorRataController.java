package com.huami.watch.sensordata;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.util.Log;

/**
 * Created by jinliang on 18-4-18.
 */

public class SensorRataController implements SensorEventListener {

    public static SensorRataController instance ;

    public static SensorManager sm ;
    private SensorEventListener sensorEventListener;
    private Sensor mAccelerometer = null;
    private Sensor mMagnSensor = null;
    private Sensor mGravSensor = null;
    private Sensor mGyroSensor = null;


    private int mRate = SensorManager.SENSOR_DELAY_GAME;
    public SensorRataController(Context mContext) {
        sm = (SensorManager) mContext.getSystemService(Context.SENSOR_SERVICE);
    }

    public void registerWithRata(){

        mGravSensor = sm.getDefaultSensor(Sensor.TYPE_GRAVITY);
        try {
            Log.d("sensor",mGravSensor.toString());
        } catch (NullPointerException e) {
            Log.d("sensor","not found " + "TYPE_GRAVITY");
        }

        mMagnSensor = sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        try {
            Log.d("sensor",mMagnSensor.toString());
        } catch (NullPointerException e) {
            Log.d("sensor","not found " + "TYPE_MAGNETIC_FIELD");
        }
        mAccelerometer = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        try {
            Log.d("sensor",mAccelerometer.toString());
        } catch (NullPointerException e) {
            Log.d("sensor","not found " + "TYPE_ACCELEROMETER");
        }
        mGyroSensor = sm.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        try {
            Log.d("sensor",mGyroSensor.toString());
        } catch (NullPointerException e) {
            Log.d("sensor","not found " + "TYPE_GYROSCOPE");
        }

        // register

        sm.registerListener(this,mAccelerometer, mRate);

        sm.registerListener(this, mMagnSensor, mRate);
        sm.registerListener(this, mGravSensor, mRate);
        sm.registerListener(this, mGyroSensor, mRate);

    }

    public void setSensorLister(SensorEventListener sensorLister){
        this.sensorEventListener = sensorLister;
    }
    public void unRegisterSensor(){
        if(sm!=null){
            sm.unregisterListener(this);
        }
    }


    public static SensorRataController getInstance(Context mContext){

        if(instance==null){
            synchronized (SensorRataController.class){
                if(instance==null){
                    instance =new SensorRataController(mContext);
                }
            }
        }
        return instance ;
    }



    @Override
    public void onSensorChanged(SensorEvent event) {
        this.sensorEventListener.onSensorChanged(event);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        this.sensorEventListener.onAccuracyChanged(sensor,accuracy);

    }
}
