package com.example.kotlin_first

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView



class MainActivity2 : AppCompatActivity() {
    lateinit var imageView:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        imageView=findViewById(R.id.imageView)
        imageView.setOnClickListener{
            val intent= Intent (applicationContext,MainActivity3::class.java)
            startActivity(intent)
        }
    }
}