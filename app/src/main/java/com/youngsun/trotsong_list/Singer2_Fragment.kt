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


class Singer2_Fragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        var view = inflater.inflate(R.layout.fragment_singer2, container, false)

        val song_list = mutableListOf<String>()
        song_list.add("별빛 같은 나의 사랑")
        song_list.add("사랑의 콜센터")
        song_list.add("영웅시대")
        song_list.add("이제 나만 믿어요")
        song_list.add("사랑은 늘 도망가")
        song_list.add("별빛 같은 나의 사랑")
        song_list.add("사랑의 콜센터")
        song_list.add("영웅시대")
        song_list.add("이제 나만 믿어요")
        song_list.add("사랑은 늘 도망가")
        song_list.add("별빛 같은 나의 사랑")
        song_list.add("사랑의 콜센터")
        song_list.add("영웅시대")
        song_list.add("이제 나만 믿어요")
        song_list.add("사랑은 늘 도망가")


        val rv = view.findViewById<RecyclerView>(R.id.songRV)
        val rvAdapter = SongRVAdapter(song_list)

        rv.adapter = rvAdapter
        rv.layoutManager = LinearLayoutManager(context)

        view.findViewById<ImageView>(R.id.image1).setOnClickListener {
            it.findNavController().navigate(R.id.action_singer2_Fragment2_to_singer1_Fragment)
        }

        view.findViewById<ImageView>(R.id.image3).setOnClickListener {
            it.findNavController().navigate(R.id.action_singer2_Fragment2_to_singer3_Fragment)
        }

        return view
    }

}