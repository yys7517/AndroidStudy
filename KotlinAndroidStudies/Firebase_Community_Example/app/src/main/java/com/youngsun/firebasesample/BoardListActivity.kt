package com.youngsun.firebasesample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class BoardListActivity : AppCompatActivity() {

    private lateinit var boardAdapter : BoardAdapter
    private val items = mutableListOf<BoardModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_list)

        val btnWrite = findViewById<Button>(R.id.btnWrite)


        btnWrite.setOnClickListener {

            val intent = Intent( this, BoardWriteActivity::class.java )
            startActivity(intent)
        }

        boardAdapter = BoardAdapter( items )

        val boardList = findViewById<ListView>(R.id.boardListView)
        boardList.adapter = boardAdapter

        getData()       // 게시글 목록 가져오기.

    }

    fun getData() {

        val database = Firebase.database
        val postReference = database.getReference("board")

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                items.clear()       // 기존 아이템들을 모두 비운다.

                // 게시글 정보들을 아이템에 담는다.
                for( dataModel in dataSnapshot.children ) {
                    val post = dataModel.getValue<BoardModel>()

                    val title = post!!.title
                    val contents = post!!.contents

                    items.add( BoardModel( title, contents ))
                }

                boardAdapter.notifyDataSetChanged()
            }


            override fun onCancelled(databaseError: DatabaseError) {

            }
        }
        postReference.addValueEventListener(postListener)
    }
}