package com.sungkyul.moji;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Sudokwon extends AppCompatActivity {

    static String tname;
    Cursor cursor;
    SQLiteDatabase db;
    private MyDBHelper mHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sudokwon);

        mHelper = new MyDBHelper(this, 1);
        db = mHelper.getWritableDatabase();

        final TextView line_1 = (TextView) findViewById(R.id.line_1);
        final TextView line_2= (TextView) findViewById(R.id.line_2);
        final TextView line_3 = (TextView) findViewById(R.id.line_3);
        final TextView line_4 = (TextView) findViewById(R.id.line_4);
        final TextView line_5 = (TextView) findViewById(R.id.line_5);
        final TextView line_6 = (TextView) findViewById(R.id.line_6);
        final TextView line_7 = (TextView) findViewById(R.id.line_7);
        final TextView line_8 = (TextView) findViewById(R.id.line_8);
        final TextView line_9 = (TextView) findViewById(R.id.line_9);
        final TextView line_kyeonggang = (TextView) findViewById(R.id.line_kyeonggang);
        final TextView line_kyongchun = (TextView) findViewById(R.id.line_kyongchun);
        final TextView line_airport = (TextView) findViewById(R.id.line_airport);
        final TextView line_bundang = (TextView) findViewById(R.id.line_bundang);
        final TextView line_sooin = (TextView) findViewById(R.id.line_sooin);
        final TextView line_newbundang = (TextView) findViewById(R.id.line_newbundang);
        final TextView line_ever = (TextView) findViewById(R.id.line_ever);
        final TextView line_wooee = (TextView) findViewById(R.id.line_wooee);
        final TextView line_euijungbu = (TextView) findViewById(R.id.line_euijungbu);
        final TextView line_incheon1 = (TextView) findViewById(R.id.line_incheon1);
        final TextView line_incheon2 = (TextView) findViewById(R.id.line_incheon2);
        final TextView line_magnet = (TextView) findViewById(R.id.line_magnet);
        final TextView line_center = (TextView) findViewById(R.id.line_center);
        final TextView location = (TextView)findViewById(R.id.locationname);


        final TextView test = (TextView) findViewById(R.id.test);


        final ListView listview = (ListView) findViewById(R.id.stationlistview);

        final ArrayList<String> list = new ArrayList<>();

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, list);

        line_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Sudokwonlist.class);
                test.setText("1호선");
                intent.putExtra("line_1", line_1.getText().toString());
                intent.putExtra("test", test.getText().toString());
                intent.putExtra("location",location.getText().toString());
                startActivity(intent);
            }
        });
        line_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Sudokwonlist.class);
                test.setText("2호선");
                intent.putExtra("line_2", line_2.getText().toString());
                intent.putExtra("test", test.getText().toString());
                intent.putExtra("location",location.getText().toString());
                startActivity(intent);
            }
        });
        line_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Sudokwonlist.class);
                test.setText("3호선");
                intent.putExtra("line_3", line_3.getText().toString());
                intent.putExtra("test", test.getText().toString());
                intent.putExtra("location",location.getText().toString());
                startActivity(intent);
            }
        });
///////////////////////////////////////////////////////////////////////////////////////////////
        line_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Sudokwonlist.class);
                test.setText("4호선");
                intent.putExtra("line_4", line_4.getText().toString());
                intent.putExtra("test", test.getText().toString());
                intent.putExtra("location",location.getText().toString());
                startActivity(intent);
            }
        });

        line_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Sudokwonlist.class);
                test.setText("5호선");
                intent.putExtra("line_5", line_5.getText().toString());
                intent.putExtra("test", test.getText().toString());
                intent.putExtra("location",location.getText().toString());
                startActivity(intent);
            }
        });

        line_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Sudokwonlist.class);
                test.setText("6호선");
                intent.putExtra("line_6", line_6.getText().toString());
                intent.putExtra("test", test.getText().toString());
                intent.putExtra("location",location.getText().toString());
                startActivity(intent);
            }
        });
//////////////////////////////////////////////////////////////////////////////////////////////////////
        line_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Sudokwonlist.class);
                test.setText("7호선");
                intent.putExtra("line_7", line_7.getText().toString());
                intent.putExtra("test", test.getText().toString());
                intent.putExtra("location",location.getText().toString());
                startActivity(intent);
            }
        });

        line_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Sudokwonlist.class);
                test.setText("8호선");
                intent.putExtra("line_8", line_8.getText().toString());
                intent.putExtra("test", test.getText().toString());
                intent.putExtra("location",location.getText().toString());
                startActivity(intent);
            }
        });

        line_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Sudokwonlist.class);
                test.setText("9호선");
                intent.putExtra("line_9", line_9.getText().toString());
                intent.putExtra("test", test.getText().toString());
                intent.putExtra("location",location.getText().toString());
                startActivity(intent);
            }
        });
//////////////////////////////////////////////////////////////////////////////////////////////////////////
        line_kyeonggang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Sudokwonlist.class);
                test.setText("경강선");
                intent.putExtra("line_kyeonggang", line_kyeonggang.getText().toString());
                intent.putExtra("test", test.getText().toString());
                intent.putExtra("location",location.getText().toString());
                startActivity(intent);
            }
        });

        line_kyongchun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Sudokwonlist.class);
                test.setText("경춘선");
                intent.putExtra("line_kyongchun", line_kyongchun.getText().toString());
                intent.putExtra("test", test.getText().toString());
                intent.putExtra("location",location.getText().toString());
                startActivity(intent);
            }
        });

        line_airport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Sudokwonlist.class);
                test.setText("공항철도");
                intent.putExtra("line_airport", line_airport.getText().toString());
                intent.putExtra("test", test.getText().toString());
                intent.putExtra("location",location.getText().toString());
                startActivity(intent);
            }
        });
/////////////////////////////////////////////////////////////////////////////////////////////////

        line_bundang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Sudokwonlist.class);
                test.setText("분당선");
                intent.putExtra("line_bundang", line_bundang.getText().toString());
                intent.putExtra("test", test.getText().toString());
                intent.putExtra("location",location.getText().toString());
                startActivity(intent);
            }
        });

        line_sooin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Sudokwonlist.class);
                test.setText("수인선");
                intent.putExtra("line_sooin", line_sooin.getText().toString());
                intent.putExtra("test", test.getText().toString());
                intent.putExtra("location",location.getText().toString());
                startActivity(intent);
            }
        });

        line_newbundang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Sudokwonlist.class);
                test.setText("신분당선");
                intent.putExtra("line_newbundang", line_newbundang.getText().toString());
                intent.putExtra("test", test.getText().toString());
                intent.putExtra("location",location.getText().toString());
                startActivity(intent);
            }
        });
//////////////////////////////////////////////////////////////////////////////
        line_ever.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Sudokwonlist.class);
                test.setText("에버라인");
                intent.putExtra("line_ever", line_ever.getText().toString());
                intent.putExtra("test", test.getText().toString());
                intent.putExtra("location",location.getText().toString());
                startActivity(intent);
            }
        });
        line_wooee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Sudokwonlist.class);
                test.setText("우이신설");
                intent.putExtra("line_wooee", line_wooee.getText().toString());
                intent.putExtra("test", test.getText().toString());
                intent.putExtra("location",location.getText().toString());
                startActivity(intent);
            }
        });
        line_euijungbu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Sudokwonlist.class);
                test.setText("의정부");
                intent.putExtra("line_euijungbu", line_euijungbu.getText().toString());
                intent.putExtra("test", test.getText().toString());
                intent.putExtra("location",location.getText().toString());
                startActivity(intent);
            }
        });
        /////////////////////////////////////////////////////////////////////////////////////////////

        line_incheon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Sudokwonlist.class);
                test.setText("인천1호선");
                intent.putExtra("line_incheon1", line_incheon1.getText().toString());
                intent.putExtra("test", test.getText().toString());
                intent.putExtra("location",location.getText().toString());
                startActivity(intent);
            }
        });
        line_incheon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Sudokwonlist.class);
                test.setText("인천2호선");
                intent.putExtra("line_incheon2", line_incheon2.getText().toString());
                intent.putExtra("test", test.getText().toString());
                intent.putExtra("location",location.getText().toString());
                startActivity(intent);
            }
        });
        line_magnet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Sudokwonlist.class);
                test.setText("자기부상철도");
                intent.putExtra("line_magnet", line_magnet.getText().toString());
                intent.putExtra("test", test.getText().toString());
                intent.putExtra("location",location.getText().toString());
                startActivity(intent);
            }
        });
        ///////////////////////////////////////////////////////////////////////////////////
        line_center.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Sudokwonlist.class);
                test.setText("경의중앙선");
                intent.putExtra("line_center", line_center.getText().toString());
                intent.putExtra("test", test.getText().toString());
                intent.putExtra("location",location.getText().toString());
                startActivity(intent);
            }
        });

    }
}
