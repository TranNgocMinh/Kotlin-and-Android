package com.ngocminhtran.javalayout;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import static android.view.Gravity.BOTTOM;

public class JavaLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_java_layout);

        //layout
        ConstraintLayout myLayout = new ConstraintLayout(this);
        myLayout.setBackgroundColor(Color.YELLOW);
        myLayout.setId(R.id.myLayoutId);

        //Button
        Button myButton = new Button(this);
        myButton.setBackgroundColor(Color.GREEN);
        myButton.setText("Press Me");
        myButton.setId(R.id.myButtonId);
        myLayout.addView(myButton);

        //EditText
        EditText myEditText = new EditText(this);
        myEditText.setBackgroundColor(Color.WHITE);
        myEditText.setId(R.id.myEditTextId);
        myEditText.setWidth(300);
        myLayout.addView(myEditText);


        // LayoutParams for Button
        ConstraintLayout.LayoutParams buttonParams = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
        );

        myButton.setLayoutParams(buttonParams);

        // LayoutParams for EditText
        ConstraintLayout.LayoutParams editParams = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
        );

        myEditText.setLayoutParams(editParams);



        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(myLayout);



        constraintSet.connect(myEditText.getId(), ConstraintSet.TOP, myLayout.getId(), ConstraintSet.TOP, 60);
        constraintSet.connect(myEditText.getId(), ConstraintSet.LEFT, myLayout.getId(), ConstraintSet.LEFT, 60);
         constraintSet.connect(myButton.getId(), ConstraintSet.TOP, myLayout.getId(), ConstraintSet.TOP, 120);
         constraintSet.connect(myButton.getId(), ConstraintSet.LEFT, myLayout.getId(), ConstraintSet.LEFT, 120);

        //constraintSet.centerHorizontally(myButton.getId(), myLayout.getId());
        //constraintSet.centerVertically(myButton.getId(), myLayout.getId());


        constraintSet.applyTo(myLayout);


        setContentView(myLayout);
    }
}
