package com.e.feedbackdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
RatingBar fb;
TextView res;
RelativeLayout rl;
Button but;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        but=findViewById(R.id.but);
        rl=findViewById(R.id.rl);
        fb=findViewById(R.id.fb);
        res=findViewById(R.id.res);
        fb.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {

                //res.setText("Rating is "+v);
                int getrating=(int)v;
                //res.setText("Rating is "+getrating);
                switch (getrating)
                {
                    case 1:
                        but.setVisibility(View.INVISIBLE);
                        res.setText("You are number one");
                        rl.setBackgroundColor(Color.parseColor("#EBC7BF"));
                        res.setTextColor(Color.parseColor("#F82C00"));
                        res.setBackgroundColor(Color.YELLOW);
                        Toast.makeText(MainActivity.this, "Rated 1........", Toast.LENGTH_LONG).show();
                        break;
                    case 2:
                        but.setVisibility(View.VISIBLE);
                        res.setText("you are number two");
                        rl.setBackgroundColor(Color.parseColor("#BF9B94"));
                        Toast.makeText(MainActivity.this, "Rated 2........", Toast.LENGTH_LONG).show();
                        break;
                }

            }
        });
    }
}