package com.ngocminhtran.sqlitedemoapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.ngocminhtran.database.provider.MyContentProvider;
import java.util.ArrayList;
import java.util.List;
import android.content.ContentResolver;
public class DataHandler extends SQLiteOpenHelper {

    private ContentResolver myCR;
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
        myCR = context.getContentResolver();
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
        myCR.insert(MyContentProvider.CONTENT_URI, values);
    }
    //tìm kiếm Student theo StudentName
    //kết quả trả về là Student đầu tiên trong danh sách kết quả
    public Student findFisrtDataHandler(String studentname) {

        String[] projection = {COLUMN_ID, COLUMN_NAME };
        String selection = "studentname = \"" + studentname +
                "\"";
        Cursor cursor = myCR.query(MyContentProvider.CONTENT_URI,
                projection, selection, null,
                null);
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
        //trả về sinh viên đầu tiên tìm được
        return student;
    }
    //tìm kiếm Student theo StudentName
    //kết quả trả về là tất cả Student trong danh sách kết quả
    public List<Student> findAllDataHandler(String studentname) {
        String[] projection = {COLUMN_ID, COLUMN_NAME };
        String selection = "studentname = \"" + studentname +
                "\"";
        // Thực thi truy vấn và gán kết quả đến đối tượng Cursor
        Cursor cursor = myCR.query(MyContentProvider.CONTENT_URI,
                projection, selection, null,
                null);
        //danh sách chứa tất cả các Student tìm được
        List<Student> lst =  new ArrayList<Student>();

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
        //trả về danh sách sinh viên tìm được
        return lst;
    }
    public boolean deleteDataHandler(int ID) {
        boolean result = false;
        String selection = "studentid = \"" + ID +
                "\"";
        int rowsDeleted =
                myCR.delete(MyContentProvider.CONTENT_URI,
                        selection, null);
        if (rowsDeleted > 0)
            result = true;
        return result;
    }
    public boolean updateDataHandler(int ID, String name) {

        ContentValues args = new ContentValues();
        args.put(COLUMN_ID, ID);
        args.put(COLUMN_NAME, name);

        boolean result = false;
       String selection = "studentid = \"" + ID + "\"";
        int rowsUpdated =
                myCR.update(MyContentProvider.CONTENT_URI,args,selection,null);
        if (rowsUpdated > 0)
            result = true;
        return result;

    }

}
