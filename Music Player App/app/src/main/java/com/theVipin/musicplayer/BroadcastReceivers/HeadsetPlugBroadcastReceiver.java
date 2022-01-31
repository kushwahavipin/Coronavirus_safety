package com.theVipin.musicplayer.BroadcastReceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.theVipin.musicplayer.Common;


public class HeadsetPlugBroadcastReceiver extends BroadcastReceiver {
    private Common mApp;
    @Override
    public void onReceive(Context context, Intent intent) {
        mApp = (Common) context.getApplicationContext();
        if (intent.getAction().equals(Intent.ACTION_HEADSET_PLUG)) {
            int state = intent.getIntExtra("state", -1);
            Log.d("state",""+state);
            switch (state) {
                case 0:
                    mApp.getService().headsetDisconnected();
                    break;
                case 1:
                    mApp.getService().headsetIsConnected();
                    break;
                default:
                    break;
            }

        }

    }
}