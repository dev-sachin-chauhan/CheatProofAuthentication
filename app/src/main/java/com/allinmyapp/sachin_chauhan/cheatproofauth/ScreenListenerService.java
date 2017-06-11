package com.allinmyapp.sachin_chauhan.cheatproofauth;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.widget.Toast;

public class ScreenListenerService extends Service {

    IntentFilter filter;
    static BroadcastReceiver mScreenListener = null;

    public ScreenListenerService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        mScreenListener = new ScreenReceiver();
        Toast.makeText(this,"The service is created",Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        registerReceiver(mScreenListener, filter);
        Toast.makeText(this,"The service has started",Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this,"The service has stopped",Toast.LENGTH_SHORT).show();
        unregisterReceiver(mScreenListener);
        mScreenListener = null;
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
