package com.e.dropstudent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Spinner sdata;
    String[] name={"Avantika Rewtani","shivani shruti","PUJA VERMA","Manan Khare","Kushagra Shrivastava"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sdata=findViewById(R.id.sdata);
        ArrayAdapter ad=new ArrayAdapter(MainActivity.this,
                android.R.layout.simple_dropdown_item_1line,
                name);
        sdata.setAdapter(ad);
        sdata.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, ""+name[i], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        
    }
}