package com.thinkagaingames.integratedbasketball;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unity3d.player.UnityPlayer;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AndroidToUnity.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class AndroidToUnity extends android.app.Fragment {
    private OnFragmentInteractionListener mListener;

    public AndroidToUnity() {
        bbr = new BasketballBroadcastReceiver();
    }

    public static AndroidToUnity a2uInstance = null;

    private BasketballBroadcastReceiver bbr = null;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment AndroidToUnity.
     */
    // TODO: Rename and change types and number of parameters
    public static void createInstance() {
        if (a2uInstance == null) {
            AndroidToUnity fragment = new AndroidToUnity();
            fragment.setArguments(null);

            FragmentTransaction ft = UnityPlayer.currentActivity.getFragmentManager().beginTransaction();
            ft.add(fragment, "A2Ufragment").commit();

            Log.e("UNITY", "A2Ufragment created!");

            a2uInstance = fragment;
        }
    }

    public static void UnityDidCompleteSetup() {
        // TODO: initialize custom audio system.
        Log.e("UNITY", ">>> Initialize Audio System Here <<<");
    }

    public static AndroidToUnity instance() {
        return a2uInstance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_android_to_unity, container, false);
    }

    public void onStartButtonPressed(Uri uri, Context context) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public void startGame(int adId) {
        UnityPlayer.UnitySendMessage("World", "StartResourceLoad", "" + adId);
    }

    public void setListeners(MainActivity mainActivity, AndroidToUnity a2u) {
        if (this.bbr != null) {
            this.bbr.setListeners(mainActivity, a2u);

            IntentFilter filter = new IntentFilter();
            filter.addAction(getString(R.string.INTENT_SET_AD_ID));
            UnityPlayer.currentActivity.registerReceiver(bbr, filter);
        }
        else {
            throw new UnsupportedOperationException("No bbr defined!");
        }
    }

    public void setLastScore(int score) {
        if (NativeToUnityApp.instance != null) {
            Log.e("UNITY", "*** Setting last score to " + score);

            Intent eventIntent = new Intent();
            eventIntent.setAction(getString(R.string.INTENT_SET_LAST_SCORE));
            eventIntent.putExtra(getString(R.string.KEY_LAST_SCORE), score);
            UnityPlayer.currentActivity.sendBroadcast(eventIntent);
        }
        else {
            Log.e("UNITY", "!!! No App Context !!!");
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        Log.e("UNITY", "onAttach (1)");

        setListeners(null, this);

        requestAdId();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Log.e("UNITY", "onAttach (2)");

        requestAdId();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void requestAdId() {
        Log.e("UNITY", "Requestin an ID...");
        Intent eventIntent = new Intent();
        eventIntent.setAction(getString(R.string.INTENT_REQUEST_AD_ID));
        UnityPlayer.currentActivity.sendBroadcast(eventIntent);
    }
}
