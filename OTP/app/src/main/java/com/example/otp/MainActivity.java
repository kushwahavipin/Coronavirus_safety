package com.example.otp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
EditText editText;
Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=findViewById(R.id.inputMobile);
        button=findViewById(R.id.otpBtn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editText.getText().toString().trim().isEmpty())
                {
                    editText.setError("Enter Phone No");
                }else{
                    Intent i = new Intent(getApplicationContext(), OtpVerification.class);
                    i.putExtra("phone",editText.getText().toString().trim());

                    startActivity(i);
                    finish();
                }
            }
        });
    }
}