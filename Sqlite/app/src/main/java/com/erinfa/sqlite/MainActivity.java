package com.erinfa.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
EditText editText,editText1;
TextView textView,textView1;
Button button,button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=findViewById(R.id.editText);
        editText1=findViewById(R.id.editText1);
        textView=findViewById(R.id.textView);
        textView1=findViewById(R.id.textView1);
        button=findViewById(R.id.button);
        button1=findViewById(R.id.button1);
        ListView listView = findViewById(R.id.list);
        ArrayList<String> list = new ArrayList<String>();
        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase("Student",MODE_PRIVATE,null);
            sqLiteDatabase.execSQL("create table if not exists student_info(name varchar(50),id varchar(10))");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String edittexttxt=editText.getText().toString();
                String edittext1txt=editText1.getText().toString();
                sqLiteDatabase.execSQL("insert into student_info values('"+edittexttxt+"','"+edittext1txt+"')");
            }
        });

        
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String val="select * from student_info";
                Cursor cursor=sqLiteDatabase.rawQuery(val,null);

              if (cursor.getCount()!=0){
                  cursor.moveToNext();
                  while (cursor.moveToNext()){
                      list.add(cursor.getString(0));
                     // list.add(cursor.getString(1));
                      Toast.makeText(MainActivity.this, cursor.getString(0)+"", Toast.LENGTH_SHORT).show();
                  }
              }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,list);
              listView.setAdapter(adapter);
              adapter.notifyDataSetChanged();


            }
        });
    }
}