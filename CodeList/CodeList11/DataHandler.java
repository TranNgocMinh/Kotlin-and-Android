package com.ngocminhtran.sqlitedemoapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DataHandler extends SQLiteOpenHelper {

    // các biến mô tả cơ sở dữ liệu
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "StudentsDB.db";
    public static final String TABLE_NAME = "Students";
    public static final String COLUMN_ID = "StudentID";
    public static final String COLUMN_NAME = "StudentName";

    //phương thức khởi tạo
    public DataHandler(Context context, String name,
                       SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        //chuỗi lệnh truy vấn tạo bảng Students
        String CREATE_STUDENTS_TABLE = "CREATE TABLE " +
                TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_NAME + " TEXT )";
        //thực thi truy vấn
        db.execSQL(CREATE_STUDENTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        //Xóa bảng nếu tồn tại
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        //Tạo bảng mới
        onCreate(db);
    }
    //hiển thị dữ liệu từ bảng Students
    public String loadDataHandler() {
        String result = "";
        //chuỗi truy vấn SELECT
        String query = "SELECT* FROM " + TABLE_NAME;
        //sẵn sàng thực thi các truy vấn
        SQLiteDatabase db = this.getWritableDatabase();
        //thực thi truy vấn bằng phương thức rawQuery()
        //kết quả trả về lưu trong đối tượng Cursor
        Cursor cursor = db.rawQuery(query, null);
        //duyệt qua dữ liệu từ đối tượng Cursor
        while (cursor.moveToNext()) {
            //nhận giá trị cột thứ nhất (StudentID)
            int result_0 = cursor.getInt(0);
            //nhận giá trị cột thứ hai (StudentName)
            String result_1 = cursor.getString(1);
            //hiển thị mỗi hàng trong một chuỗi
            result += String.valueOf(result_0) + " " + result_1 +
                    System.getProperty("line.separator");
        }
        //đóng đối tượng Cursor
        cursor.close();
        //đóng đối tượng SQLiteDatabase
        db.close();
        return result;
    }
    //thêm dữ liệu đến bảng Students
    public void addDataHandler(Student student) {
        //tạo đối tượng ContentValues
        ContentValues values = new ContentValues();
        //thêm giá trị các cột đến đối tượng ContentValues
        values.put(COLUMN_ID, student.getStudentID());
        values.put(COLUMN_NAME, student.getStudentName());
        SQLiteDatabase db = this.getWritableDatabase();
        //chèn dữ liệu đến bảng
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    //tìm kiếm Student theo StudentName
    //kết quả trả về là Student đầu tiên trong danh sách kết quả
    public Student findFisrtDataHandler(String studentname) {

        //chuỗi truy vấn tìm kiếm Student theo StudentName
        String query = "Select * FROM " + TABLE_NAME
                       + " WHERE " + COLUMN_NAME + " = "
                       + "'" + studentname + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        // Thực thi truy vấn và gán kết quả đến đối tượng Cursor
        Cursor cursor = db.rawQuery(query, null);
        Student student = new Student();
        //trả về hàng đầu tiên trong kết quả
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            student.setStudentID(Integer.parseInt(cursor.getString(0)));
            student.setStudentName(cursor.getString(1));
            cursor.close();
        } else {
            student = null;
        }
        db.close();
        //trả về sinh viên đầu tiên tìm được
        return student;
    }
    //tìm kiếm Student theo StudentName
    //kết quả trả về là tất cả Student trong danh sách kết quả
    public List<Student> findAllDataHandler(String studentname) {
        //chuỗi truy vấn tìm kiếm Student theo StudentName
        String query = "Select * FROM " + TABLE_NAME
                        + " WHERE " + COLUMN_NAME + " = "
                        + "'" + studentname + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        //danh sách chứa tất cả các Student tìm được
        List<Student> lst =  new ArrayList<Student>();
        // Thực thi truy vấn và gán kết quả đến đối tượng Cursor
        Cursor cursor = db.rawQuery(query, null);
        //duyệt qua tất cả các hàng từ hàng đầu tiên
        if(cursor.moveToFirst()) {
            do {
                    Student student = new Student();
                    student.setStudentID(Integer.parseInt(cursor.getString(0)));
                    student.setStudentName(cursor.getString(1));
                    lst.add(student);
             }while (cursor.moveToNext());
        }
        //đóng các đối tượng
        cursor.close();
        db.close();
        //trả về danh sách sinh viên tìm được
        return lst;
    }
    public boolean deleteDataHandler(int ID) {
        boolean result = false;
        String query = "Select * FROM " + TABLE_NAME + " WHERE "
                + COLUMN_ID + " = '" + String.valueOf(ID) + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Student student = new Student();
        if (cursor.moveToFirst()) {
            student.setStudentID(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_NAME, COLUMN_ID + "=?",
                    new String[] {
                String.valueOf(student.getStudentID())
            });
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }
    public boolean updateDataHandler(int ID, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(COLUMN_ID, ID);
        args.put(COLUMN_NAME, name);
        return db.update(TABLE_NAME, args, COLUMN_ID + " = " + ID, null) > 0;
    }

}
