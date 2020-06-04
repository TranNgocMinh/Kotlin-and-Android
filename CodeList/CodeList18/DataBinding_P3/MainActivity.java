package com.example.mydatabinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.example.mydatabinding.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        User user = new User("Ngoc Minh Tran",35);
        binding.setUser(user);
    }
    public void Change_Name(View v){
        User user = new User("Ngoc Minh Tran",35);
        binding.setUser(user);
    }
}
