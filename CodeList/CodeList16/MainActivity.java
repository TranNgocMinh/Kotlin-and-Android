package com.ngocminhtran.remoteboundservice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.View;
public class MainActivity extends AppCompatActivity {

    Messenger myService = null;
    boolean isBound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(getApplicationContext(),
                RemoteBoundService.class);
        bindService(intent, myConnection, Context.BIND_AUTO_CREATE);
    }
    private ServiceConnection myConnection =
            new ServiceConnection() {
        public void onServiceConnected(
                ComponentName className,
                IBinder service) {
            myService = new Messenger(service);
            isBound = true;
        }
        public void onServiceDisconnected(
                ComponentName className) {
            myService = null;
            isBound = false;
        }
    };
    public void sendMessage(View view)
    {
        if (!isBound) return;
        Message msg = Message.obtain();
        Bundle bundle = new Bundle();
        bundle.putString("MyString", "Message Received");
        msg.setData(bundle);
        try {
            myService.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
