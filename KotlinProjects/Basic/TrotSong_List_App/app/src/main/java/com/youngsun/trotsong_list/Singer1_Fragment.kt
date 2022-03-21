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


class Singer1_Fragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_singer1, container, false)

        val song_list = mutableListOf<String>()
        song_list.add("니가 왜 거기서 나와")
        song_list.add("이불")
        song_list.add("찐이야")
        song_list.add("비상")
        song_list.add("니가 왜 거기서 나와")
        song_list.add("이불")
        song_list.add("찐이야")
        song_list.add("비상")
        song_list.add("니가 왜 거기서 나와")
        song_list.add("이불")
        song_list.add("찐이야")
        song_list.add("비상")
        song_list.add("니가 왜 거기서 나와")
        song_list.add("이불")
        song_list.add("찐이야")
        song_list.add("비상")


        val rv = view.findViewById<RecyclerView>(R.id.songRV)
        val rvAdapter = SongRVAdapter(song_list)

        rv.adapter = rvAdapter
        rv.layoutManager = LinearLayoutManager(context)

        view.findViewById<ImageView>(R.id.image2).setOnClickListener {
            it.findNavController().navigate(R.id.action_singer1_Fragment_to_singer2_Fragment2)
        }

        view.findViewById<ImageView>(R.id.image3).setOnClickListener {
            it.findNavController().navigate(R.id.action_singer1_Fragment_to_singer3_Fragment)
        }
        return view
}

}