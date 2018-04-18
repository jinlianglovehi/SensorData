package com.huami.watch.sensordata;

import android.content.Context;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

/**
 * Created by jinliang on 18-4-18.
 */

public class SensorDataManager implements SensorListener {

    private static final String TAG = SensorDataManager.class.getSimpleName();

    public static SensorDataManager sensorDataManager ;

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
        synchronized (this) {
            String str = "X：" + values[0] + "，Y：" + values[1] + "，Z：" + values[2];
            switch (sensor){
                case Sensor.TYPE_ACCELEROMETER:
//                    View1.setText("加速度：" + str);
                    Log.i(TAG," acc:"+ DataUtils.printData(values));
                    break;
                case Sensor.TYPE_MAGNETIC_FIELD:
//                    View2.setText("磁场：" + str);
                    Log.i(TAG," mag:"+ DataUtils.printData(values));
                    break;
                case Sensor.TYPE_ORIENTATION:
//                    View3.setText("定位：" + str);
                    break;
                case Sensor.TYPE_GYROSCOPE:
//                    View4.setText("陀螺仪：" + str);
                    Log.i(TAG," gyr:"+ DataUtils.printData(values));
                    break;
                case Sensor.TYPE_LIGHT:
//                    View5.setText("光线：" + str);
                    break;
                case Sensor.TYPE_PRESSURE:
//                    View6.setText("压力：" + str);
                    break;
                case Sensor.TYPE_TEMPERATURE:
//                    View7.setText("温度：" + str);
                    break;
                case Sensor.TYPE_PROXIMITY:
//                    View8.setText("距离：" + str);
                    break;
                case Sensor.TYPE_GRAVITY:
//                    View9.setText("重力：" + str);
                    break;
                case Sensor.TYPE_LINEAR_ACCELERATION:
//                    View10.setText("线性加速度：" + str);
                    break;
                case Sensor.TYPE_ROTATION_VECTOR:
//                    View11.setText("旋转矢量：" + str);
                    break;
                default:
//                    View12.setText("NORMAL：" + str);
                    break;
            }
        }


    }

    @Override
    public void onAccuracyChanged(int sensor, int accuracy) {

    }


}
