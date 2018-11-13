package com.ngocminhtran.sqlitedemoapplication;

public class Student {
    //các biến tương ứng với các cột của bảng Students
    private int _studentid;
    private String _studentname;

    //Các phương thức khởi tạo (constructors)
    //Phương thức khởi tạo mặc định
    public Student(){

    }
    //Phương thức khởi tạo có tham số
    public Student(int id, String name){
        this._studentid = id;
        this._studentname = name;
    }

    //các phương thức truy cập các biến thành viên

    public int getStudentID(){
        return this._studentid;
    }
    public void setStudentID(int id){
        this._studentid = id;
    }

    public String getStudentName(){
        return this._studentname;
    }
    public void setStudentName(String name){
        this._studentname = name;
    }

}
