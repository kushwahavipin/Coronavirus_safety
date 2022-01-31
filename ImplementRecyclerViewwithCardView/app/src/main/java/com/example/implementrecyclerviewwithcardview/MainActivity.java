 package com.example.implementrecyclerviewwithcardview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

 public class MainActivity extends AppCompatActivity {
        EditText editText;
        Button btSave,next;
        RecyclerView recyclerView;
        List<String> itemList=new ArrayList<>();
        CustomAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText=findViewById(R.id.ed_text);
        btSave=findViewById(R.id.bt_save);
        next=findViewById(R.id.next);
        recyclerView=findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter=new CustomAdapter(itemList);
        recyclerView.setAdapter(adapter);

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s=editText.getText().toString();
                if (s.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Enter Text !!!", Toast.LENGTH_SHORT).show();
                }else {
                    try {
                        String s1=editText.getText().toString();
                        itemList.add(s1);
                        editText.setText("");
                        adapter.notifyItemInserted(itemList.size()-1);
                    }catch (Exception ignored){

                    }
                }

            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"DesignRecyclerView",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), DesignRecyclerView.class);
                startActivity(intent);
            }
        });
    }
}