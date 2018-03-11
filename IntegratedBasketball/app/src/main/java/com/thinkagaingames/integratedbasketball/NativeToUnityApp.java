package com.thinkagaingames.integratedbasketball;

import android.app.Application;
import android.util.Log;

/**
 * Created by markkreitler on 3/11/18.
 */

public class NativeToUnityApp extends Application {
    public static NativeToUnityApp instance = null;

    public static int adId = 0;
    public static int lastScore = 0;


    @Override
    public void onCreate() {
        super.onCreate();

        Log.e("UNITY",">>> Creating NativeToUnityApp <<<");
        instance = this;
    }
}
