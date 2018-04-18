package com.huami.watch.sensordata;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.util.Log;

/**
 * Created by jinliang on 18-4-18.
 */

public class SensorDataManager implements SensorListener  {

    private static final String TAG = SensorDataManager.class.getSimpleName();



    private SensorListener sensorListener ;
    public static SensorDataManager sensorDataManager ;

    private Sensor mAccelerometer = null;
    private Sensor mMagnSensor = null;
    private Sensor mGravSensor = null;
    private Sensor mGyroSensor = null;


    public static SensorDataManager getInstance(Context mContext){
        if(sensorDataManager==null){
            synchronized (SensorDataManager.class){
                if(sensorDataManager==null){
                    sensorDataManager = new SensorDataManager(mContext);
                }
            }
        }
        return sensorDataManager;
    }

    public void setSensorLister(SensorListener sensorLister){
        this.sensorListener = sensorLister;
    }

    private SensorManager sm = null;
    public SensorDataManager(Context mContext) {
        sm = (SensorManager) mContext.getSystemService(Context.SENSOR_SERVICE);
    }

    /**
     *  register
     */
    public void registerSensor(){
        sm.registerListener(this,
                Sensor.TYPE_ACCELEROMETER |
                        Sensor.TYPE_MAGNETIC_FIELD |
                        Sensor.TYPE_ORIENTATION |
                        Sensor.TYPE_GYROSCOPE |
                        Sensor.TYPE_LIGHT |
                        Sensor.TYPE_PRESSURE |
                        Sensor.TYPE_TEMPERATURE |
                        Sensor.TYPE_PROXIMITY |
                        Sensor.TYPE_GRAVITY |
                        Sensor.TYPE_LINEAR_ACCELERATION |
                        Sensor.TYPE_ROTATION_VECTOR,
                SensorManager.SENSOR_DELAY_NORMAL);
    }


    public void unRegisterSensor(){
        if(sm!=null){
            sm.unregisterListener(this);
        }
    }
    @Override
    public void onSensorChanged(int sensor, float[] values) {
        this.sensorListener.onSensorChanged(sensor,values);
    }

    @Override
    public void onAccuracyChanged(int sensor, int accuracy) {
        this.sensorListener.onAccuracyChanged(sensor,accuracy);

    }


}
