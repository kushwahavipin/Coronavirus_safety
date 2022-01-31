package com.erinfa.fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {
    lateinit var mBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mBtn=findViewById(R.id.next)
        mBtn.setOnClickListener{
        var fragmentManager:FragmentManager=supportFragmentManager
            var fragmentTransaction:FragmentTransaction=fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.mainScreen,FragmentOne())
            fragmentTransaction.commit()
        }
    }
}