package com.e.databasedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayDemo extends AppCompatActivity {
 EditText lphone,lpwd;
 Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_demo);
        lphone=findViewById(R.id.lphone);
        lpwd=findViewById(R.id.lpwd);
        login=findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StoreHelper sh=new StoreHelper(DisplayDemo.this);
             if(sh.checkuser(lphone.getText().toString(),lpwd.getText().toString())) /// boolean
             {
                 Intent gth=new Intent(DisplayDemo.this,Home.class);
                 startActivity(gth);
             }
             else
             {
                 Toast.makeText(DisplayDemo.this, "Sorry", Toast.LENGTH_SHORT).show();
             }
            }
        });

    }
}