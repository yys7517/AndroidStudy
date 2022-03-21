package com.sungkyul.osan;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Copyrights extends AppCompatActivity {

    private TextView copyrights, opensource, show;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.copyrights);

        copyrights = findViewById(R.id.copyrights);
        opensource = findViewById(R.id.opensource);
        show = findViewById(R.id.show);
        show.setMovementMethod(new ScrollingMovementMethod());


        final String copyrightstext, opensourcetext;

        copyrightstext = "정보 제공처 : 오산시 남부 장애인 주간 보호센터\n" +
                "문의사항은 개발자에게 연락해주세요.\n" +
                "tkdfuf7600@naver.com\n" +
                "qodydgus97@naver.com\n" +
                "yys7517@naver.com\n" +
                "lhh1120@gmail.com";
        opensourcetext = "주변 시설 지도로 확인하기 : 카카오 맵 API";

        copyrights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(show.getText().toString().equals("") || show.getText().toString().equals(opensourcetext)) {
                    show.setVisibility(View.VISIBLE);
                    show.setText(copyrightstext);
                }
                else{
                    show.setText("");
                    show.setVisibility(View.INVISIBLE);
                }

            }
        });

        opensource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(show.getText().toString().equals("") || show.getText().toString().equals(copyrightstext))
                {
                    show.setVisibility(View.VISIBLE);
                    show.setText(opensourcetext);

                }
                else{
                    show.setText("");
                    show.setVisibility(View.INVISIBLE);
                }

            }
        });


    }
}
