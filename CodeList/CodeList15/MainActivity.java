package com.ngocminhtran.localboundservice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.IBinder;
import android.content.Context;
import android.content.Intent;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.view.View;
import android.widget.TextView;

import com.ngocminhtran.localboundservice.LocalBoundService.MyLocalBinder;
public class MainActivity extends AppCompatActivity {
    LocalBoundService myService;
    boolean isBound = false;
    public void showTime(View view)
    {
        String currentTime = myService.getCurrentTime();
        TextView myTextView =
                (TextView)findViewById(R.id.myTextView);
        myTextView.setText(currentTime);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, LocalBoundService.class);
        bindService(intent, myConnection, Context.BIND_AUTO_CREATE);
    }
    private ServiceConnection myConnection = new ServiceConnection()
    {
        @Override
        public void onServiceConnected(ComponentName className,
            IBinder service) {
            MyLocalBinder binder = (MyLocalBinder) service;
            myService = binder.getService();
            isBound = true;
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };
}
