package com.erinfa.onlineschoolmangamentadmin.Faculty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.erinfa.onlineschoolmangamentadmin.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AddFaculty extends AppCompatActivity {
private FloatingActionButton floatingBtn;

private RecyclerView csDepartment,meDepartment,enDepartment;
private LinearLayout csNoData,meNoData,enNoData;
private List<TeacherData> list1,list2,list3;

private TeacherAdapter adapter;

private DatabaseReference reference,dbRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_faculty);

        csDepartment=findViewById(R.id.csDepartment);
        meDepartment=findViewById(R.id.meDepartment);
        enDepartment=findViewById(R.id.enDepartment);

        csNoData=findViewById(R.id.csNoData);
        meNoData=findViewById(R.id.meNoData);
        enNoData=findViewById(R.id.enNoData);

        reference= FirebaseDatabase.getInstance().getReference().child("teacher");

        csDepartment();
        meDepartment();
        enDepartment();

        floatingBtn=findViewById(R.id.floatingBtn);
        floatingBtn.setOnClickListener(view -> {
            Intent intent=new Intent(AddFaculty.this, AddTeachers.class);
            startActivity(intent);
        });


    }

    private void csDepartment() {
        dbRef=reference.child("CS");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list1=new ArrayList<>();
                if (!snapshot.exists()){
                    csNoData.setVisibility(View.VISIBLE);
                    csDepartment.setVisibility(View.GONE);

                }else {
                    csNoData.setVisibility(View.GONE);
                    csDepartment.setVisibility(View.VISIBLE);

                    for (DataSnapshot snapshot1: snapshot.getChildren()){
                        TeacherData data=snapshot1.getValue(TeacherData.class);
                        list1.add(data);
                    }
                    csDepartment.setHasFixedSize(true);
                    csDepartment.setLayoutManager(new LinearLayoutManager(AddFaculty.this));
                    adapter=new TeacherAdapter(list1,AddFaculty.this,"CS");
                    csDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AddFaculty.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void meDepartment() {
        dbRef=reference.child("ME");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list2=new ArrayList<>();
                if (!snapshot.exists()){
                    meNoData.setVisibility(View.VISIBLE);
                    meDepartment.setVisibility(View.GONE);

                }else {
                    meNoData.setVisibility(View.GONE);
                    meDepartment.setVisibility(View.VISIBLE);

                    for (DataSnapshot snapshot1: snapshot.getChildren()){
                        TeacherData data=snapshot1.getValue(TeacherData.class);
                        list2.add(data);
                    }
                    meDepartment.setHasFixedSize(true);
                    meDepartment.setLayoutManager(new LinearLayoutManager(AddFaculty.this));
                    adapter=new TeacherAdapter(list2,AddFaculty.this,"ME");
                    meDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AddFaculty.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void enDepartment() {
        dbRef=reference.child("EN");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list3=new ArrayList<>();
                if (!snapshot.exists()){
                    enNoData.setVisibility(View.VISIBLE);
                    enDepartment.setVisibility(View.GONE);

                }else {
                    enNoData.setVisibility(View.GONE);
                    enDepartment.setVisibility(View.VISIBLE);

                    for (DataSnapshot snapshot1: snapshot.getChildren()){
                        TeacherData data=snapshot1.getValue(TeacherData.class);
                        list3.add(data);
                    }
                    enDepartment.setHasFixedSize(true);
                    enDepartment.setLayoutManager(new LinearLayoutManager(AddFaculty.this));
                    adapter=new TeacherAdapter(list3,AddFaculty.this,"EN");
                    enDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AddFaculty.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}