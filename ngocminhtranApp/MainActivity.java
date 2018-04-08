package com.example.admin.myapplication;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebBackForwardList;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       final ActionBar actionBar = getSupportActionBar();
       actionBar.setTitle("ngocminhtran.com");
       actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#333333")));

        final WebView myWebView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        myWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url)
            {
                 view.loadUrl("javascript:(function() { " +
                         "document.getElementsByClassName('navigation')[0].style.display='none'; " +
                         "document.getElementById('footer').style.display='none'; " +
                        "})()");

            }

        });
        myWebView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setHomeAsUpIndicator(R.drawable.ic_close_white_24dp);
                return false;
            }
        });

        myWebView.loadUrl("http://ngocminhtran.com/thu-vien-cua-toi/");

    }
    @Override
    public boolean onSupportNavigateUp() {
        String url="" ;
        WebView myWebView = (WebView) findViewById(R.id.webview);
        WebBackForwardList mWebBackForwardList = myWebView.copyBackForwardList();
        int i =  mWebBackForwardList.getSize();
        while (i > 0){

            url = mWebBackForwardList.getItemAtIndex(i-1).getUrl();
            if (url.equals("https://ngocminhtran.com/category/blog/")||
                    url.equals("https://ngocminhtran.com/thu-vien-cua-toi/"))
            {
                ActionBar actionBar = getSupportActionBar();
                actionBar.setDisplayHomeAsUpEnabled(false);
                break;
            }

           i = i - 1;
        }
        myWebView.loadUrl(url);
        return true;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menuapp, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final ActionBar actionBar = getSupportActionBar();
        final WebView myWebView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        View view = findViewById(item.getItemId());
        if (view != null && view instanceof TextView) {
            ((TextView) view).setTextColor( Color.DKGRAY ); 
        }
        switch (item.getItemId()) {
            case R.id.blog:

                myWebView.setWebViewClient(new WebViewClient() {
                    @Override
                    public void onPageFinished(WebView view, String url)
                    {
                        view.loadUrl("javascript:(function() { " +
                                "document.getElementsByClassName('navigation')[0].style.display='none'; " +
                                "document.getElementById('footer').style.display='none'; " +
                                "})()");

                    }

                });
                myWebView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        actionBar.setDisplayHomeAsUpEnabled(true);
                        actionBar.setHomeAsUpIndicator(R.drawable.ic_close_white_24dp);
                        return false;
                    }
                });

                myWebView.loadUrl("http://ngocminhtran.com/category/blog");
                actionBar.setDisplayHomeAsUpEnabled(false);
                view = findViewById(R.id.mybooks);
                if (view != null && view instanceof TextView) {
                    ((TextView) view).setTextColor( Color.WHITE ); 
                }
                return true;

            case R.id.mybooks:

                myWebView.setWebViewClient(new WebViewClient() {
                    @Override
                    public void onPageFinished(WebView view, String url)
                    {
                        view.loadUrl("javascript:(function() { " +
                                "document.getElementsByClassName('navigation')[0].style.display='none'; " +
                                "document.getElementById('footer').style.display='none'; " +
                                "})()");

                    }

                });
                myWebView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        actionBar.setDisplayHomeAsUpEnabled(true);
                        actionBar.setHomeAsUpIndicator(R.drawable.ic_close_white_24dp);
                        return false;
                    }
                });

                myWebView.loadUrl(" http://ngocminhtran.com/thu-vien-cua-toi/");
                actionBar.setDisplayHomeAsUpEnabled(false);
                view = findViewById(R.id.mybooks);
                view = findViewById(R.id.blog);
                if (view != null && view instanceof TextView) {
                    ((TextView) view).setTextColor( Color.WHITE ); 
                }
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
