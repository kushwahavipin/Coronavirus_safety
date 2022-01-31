package com.e.mobilestore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
  ListView mlist;
  Integer[] img={R.drawable.htc,R.drawable.mi,R.drawable.oneplusnord,R.drawable.sgalaxyf,R.drawable.vivo};
  String [] name={"HTC","MI","OnePlus Nord","Samsung","Vivo"};
  String [] price={"10000","27000","29999","17000","15000"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mlist=findViewById(R.id.mlist);
        MyAdapter ma=new MyAdapter(this,img,name,price);
        mlist.setAdapter(ma);
        mlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, ""+price[i], Toast.LENGTH_SHORT).show();
            }
        });


    }
}