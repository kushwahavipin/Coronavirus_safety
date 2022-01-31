package com.erinfa.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction


class FragmentOne : Fragment() {
lateinit var image1:ImageView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view:View=inflater.inflate(R.layout.fragment_one, container, false)
        image1=view.findViewById(R.id.image1)
        image1.setOnClickListener{

            var fragmentManager:FragmentManager=requireActivity().supportFragmentManager
            var fragmentTransaction:FragmentTransaction=fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.mainScreen,FragmentTwo())
            fragmentTransaction.commit()
        }
        // Inflate the layout for this fragment
        return (view)
    }


}