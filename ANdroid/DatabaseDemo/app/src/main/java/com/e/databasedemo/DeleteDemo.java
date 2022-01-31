package com.e.databasedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DeleteDemo extends AppCompatActivity {
 EditText dph;
 Button dstore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_demo);
        dph=findViewById(R.id.dph);
        dstore=findViewById(R.id.dstore);
        dstore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             StoreHelper sh=new StoreHelper(DeleteDemo.this);
             sh.delete(dph.getText().toString());
            }
        });
    }
}