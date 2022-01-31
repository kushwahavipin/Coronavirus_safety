package com.example.implementrecyclerviewwithcardview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;

import java.util.ArrayList;

public class DesignRecyclerView extends AppCompatActivity {
RecyclerView recyclerView;
ArrayList<MainModel> mainModels;
MainAdapter mainAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design_recycler_view);
        recyclerView=findViewById(R.id.recycler_view);
        Integer[] numberImages={R.drawable.a,R.drawable.b,
                R.drawable.c,R.drawable.d,R.drawable.e,
                R.drawable.f};
        String[] numberWords={"One","Two","Three","Four","Five","Six"};

        mainModels=new ArrayList<>();
        for (int i=0;i<numberImages.length;i++){
            MainModel mainModel=new MainModel(numberImages[i],numberWords[i]);
            this.mainModels.add(mainModel);
        }
        RecyclerView.LayoutManager layoutManager=new StaggeredGridLayoutManager(
                2,StaggeredGridLayoutManager.VERTICAL
        );
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        mainAdapter=new MainAdapter(DesignRecyclerView.this,mainModels);

        recyclerView.setAdapter(mainAdapter);
    }
}