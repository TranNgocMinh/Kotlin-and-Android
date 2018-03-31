package com.example.admin.myfirstkotlinapp
import android.content.Intent
import android.view.View
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val buttonClickListener = View.OnClickListener { view ->
            display()

        }
        display.setOnClickListener(buttonClickListener)
    }

    fun display(){


        val lst  = ArrayList<Shape>()

        val rect = Shape(Rectangle())
        rect.fillColor ="RED"
        rect.outlineColor ="BLUE"
        lst.add(rect)

        val circle = Shape(Circle())
        circle.fillColor ="YELLOW"
        circle.outlineColor ="GRAY"
        lst.add(circle)

       for(i:Int in 0..lst.size-1)
           Helper.DisplayString = (i+1).toString()+". "+ lst[i].Draw()


        val intent = Intent(this,TheSecondActivity::class.java)
        startActivity(intent)
    }
}

interface iShape {

    //properties
    var fillColor:String
    var outlineColor:String
    //methods
    fun Draw():String
}
class Shape (s:iShape):iShape by s
class Circle:iShape{
    override var fillColor:String = ""
    override var outlineColor: String = ""
    override fun Draw():String {
        return "I am a Circle. My Fill is $fillColor and My Outline is $outlineColor"
    }

}
class Rectangle:iShape{
    override var fillColor:String = ""
    override var outlineColor: String = ""
    override fun Draw():String {
        return "I am a Rectangle. My Fill is $fillColor and My Outline is $outlineColor"
    }

}
class Square:iShape by Rectangle()
