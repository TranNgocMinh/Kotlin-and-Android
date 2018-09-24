package com.ngocminhtran.basicviews;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class BasicViewsActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_views);

        Button btnCancel =  (Button)findViewById(R.id.btnCancel);
        Button btnInfo =  (Button)findViewById(R.id.btnInfo);
        final EditText editName =  (EditText)findViewById(R.id.editName);
        final EditText editEmail =  (EditText)findViewById(R.id.editEmail);
        final CheckBox chkCoding =  (CheckBox) findViewById(R.id.chkCoding);
        final CheckBox chkReading =  (CheckBox) findViewById(R.id.chkReading);
        final CheckBox chkTravelling =  (CheckBox) findViewById(R.id.chkTravelling);
        final RadioButton rdMale =  (RadioButton) findViewById(R.id.rdMale);
        final RadioButton rdFemale =  (RadioButton) findViewById(R.id.rdFemale);
        final Spinner spinLang =  (Spinner) findViewById(R.id.spinLang);
        final Switch switchExpert =  (Switch) findViewById(R.id.switchExpert);



        btnCancel.setOnClickListener(new View.OnClickListener() {
                                      // thực thi phương thức callback onClick
                                      public void onClick(View v) {
                                          // nội dung thực thi
                                          cancel(v);
                                      }
                                  }
        );
        btnInfo.setOnClickListener(new View.OnClickListener() {
                                      // thực thi phương thức callback onClick
                                      public void onClick(View v) {
                                          // nội dung thực thi
                                         String msg = "My name: " + editName.getText().toString() +
                                                        System.getProperty("line.separator") +
                                                        "Email: " + editEmail.getText().toString();
                                         msg += System.getProperty("line.separator") + "My hobbies: ";
                                         if(chkCoding.isChecked())
                                             msg +=  chkCoding.getText().toString()+ ", ";
                                          if(chkReading.isChecked())
                                              msg += chkReading.getText().toString() + ", ";
                                          if(chkTravelling.isChecked())
                                              msg += chkTravelling.getText().toString()+ ", ";
                                          msg += System.getProperty("line.separator") + "My Sex: ";
                                          if(rdFemale.isChecked())
                                              msg += rdFemale.getText().toString();
                                          if(rdMale.isChecked())
                                              msg += rdMale.getText().toString();
                                          msg += System.getProperty("line.separator") + "My language: " + spinLang.getSelectedItem().toString();
                                          msg += System.getProperty("line.separator") + "Do you have experience with Jave frameworks: ";
                                          if(switchExpert.isChecked())
                                              msg += "Yes";
                                          else
                                              msg += "No";
                                         info(v,msg);
                                          editName.setText("");
                                          editEmail.setText("");
                                          chkCoding.setChecked(false);
                                          chkReading.setChecked(false);
                                          chkTravelling.setChecked(false);
                                          rdFemale.setChecked(false);
                                          rdMale.setChecked(false);
                                      }
                                  }
        );



    }
    public void cancel(View v){
        System.exit(0);
    }
    public void info(View v, String msg){
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
        dlgAlert.setMessage(msg);
        dlgAlert.setTitle("My First App");
        dlgAlert.setPositiveButton("OK", null);
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
    }
}
