package com.thinkagaingames.integratedbasketball;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.thinkagaingames.basketball.UnityPlayerActivity;
import com.unity3d.player.UnityPlayer;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity implements AndroidToUnity.OnFragmentInteractionListener {

    private AndroidToUnity a2uFragment = null;
    private BasketballBroadcastReceiver bbr = null;
    private int adId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bbr = new BasketballBroadcastReceiver();
        bbr.setListeners(this, null);
        IntentFilter filter = new IntentFilter();
        filter.addAction(getString(R.string.INTENT_REQUEST_AD_ID));
        filter.addAction(getString(R.string.INTENT_SET_LAST_SCORE));
        registerReceiver(bbr, filter);
    }

    public void onPlayButtonPushed(View view) {
        generateAdId();

        TextView tv = (TextView)findViewById(R.id.textView);
        tv.setText("" + adId);

        Intent unityIntent = new Intent(getApplicationContext(), UnityPlayerActivity.class);
        startActivity(unityIntent);
    }

    public int getAdId() {
        return adId;
    }

    public void setLastScore(int score) {
        TextView tv = (TextView)findViewById(R.id.textView);
        tv.setText("" + score);
    }

    private void generateAdId() {
        adId = (int)Math.floor(Math.random() * 3) + 1;
    }

    public void onFragmentInteraction(Uri uri) {

    }
}
