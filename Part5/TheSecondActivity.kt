package com.example.admin.myfirstkotlinapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_the_second.*

class TheSecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_the_second)
        txt.text = Helper.DisplayString
        val buttonClickListener = View.OnClickListener { view ->
           back()
        }
        back.setOnClickListener(buttonClickListener)
    }
    fun back(){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
}
