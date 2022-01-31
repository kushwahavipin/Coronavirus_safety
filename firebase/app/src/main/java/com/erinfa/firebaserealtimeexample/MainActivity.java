package com.erinfa.firebaserealtimeexample;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
EditText editText;
Button btnSubmit,btVar1,btVar2,next;
TextView textView;
RelativeLayout rlOne;
RadioGroup radioBtn1;
RadioButton male,female;
int i=0;

    boolean isPressed=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=findViewById(R.id.editText);
        btnSubmit=findViewById(R.id.btnSubmit);
        textView=findViewById(R.id.textView);
        btVar1=findViewById(R.id.btVar1);
        btVar2=findViewById(R.id.btVar2);
        rlOne=findViewById(R.id.RelId);
        next=findViewById(R.id.next);
        isPressed=!isPressed;

        radioBtn1=findViewById(R.id.radioBtn);
        male=findViewById(R.id.male);
        female=findViewById(R.id.female);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainActivity2.class));
            }
        });

        btVar1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                textView.setTextColor((getColor(R.color.black)));
                textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
                textView.setTextSize(25);
            }
        });
        btVar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPressed){
                    rlOne.setBackgroundResource(R.drawable.color_btn_2);
                }else {
                    rlOne.setBackgroundResource(R.drawable.color_btn_3);
                }
                isPressed=!isPressed;
            }
        });

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
        DatabaseReference myRef1 = database.getReference("Gender");
        String s1="Play";

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String edit=editText.getText().toString();
                int selectedId = radioBtn1.getCheckedRadioButtonId();
                radioBtn1 = findViewById(selectedId);

                if(selectedId==-1){
                    myRef1.setValue("Male"+male);
                    //Toast.makeText(MainActivity.this,"Female", Toast.LENGTH_SHORT).show();
                }
                else{
                    myRef1.setValue("Female"+radioBtn1);
                    //Toast.makeText(MainActivity.this,male.getText(), Toast.LENGTH_SHORT).show();
                }

                myRef.setValue(edit);

                if (s1.equals(edit)){
                    MediaPlayer ring= MediaPlayer.create(MainActivity.this,R.raw.song);
                    ring.start();
                }

            }
        });


         myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String value = dataSnapshot.getValue(String.class);
                textView.setText(value);


            }

            @Override
            public void onCancelled(DatabaseError error) {


            }
        });

    }
}