package com.thinkagaingames.integratedbasketball;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.unity3d.player.UnityPlayer;

public class BasketballBroadcastReceiver extends BroadcastReceiver {
    private MainActivity mainActivity = null;
    private AndroidToUnity pluginFragment = null;

    public void setListeners(MainActivity mainActivity, AndroidToUnity pluginFragment) {
        this.mainActivity = mainActivity;
        this.pluginFragment = pluginFragment;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Log.e("UNITY", ">>> Handling message " + intent.getAction());

        if (mainActivity == null) {
            Log.e("UNITY", "!!! mainActivity is null!");
        }

        if (pluginFragment == null) {
            Log.e("UNITY", "!!! pluginFragment is null!");
        }

        if (mainActivity != null) {
            // This is a handler for the main activity.
            if (intent.getAction() == mainActivity.getString(R.string.INTENT_REQUEST_AD_ID)) {
                    Intent eventIntent = new Intent();
                    eventIntent.setAction(mainActivity.getString(R.string.INTENT_SET_AD_ID));
                    eventIntent.putExtra(mainActivity.getString(R.string.KEY_AD_ID), mainActivity.getAdId());
                    mainActivity.sendBroadcast(eventIntent);
            }
            else if (intent.getAction() == mainActivity.getString(R.string.INTENT_SET_LAST_SCORE)) {
                mainActivity.setLastScore(intent.getIntExtra(mainActivity.getString(R.string.KEY_LAST_SCORE), 0));
            }
        }
        else if (pluginFragment != null) {
            // This is a handler for the plug-in fragment.
            if (intent.getAction() == pluginFragment.getString(R.string.INTENT_SET_AD_ID)) {
                pluginFragment.startGame(intent.getIntExtra(pluginFragment.getString(R.string.KEY_AD_ID), 0));
            }
        }
        else {
            // Shouldn't get here.
            throw new UnsupportedOperationException("Not yet implemented");
        }
    }
}
