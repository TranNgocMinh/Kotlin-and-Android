package com.ngocminhtran.serviceexampleactivity;
import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class MyIntentService extends IntentService{
    private static final String TAG =
            "ServiceExample";
    @Override
    protected void onHandleIntent(Intent arg0) {
        Log.i(TAG, "Intent Service started");
    }
    public MyIntentService() {
        super("MyIntentService");
    }
}
