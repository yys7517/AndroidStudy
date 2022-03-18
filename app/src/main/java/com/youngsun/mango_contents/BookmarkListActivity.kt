package com.youngsun.mango_contents

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class BookmarkListActivity : AppCompatActivity() {
    private lateinit var auth : FirebaseAuth
    private var items = mutableListOf<ContentsModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bookmark)

        auth = Firebase.auth
        val uid = auth.currentUser!!.uid

        val database = Firebase.database
        val getBookmarkPath = database.getReference("bookmark_ref").child( uid )   // path

        val recyclerview = findViewById<RecyclerView>(R.id.rv)
        val rvAdapter = RVAdapter( baseContext ,items )
        rvAdapter.notifyDataSetChanged()

        recyclerview.adapter = rvAdapter
        recyclerview.layoutManager = GridLayoutManager(this, 2 )

        getBookmarkPath.addValueEventListener( object : ValueEventListener {
            override fun onDataChange( snapshot: DataSnapshot ) {
                for( dataModel in snapshot.children ) {
                    items.add( dataModel.getValue(ContentsModel::class.java)!! )
                }
                rvAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(baseContext, "북마크 불러오기 실패 !", Toast.LENGTH_SHORT).show()
            }
        })

        Toast.makeText(baseContext, "북마크 불러오기 성공 !", Toast.LENGTH_SHORT).show()

        // 북마크 목록에서도 RecyclerView 클릭 이벤트 구현 해보기 .
        // RecyclerView 아이템 클릭 이벤트
        rvAdapter.itemClick = object  : RVAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                val intent = Intent( baseContext, BookmarkViewActivity::class.java )
                intent.putExtra("url",items[position].url)
                startActivity(intent)
            }
        }


    }
}