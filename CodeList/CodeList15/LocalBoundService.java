package com.ngocminhtran.localboundservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class LocalBoundService extends Service {

    private final IBinder myBinder = new MyLocalBinder();
    public LocalBoundService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }
    public String getCurrentTime() {
        SimpleDateFormat dateformat =
                new SimpleDateFormat("HH:mm:ss dd/MM/yyyy", Locale.US);
        return (dateformat.format(new Date()));
    }
    public class MyLocalBinder extends Binder {
        LocalBoundService getService() {
            return LocalBoundService.this;
        }
    }
}
