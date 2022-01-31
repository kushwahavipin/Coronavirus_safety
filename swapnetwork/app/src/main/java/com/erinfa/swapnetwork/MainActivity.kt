package com.erinfa.swapnetwork

import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var button=findViewById<Button>(R.id.btnSubmit)
        var textView=findViewById<TextView>(R.id.textView)
        var swap=findViewById<SwipeRefreshLayout>(R.id.swap)
        swap.setOnRefreshListener {
            val cn=checkNetwork()
            if(cn){
                Toast.makeText(this,"Connected",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"Not Connected",Toast.LENGTH_SHORT).show()

            }
        }

        button.setOnClickListener{
            val cn=checkNetwork()
            if(cn){
                Toast.makeText(this,"Connected",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"Not Connected",Toast.LENGTH_SHORT).show()

            }
        }
    }

    private fun checkNetwork(): Boolean {
        var connectManager:ConnectivityManager=applicationContext.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        var networkInfo:NetworkInfo?=connectManager.activeNetworkInfo
        return networkInfo!=null
    }
}