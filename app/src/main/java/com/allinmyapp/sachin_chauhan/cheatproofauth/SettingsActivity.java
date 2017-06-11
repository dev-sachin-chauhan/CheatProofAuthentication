package com.allinmyapp.sachin_chauhan.cheatproofauth;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;

public class SettingsActivity extends AppCompatActivity {

    Intent mServiceIntent = null;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        context = getApplicationContext();
        mServiceIntent = new Intent(context,ScreenListenerService.class);
        Switch listenswitch =(Switch)findViewById(R.id.enable_switch);
        if(ScreenListenerService.mScreenListener == null){
            listenswitch.setChecked(false);
        }
        else
        {
            listenswitch.setChecked(true);
        }

        listenswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    // INITIALIZE RECEIVER
                    startService(mServiceIntent);
                }else{
                    stopService(mServiceIntent);
                }

            }
        });
    }


}
