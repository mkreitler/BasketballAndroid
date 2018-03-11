package com.thinkagaingames.integratedbasketball;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.thinkagaingames.basketball.UnityPlayerActivity;

public class UnityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unity);

        Intent intent = new Intent(getApplicationContext(), UnityPlayerActivity.class);
        startActivity(intent);
    }
}
