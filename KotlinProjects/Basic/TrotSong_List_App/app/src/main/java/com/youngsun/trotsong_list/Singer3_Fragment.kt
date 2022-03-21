package com.youngsun.trotsong_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class Singer3_Fragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        var view = inflater.inflate(R.layout.fragment_singer3, container, false)

        val song_list = mutableListOf<String>()
        song_list.add("피어나")
        song_list.add("진실 혹은 대답")
        song_list.add("우리 사랑하게 됐어요")
        song_list.add("피어나")
        song_list.add("진실 혹은 대답")
        song_list.add("우리 사랑하게 됐어요")
        song_list.add("피어나")
        song_list.add("진실 혹은 대답")
        song_list.add("우리 사랑하게 됐어요")
        song_list.add("피어나")
        song_list.add("진실 혹은 대답")
        song_list.add("우리 사랑하게 됐어요")


        val rv = view.findViewById<RecyclerView>(R.id.songRV)
        val rvAdapter = SongRVAdapter(song_list)

        rv.layoutManager = LinearLayoutManager(context)
        rv.adapter = rvAdapter

        view.findViewById<ImageView>(R.id.image1).setOnClickListener {
            it.findNavController().navigate(R.id.action_singer3_Fragment_to_singer1_Fragment)
        }

        view.findViewById<ImageView>(R.id.image2).setOnClickListener {
            it.findNavController().navigate(R.id.action_singer3_Fragment_to_singer2_Fragment2)
        }

        return view
    }

}