package com.e.studentlist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
 ListView sdata;
 String[] name={"Avantika Rewtani","shivani shruti","PUJA VERMA","Manan Khare","Kushagra Shrivastava"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sdata=findViewById(R.id.sdata);
        ArrayAdapter ad=new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1,name);
        sdata.setAdapter(ad);
        sdata.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, ""+name[i], Toast.LENGTH_SHORT).show();
            }
        });


    }
}