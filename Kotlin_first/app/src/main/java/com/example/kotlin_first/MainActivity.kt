package com.example.kotlin_first

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var button:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button=findViewById(R.id.button)
        button.setOnClickListener{
            Toast.makeText(applicationContext,"Hello World",Toast.LENGTH_LONG).show()
            val intent=Intent (applicationContext,MainActivity2::class.java)
            startActivity(intent)
        }

    }
}