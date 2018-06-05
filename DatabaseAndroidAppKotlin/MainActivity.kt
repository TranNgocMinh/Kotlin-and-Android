package com.example.admin.mydatabasekotlinapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.admin.mydatabasekotlinapp.R.id.studentname
import com.example.admin.mydatabasekotlinapp.R.id.studentid
import com.example.admin.mydatabasekotlinapp.R.id.lst
import kotlinx.android.synthetic.main.activity_main.*
import com.example.admin.mydatabasekotlinapp.R.id.studentname
import com.example.admin.mydatabasekotlinapp.R.id.studentid
import com.example.admin.mydatabasekotlinapp.R.id.studentname
import com.example.admin.mydatabasekotlinapp.R.id.studentid
import com.example.admin.mydatabasekotlinapp.R.id.lst
import com.example.admin.mydatabasekotlinapp.R.id.studentid
import com.example.admin.mydatabasekotlinapp.R.id.lst
import com.example.admin.mydatabasekotlinapp.R.id.studentname
import com.example.admin.mydatabasekotlinapp.R.id.studentid
import com.example.admin.mydatabasekotlinapp.R.id.lst
import com.example.admin.mydatabasekotlinapp.R.id.studentname




//import sun.rmi.registry.RegistryImpl.getID




class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun loadStudents(view: View) {
        val dbHandler = MyDBHandler(this, null, null, 1)
        lst.setText(dbHandler.loadHandler())
        studentid.setText("")
        studentname.setText("")
    }

    fun addStudent(view: View) {
        val dbHandler = MyDBHandler(this, null, null, 1)
        val id = Integer.parseInt(studentid.text.toString())
        val name = studentname.text.toString()
        val student = Student(id, name)
        dbHandler.addHandler(student)
        studentid.setText("")
        studentname.setText("")
    }

    fun findStudent(view: View) {
        val dbHandler = MyDBHandler(this, null, null, 1)
        var student = dbHandler.findHandler(studentname.text.toString())
        if (student != null) {
            lst.setText(student.studentID.toString() + " " + student.studentName + System.getProperty("line.separator"))
            studentid.setText("")
            studentname.setText("")
        } else {
            lst.text = "No Match Found"
            studentid.setText("")
            studentname.setText("")
        }
    }

    fun deleteStudent(view: View) {
        val dbHandler = MyDBHandler(this,
                null, null, 1)
        val result = dbHandler.deleteHandler(Integer.parseInt(
                studentid.text.toString()))
        if (result) {
            studentid.setText("")
            studentname.setText("")
            lst.text = "Record Deleted"
        } else
            studentid.setText("No Match Found")
    }

    fun updateStudent(view: View) {
        val dbHandler = MyDBHandler(this,
                null, null, 1)
        val result = dbHandler.updateHandler(Integer.parseInt(
                studentid.text.toString()), studentname.text.toString())
        if (result) {
            studentid.setText("")
            studentname.setText("")
            lst.text = "Record Updated"
        } else
            studentid.setText("No Match Found")
    }
}
