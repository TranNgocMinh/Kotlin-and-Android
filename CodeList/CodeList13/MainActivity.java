package com.ngocminhtran.asyncdemo;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private class MyTask extends AsyncTask<String, Integer, String> {
        @Override
        protected void onPreExecute() {
        }
        @Override
        protected String doInBackground(String... params) {
            int i = 0;
            while (i <= 20) {
                publishProgress(i);
                try {
                    Thread.sleep(1000);
                    publishProgress(i);
                    i++;
                }catch (Exception e) {
                    return(e.getLocalizedMessage());
                }
            }
            int cpu_cores = Runtime.getRuntime().availableProcessors();
            //return "Button Pressed";
            return String.valueOf(cpu_cores);
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            myTextView.setText("Counter = " + values[0]);
        }
        @Override
        protected void onPostExecute(String result) {
            myTextView.setText(result);
        }
    }
    private TextView myTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myTextView = (TextView)findViewById(R.id.myTextView);
    }
    public void buttonClick(View view)
    {
        AsyncTask task = new MyTask().execute();
        //AsyncTask task = new
                //MyTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        task.cancel(true);
    }
}
