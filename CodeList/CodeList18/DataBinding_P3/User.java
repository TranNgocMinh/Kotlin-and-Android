package com.example.mydatabinding;

import android.database.Observable;

import androidx.databinding.ObservableField;

public class User {
    public final ObservableField<String> fullName = new ObservableField<>();
    public final ObservableField<Integer> age = new ObservableField<>();
    public User(String fullName, Integer age) {
        this.fullName.set(fullName);
        this.age.set(age);
    }
    public int incAge(){
        this.age.set(this.age.get()+1);
        return this.age.get();
    }
}
