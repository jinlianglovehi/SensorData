package com.huami.watch.sensordata;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by jinliang on 18-4-18.
 *  第二种解决办法
 */
public class SensorRataActivity extends Activity implements SensorEventListener {

    private static final String TAG = SensorRataActivity.class.getSimpleName();
    private SensorRataController controller ;
    private TextView dataAccView,dataMagView,dataGyrView ;
     float[]  mAcceData ;
     float[] magData;
     float[] gyrData;

     private Handler handler ;
     private Runnable runnable ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controller = SensorRataController.getInstance(this);
        controller.setSensorLister(this);
        controller.registerWithRata();

        dataAccView = (TextView) findViewById(R.id.data_acc);
        dataMagView =(TextView)findViewById(R.id.data_mag) ;
        dataGyrView = (TextView)findViewById(R.id.data_gyr);

        handler=new Handler();

        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(magData!=null && magData.length>0){
                            dataAccView.setText(DataUtils.printData(mAcceData));
                        }
                        if(magData!= null && magData.length>0){
                            dataMagView.setText(DataUtils.printData(magData));
                        }
                        if(gyrData!=null && gyrData.length>0){
                            dataGyrView.setText(DataUtils.printData(gyrData));
                        }
                    }
                });
                //要做的事情
                handler.postDelayed(this, 500);
            }
        };
        new Thread(runnable).start();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        synchronized (this) {
            switch (event.sensor.getType()){
                case Sensor.TYPE_ACCELEROMETER:
                     mAcceData = event.values.clone();
                     Log.i(TAG," acc:"+ DataUtils.printData(mAcceData));
                    break;
                case Sensor.TYPE_MAGNETIC_FIELD:
//                    View2.setText("磁场：" + str);
                     magData = event.values.clone() ;
                    Log.i(TAG," mag:"+ DataUtils.printData(magData));
                    break;
                case Sensor.TYPE_ORIENTATION:
//                    View3.setText("定位：" + str);
                    break;
                case Sensor.TYPE_GYROSCOPE:
//                    View4.setText("陀螺仪：" + str);
                    gyrData = event.values.clone();
                    Log.i(TAG," gyr:"+ DataUtils.printData(gyrData));
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
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onStop() {
        super.onStop();
        if(controller!=null){
            controller.unRegisterSensor();
        }
        if(handler!=null && runnable!=null){
            handler.removeCallbacks(runnable);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
