package com.thinkagaingames.integratedbasketball;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.thinkagaingames.basketball.UnityPlayerActivity;

public class UnityActivity extends AppCompatActivity {
    protected Intent unityIntent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unity);

        Log.e("UNITY", "UnityActivity created!");
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.e("UNITY", "UnityActivity started!");

        if (unityIntent == null) {
            Log.e("UNITY", "Allocating Unity intent...");

            Intent intent = new Intent(getApplicationContext(), UnityPlayerActivity.class);
            startActivity(intent);
        }
    }
}
