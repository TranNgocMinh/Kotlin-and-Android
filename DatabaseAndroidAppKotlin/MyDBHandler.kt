package com.example.admin.mydatabasekotlinapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
//import javax.swing.UIManager.put
//import sun.rmi.registry.RegistryImpl.getID
import android.content.ContentValues
import android.database.Cursor
//import javax.swing.UIManager.put

/**
 * Created by Admin on 18/05/2018.
 */
class MyDBHandler(context: Context, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int) :
        SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {
    companion object {
        //information of database
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "studentDB.db"
        val TABLE_NAME = "students"
        val COLUMN_ID = "StudentID"
        val COLUMN_NAME = "StudentName"
    }
    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TABLE = ("CREATE TABLE " +
                TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_NAME
                + " TEXT" + ")")
        db.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int,
                           newVersion: Int) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    fun loadHandler(): String {
        var result = ""
        val query = "Select*FROM " + TABLE_NAME
        val db = this.writableDatabase
        val cursor = db.rawQuery(query, null)
        while (cursor.moveToNext()) {
            val result_0 = cursor.getInt(0)
            val result_1 = cursor.getString(1)
            result += result_0.toString() + " " + result_1 +
                    System.getProperty("line.separator")
        }
        cursor.close()
        db.close()
        return result
    }

    fun addHandler(student: Student) {
        val values = ContentValues()
        values.put(COLUMN_ID, student.studentID)
        values.put(COLUMN_NAME, student.studentName)
        val db = this.writableDatabase
        db.insert(TABLE_NAME, null, values)
        db.close()
    }
    fun findHandler(studentname:String):Student? {
        val query:String = "Select * FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME + " = " + "'" + studentname + "'"
        val db:SQLiteDatabase = this.getWritableDatabase()
        val cursor:Cursor = db.rawQuery(query, null)
        var student:Student?
        if (cursor.moveToFirst()) {
            cursor.moveToFirst()
            val id = Integer.parseInt(cursor.getString(0))
            val name = cursor.getString(1)
            student = Student(id, name)
            cursor.close()
        } else {
            student = null
        }
        db.close()
        return student
    }

    fun deleteHandler(ID:Int):Boolean {
        var result = false
        var query = "Select*FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = '" + ID.toString() + "'";
        var db = this.getWritableDatabase();
        var cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            val id =Integer.parseInt(cursor.getString(0))
            db.delete(TABLE_NAME, COLUMN_ID + " =?",
                    arrayOf(id.toString()))
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    fun updateHandler(ID: Int, name: String): Boolean {
        val db = this.writableDatabase
        val args = ContentValues()
        args.put(COLUMN_ID, ID)
        args.put(COLUMN_NAME, name)
        return db.update(TABLE_NAME, args, COLUMN_ID + " = " + ID, null) > 0
    }


}
