package com.ngocminhtran.fragmentexample;


import android.support.v4.app.FragmentActivity;
import android.os.Bundle;


public class FragmentExampleActivity extends FragmentActivity
        implements FirstFragment.FirstFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_example);

    }
    public void onButtonClick(int fontsize, String text) {
        SecondFragment textFragment =
                (SecondFragment)
                        getSupportFragmentManager().findFragmentById(R.id.second_fragment);
        textFragment.changeTextProperties(fontsize, text);
    }

}
