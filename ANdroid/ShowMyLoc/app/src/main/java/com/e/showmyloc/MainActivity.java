package com.e.showmyloc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
   LocationManager lm;
   LocationListener ll;
   TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lm= (LocationManager) getSystemService(LOCATION_SERVICE);
        tv=findViewById(R.id.tv);
        ll=new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                 tv.setText("Latitude : "+location.getLatitude()
                         +"\nLongitude : "+location.getLongitude()
                 +"\nSpeed : "+location.getSpeed()
                 +"\nAccuracy : "+location.getAccuracy()+
                 "\nHeight : "+location.getAltitude());
            }
        };
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0.0f,ll);

    }
}