package com.e.designdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
LinearLayout mylay;
Button first,second;
//declaring variables
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//setting UI
        mylay=findViewById(R.id.l1);
        first=findViewById(R.id.b1);
        second=findViewById(R.id.b2);
        //defining variables
        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //mylay.setBackgroundResource(R.drawable.img);
                mylay.setBackgroundColor(Color.RED);
            }
        });


    }
}