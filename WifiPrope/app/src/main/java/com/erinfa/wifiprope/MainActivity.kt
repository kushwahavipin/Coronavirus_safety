package com.erinfa.wifiprope

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.content.pm.PackageManager
import android.net.wifi.WifiManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnWifiOn=findViewById<Button>(R.id.button);
        val btnBTOn=findViewById<Button>(R.id.button1);
        val btnBTOff=findViewById<Button>(R.id.button2);

        btnWifiOn.setOnClickListener{
//            val wifiManager:WifiManager=applicationContext.getSystemService(WIFI_SERVICE) as WifiManager
//            wifiManager.setWifiEnabled(true)
            if(Build.VERSION.SDK_INT<Build.VERSION_CODES.Q){
                val wifiManager:WifiManager=applicationContext.getSystemService(WIFI_SERVICE) as WifiManager
                wifiManager.setWifiEnabled(true)
            }else{
                var intent:Intent=Intent(Settings.Panel.ACTION_WIFI)
                startActivityForResult(intent,1)
            }
        }
        var bluetoothAdapter:BluetoothAdapter=BluetoothAdapter.getDefaultAdapter()
 var registerBT=registerForActivityResult(ActivityResultContracts.StartActivityForResult())
 {
     result->if (result.resultCode== RESULT_OK){

 }
 }
        btnBTOn.setOnClickListener{
            if (!bluetoothAdapter.isEnabled){
                var intent:Intent=Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            //startActivityForResult(intent,0)
                registerBT.launch(intent)
            }


        }
        btnBTOff.setOnClickListener{
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.BLUETOOTH_CONNECT
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return@setOnClickListener
            }
            bluetoothAdapter.disable()
        }

    }
}