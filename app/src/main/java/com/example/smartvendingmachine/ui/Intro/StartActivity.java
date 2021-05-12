package com.example.smartvendingmachine.ui.Intro;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartvendingmachine.LoginActivity;
import com.example.smartvendingmachine.MainActivity;
import com.example.smartvendingmachine.R;
import com.example.smartvendingmachine.ui.Guide.GuideActivity;

public class StartActivity extends AppCompatActivity {

    private Handler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        handler = new Handler();

        //최초 실행 여부 판단하는 구문
        SharedPreferences pref = getSharedPreferences("isFirst", Activity.MODE_PRIVATE);
        boolean first = pref.getBoolean("isFirst", false);
        if(first==false){
            Log.d("Is first Time?", "first");
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("isFirst",true);
            editor.commit();


            //앱 최초 실행시 하고 싶은 작업
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(getApplicationContext(), GuideActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 800);

        }else{
            Log.d("Is first Time?", "not first");
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 800);

        }
    }

}
