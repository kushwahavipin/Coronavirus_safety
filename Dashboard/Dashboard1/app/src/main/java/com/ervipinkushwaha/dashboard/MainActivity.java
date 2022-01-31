package com.ervipinkushwaha.dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    Button button;
    TextView name;
    DBHelper dbHelper;
    String pName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper =new DBHelper(getApplicationContext());

        name=findViewById(R.id.name);
       // Intent intent=getIntent("user");

        Intent intent =  getIntent();
        pName = intent.getStringExtra("userName");

        name.setText(pName);




    }
}