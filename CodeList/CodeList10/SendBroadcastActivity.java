package com.ngocminhtran.sendbroadcast;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SendBroadcastActivity extends AppCompatActivity {

    BroadcastReceiver receiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_broadcast);
        configureReceiver();
    }
    public void broadcastIntent(View view)
    {
        Intent intent = new Intent();
        intent.setAction("com.ngocminhtran.sendbroadcast");
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        sendBroadcast(intent);
    }
    private void configureReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.ngocminhtran.sendbroadcast");
        filter.addAction("android.intent.action.ACTION_POWER_DISCONNECTED");
        receiver = new MyReceiver();
        registerReceiver(receiver, filter);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}
