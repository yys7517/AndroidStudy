package com.sungkyul.osan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView baldal,jungsin,service,carservice,association,mapimg,welfare,job,etc;
    TextView baldaltext,jungsintext,servicetext,carservicetext,associationtext,maptext,welfaretext,jobtext,etctext,allfacil;
    TextView copyrights,sosik;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        copyrights = findViewById(R.id.info);

        copyrights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Copyrights.class);
                startActivity(intent);
            }
        });

        allfacil = findViewById(R.id.allfacil);

        allfacil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CategoryClick.class);
                intent.putExtra("category","모든 시설 조회");
                startActivity(intent);
            }
        });

        baldal = findViewById(R.id.baldal);
        baldaltext = findViewById(R.id.baldaltext);

        baldal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CategoryClick.class);
                intent.putExtra("category",baldaltext.getText().toString());
                startActivity(intent);
            }
        });
        baldaltext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CategoryClick.class);
                intent.putExtra("category",baldaltext.getText().toString());
                startActivity(intent);
            }
        });

        jungsin = findViewById(R.id.jungsin);
        jungsintext = findViewById(R.id.jungsintext);

        jungsin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CategoryClick.class);
                intent.putExtra("category",jungsintext.getText().toString());
                startActivity(intent);
            }
        });
        jungsintext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CategoryClick.class);
                intent.putExtra("category",jungsintext.getText().toString());
                startActivity(intent);
            }
        });

        service = findViewById(R.id.service);
        servicetext = findViewById(R.id.servicetext);

        service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CategoryClick.class);
                intent.putExtra("category",servicetext.getText().toString());
                startActivity(intent);
            }
        });
        servicetext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CategoryClick.class);
                intent.putExtra("category",servicetext.getText().toString());
                startActivity(intent);
            }
        });

        carservice = findViewById(R.id.carservice);
        carservicetext = findViewById(R.id.carservicetext);

        carservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CarService.class);
                intent.putExtra("category",carservicetext.getText().toString());
                startActivity(intent);
            }
        });
        carservicetext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CarService.class);
                intent.putExtra("category",carservicetext.getText().toString());
                startActivity(intent);
            }
        });

        association = findViewById(R.id.association);
        associationtext = findViewById(R.id.associationtext);

        association.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CategoryClick.class);
                intent.putExtra("category",associationtext.getText().toString());
                startActivity(intent);
            }
        });
        associationtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CategoryClick.class);
                intent.putExtra("category",associationtext.getText().toString());
                startActivity(intent);
            }
        });


        mapimg = findViewById(R.id.mapimg); //map 이미지버튼 아이디
        maptext = findViewById(R.id.maptext);

        mapimg.setOnClickListener(new View.OnClickListener() { //map 이미지 선택시 map 인텐트
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), com.sungkyul.osan.map.Kakaomap.class);
                startActivity(intent);
            }
        });
        maptext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), com.sungkyul.osan.map.Kakaomap.class);
                startActivity(intent);
            }
        });

        welfare = findViewById(R.id.welfare);
        welfaretext = findViewById(R.id.welfaretext);

        welfare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CategoryClick.class);
                intent.putExtra("category",welfaretext.getText().toString());
                startActivity(intent);
            }
        });
        welfaretext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CategoryClick.class);
                intent.putExtra("category",welfaretext.getText().toString());
                startActivity(intent);
            }
        });

        job = findViewById(R.id.job);
        jobtext = findViewById(R.id.jobtext);

        job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CategoryClick.class);
                intent.putExtra("category",jobtext.getText().toString());
                startActivity(intent);
            }
        });
        jobtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CategoryClick.class);
                intent.putExtra("category",jobtext.getText().toString());
                startActivity(intent);
            }
        });

        etc = findViewById(R.id.etc);
        etctext = findViewById(R.id.etctext);

        etc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CategoryClick.class);
                intent.putExtra("category",etctext.getText().toString());
                startActivity(intent);
            }
        });
        etctext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CategoryClick.class);
                intent.putExtra("category",etctext.getText().toString());
                startActivity(intent);
            }
        });

        sosik = findViewById(R.id.sosik);
        sosik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SosikActivity.class);
                intent.putExtra("title","오산시 소식");
                startActivity(intent);
            }
        });
    }
}