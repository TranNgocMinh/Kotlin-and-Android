package com.example.admin.mydatabase;

/**
 * Created by Admin on 04/06/2018.
 */

public class Student {
    private int _id;
    private String _studentname;

    public Student() {
    }
    public Student(int id, String studentname) {
        this._id = id;
        this._studentname = studentname;
    }
     public void setID(int id) {
        this._id = id;
    }
     public int getID() {
        return this._id;
    }
    public void setStudentName(String studentname) {
        this._studentname = studentname;
    }
    public String getStudentName() {
        return this._studentname;
    }
}
