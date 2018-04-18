package com.huami.watch.sensordata;

import android.util.Log;

/**
 * Created by jinliang on 18-4-18.
 */

public class DataUtils {

    private static final String TAG = DataUtils.class.getSimpleName();

    public static String printData(float[] values){
        StringBuilder sb = new StringBuilder();
        if(values!=null && values.length>0){
            for (int i = 0; i < 3; i++) {
                sb.append(values[i]+",") ;
            }
        }
        return sb.toString();
    }
}
