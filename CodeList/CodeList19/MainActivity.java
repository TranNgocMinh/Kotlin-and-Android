package com.example.myviewmodelapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    Button btn;
    TextView txt;
    String myName = "";
    MyViewModel myModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt = (TextView)findViewById(R.id.txt);
        btn = (Button)findViewById(R.id.btn);
        myModel = new ViewModelProvider(this).get(MyViewModel.class);
        txt.setText(myModel.myName);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change_name("Ngoc Minh");
            }
        });
    }
    public void change_name(String newName){
        myModel.myName = newName;
        txt.setText(myModel.myName);
    }

}
