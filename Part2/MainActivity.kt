package com.example.admin.myfirstkotlinapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        numList.text = numList(5)
        evenList.text = evenList(5)
        oddList.text = oddList(5)
    }
     fun numList (n:Int):String
     {
         var S = 0
         var str = ""
         for(i in 1..n)
         {
             S = S + i
             str += " $i"
         }

         return "Sum of [ $str ] = $S"
     }
    fun evenList (n:Int):String
    {
        var S = 0
        var str = ""
        for(i in 1..n)
        {
            if(i%2 == 0)
            {
                S = S + i
                str += " $i"
            }
        }

        return "Sum of [ $str ] = $S"
    }
    fun oddList (n:Int):String
    {
        var S = 0
        var str = ""
        for(i in 1..n)
        {
            if(i%2 != 0)
            {
                S = S + i
                str += " $i"
            }
        }

        return "Sum of [ $str ] = $S"
    }
}
