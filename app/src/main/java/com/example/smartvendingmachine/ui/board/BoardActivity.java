package com.example.smartvendingmachine.ui.board;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartvendingmachine.R;

public class BoardActivity extends AppCompatActivity {

    private ImageView backspace;

    TextView username;
    String TAG = "유저 이름 테스트";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.board_activity);

        //유저 아이디 넘겨주는 코드
        username = findViewById(R.id.txtCommentNick);
        Intent intent = getIntent();
        String test = intent.getStringExtra("name");
        Log.d(TAG, test);
        username.setText(test);


        /// 뒤로가기 버튼
        backspace = findViewById(R.id.backspace);

        backspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
