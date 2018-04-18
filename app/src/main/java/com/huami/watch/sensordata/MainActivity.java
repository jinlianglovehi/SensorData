package com.huami.watch.sensordata;

import android.hardware.Sensor;
import android.hardware.SensorListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


/**
 * 第yi种解决办法
 */
public class MainActivity extends AppCompatActivity implements SensorListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private TextView dataAccView,dataMagView,dataGyrView ;
    private SensorDataManager sensorDataManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate: ");

        dataAccView = (TextView) findViewById(R.id.data_acc);
        dataMagView =(TextView)findViewById(R.id.data_mag) ;
        dataGyrView = (TextView)findViewById(R.id.data_gyr);

        sensorDataManager = SensorDataManager.getInstance(MainActivity.this);
        sensorDataManager.setSensorLister(this);
        sensorDataManager.registerSensor();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");


    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: ");
        if(sensorDataManager!=null){
            sensorDataManager.unRegisterSensor();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
    }


//    sensor lister
    @Override
    public void onSensorChanged(int sensor, final float[] values) {
        synchronized (this) {
            String str = "X：" + values[0] + "，Y：" + values[1] + "，Z：" + values[2];
            switch (sensor){
                case Sensor.TYPE_ACCELEROMETER:
//                    View1.setText("加速度：" + str);
                    Log.i(TAG," acc:"+ DataUtils.printData(values));
                     runOnUiThread(new Runnable() {
                         @Override
                         public void run() {
                             dataAccView.setText(DataUtils.printData(values));
                         }
                     });
                    break;
                case Sensor.TYPE_MAGNETIC_FIELD:
//                    View2.setText("磁场：" + str);
                    Log.i(TAG," mag:"+ DataUtils.printData(values));
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            dataMagView.setText(DataUtils.printData(values));
                        }
                    });
                    break;
                case Sensor.TYPE_ORIENTATION:
//                    View3.setText("定位：" + str);
                    break;
                case Sensor.TYPE_GYROSCOPE:
//                    View4.setText("陀螺仪：" + str);
                    Log.i(TAG," gyr:"+ DataUtils.printData(values));
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            dataGyrView.setText(DataUtils.printData(values));
                        }
                    });
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
