package com.example.admin.myfirstkotlinapp
import android.view.View
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var s =""
        val buttonClickListener = View.OnClickListener { view ->

            var student = Student()
            s += dataOf(student)
            data.text = s
            txtID.text.clear()
            txtName.text.clear()
            txtUni.text.clear()
            rdFemale.isChecked = true
            rdMale.isChecked = false
        }

        btnData.setOnClickListener(buttonClickListener)
    }
    fun dataOf(student:Student):String
    {
        student.StudentID = txtID.text.toString()
        student.Name = txtName.text.toString()
        student.University = txtUni.text.toString()
        student.Gender = if(rdMale.isChecked)"Male" else "Female"

        return student.canWork()
    }

}
open class Person
{
    var Name:String = ""
    get() = field.toUpperCase()
    set(value){
        field="I am $value"
    }
    var Gender:String=""

    open fun canWork():String = "I can unknown!"
}

class Student:Person()
{
    var StudentID:String = ""
    var University:String=""
    override fun canWork(): String {
        return "$Name .I am learning at $University. My ID is $StudentID and my gender is $Gender \n"
    }

}
