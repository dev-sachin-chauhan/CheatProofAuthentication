package com.allinmyapp.sachin_chauhan.cheatproofauth;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Sachin_Chauhan on 1/26/2016.
 */
public class ScreenReceiver extends BroadcastReceiver {

    // THANKS JASON
    public static boolean wasScreenOn = true;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            wasScreenOn = false;
        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
            Intent activityintent = new Intent(context,FullscreenActivity.class);
            activityintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(activityintent);
            wasScreenOn = true;
        }
    }

}
