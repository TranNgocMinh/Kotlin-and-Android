package com.ngocminhtran.remoteboundservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Bundle;
import android.os.Handler;import android.os.Message;
import android.widget.Toast;
import android.os.Messenger;
public class RemoteBoundService extends Service {

    final Messenger myMessenger = new Messenger(new IncomingHandler());
    public RemoteBoundService() {
    }

    class IncomingHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            Bundle data = msg.getData();
            String dataString = data.getString("MyString");
            Toast.makeText(getApplicationContext(),
                    dataString, Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public IBinder onBind(Intent intent) {
        return myMessenger.getBinder();
    }
}
