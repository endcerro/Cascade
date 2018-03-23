package com.example.enzo.cascade;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ScoreActivity extends Activity {

    TextView score1;
    TextView score2;
    TextView score3;
    TextView score4;
    TextView score5;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        sharedPref = this.getSharedPreferences("scores",Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        score1 = (TextView) findViewById(R.id.lv1s);
        score2 = (TextView) findViewById(R.id.lv2s);
        score3 = (TextView) findViewById(R.id.lv3s);
        score4 = (TextView) findViewById(R.id.lv4s);
        score5 = (TextView) findViewById(R.id.lv5s);

     //   if(sharedPref.contains("1"))
       // {
            //System.out.println(sharedPref.getInt("",0));
            score1.setText(""+sharedPref.getInt("1.0",0));
        //}
       /// else
        //{
          //  System.out.println("nope");
           // editor.putInt("1",0);
        //}

        if(sharedPref.contains("2.0"))
        {
            score2.setText(""+sharedPref.getInt("2.0",0));
        }
        else
        {
            editor.putInt("2",0);
        }
        if(sharedPref.contains("3.0"))
        {
            score3.setText(""+sharedPref.getInt("3.0",0));
        }
        else
        {
            editor.putInt("3",0);
        }
        if(sharedPref.contains("4.0"))
        {
            score4.setText(""+sharedPref.getInt("4.0",0));
        }
        else
        {
            editor.putInt("4",0);
        }
        if(sharedPref.contains("5.0"))
        {
            score5.setText(""+sharedPref.getInt("5.0",0));
        }
        else
        {
            editor.putInt("5",0);
        }
    }
}
