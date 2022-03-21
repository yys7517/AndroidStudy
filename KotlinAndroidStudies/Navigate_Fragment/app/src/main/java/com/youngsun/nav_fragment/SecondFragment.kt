package com.youngsun.nav_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController

class SecondFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_second, container, false)

        val btn1 = view.findViewById<Button>(R.id.btn1)
        val btn3 = view.findViewById<Button>(R.id.btn3)

        btn1.setOnClickListener{
            it.findNavController().navigate(R.id.action_secondFragment_to_firstFragment)
        }

        btn3.setOnClickListener {
            it.findNavController().navigate(R.id.action_secondFragment_to_thirdFragment)
        }

        return view
    }

}