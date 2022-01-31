package com.example.sharepre;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
EditText editText,editText1;
Button btnSubmit,btnSubmit1;
SharedPreferences sharedPreferences;
SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=findViewById(R.id.editText);
        editText1=findViewById(R.id.editText1);
        btnSubmit=findViewById(R.id.btnSubmit);
        btnSubmit1=findViewById(R.id.btnSubmit1);

        btnSubmit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),MainActivity2.class);
                startActivity(intent);
            }
        });

        sharedPreferences=getSharedPreferences("Student",0);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=editText.getText().toString();
                String name1=editText1.getText().toString();
                editor=sharedPreferences.edit();
                editor.putString("name",name);
                editor.putString("name1",name1);
                editor.commit();
            }
        });
    }
}