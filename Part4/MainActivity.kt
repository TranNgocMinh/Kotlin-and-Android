package com.example.admin.myfirstkotlinapp
import android.view.View
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var Val1: Double = 0.0
        var Val2: Double = 0.0
        var Result: Double = 0.0
        var ResultDisplay:String = ""
        var str1:String =""
        var str2:String=""

        val buttonClickListener = View.OnClickListener { view ->
            var numeric = true
            str1 = value1.text.toString()
            str2 = value2.text.toString()

            //Check if a string is numeric or not using regular expressions (regex)
            numeric = str1.matches("-?\\d+(\\.\\d+)?".toRegex()) && str2.matches("-?\\d+(\\.\\d+)?".toRegex())
             if(numeric){
                Val1 = value1.text.toString().toDouble()
                 Val2 = value2.text.toString().toDouble()
             }
            else{
                 Val1 = 0.0
                 Val2 = 0.0
             }

            if(add.isChecked)
                 Result = eval(Expr.Add(Expr.Num(Val1), Expr.Num(Val2)))
            if(sub.isChecked)
                Result = eval(Expr.Sub(Expr.Num(Val1), Expr.Num(Val2)))
            if(mul.isChecked)
                Result = eval(Expr.Mul(Expr.Num(Val1), Expr.Num(Val2)))
            if(div.isChecked)
                Result = eval(Expr.Div(Expr.Num(Val1), Expr.Num(Val2)))


            ResultDisplay = String.format("%.1f", Result)
            result.setText(ResultDisplay)
            value1.text.clear()
            value2.text.clear()

       }
            calculate.setOnClickListener(buttonClickListener)
    }
    fun eval(e: Expr): Double =
            when (e) {
                    is Expr.Num -> e.value
                    is Expr.Add -> eval(e.left) + eval(e.right)
                    is Expr.Sub -> eval(e.left) - eval(e.right)
                    is Expr.Mul -> eval(e.left) * eval(e.right)
                    is Expr.Div -> eval(e.left) / eval(e.right)
            }
}

sealed class Expr {
    class Num(val value: Double) : Expr()
    class Add(val left: Expr, val right: Expr) : Expr()
    class Sub(val left: Expr, val right: Expr) : Expr()
    class Mul(val left: Expr, val right: Expr) : Expr()
    class Div(val left: Expr, val right: Expr) : Expr()
}
