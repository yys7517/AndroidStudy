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

public class Daejeon extends AppCompatActivity {

    static String tname;
    Cursor cursor;
    SQLiteDatabase db;
    private MyDBHelper mHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daejeon);

        mHelper = new MyDBHelper(this, 1);
        db = mHelper.getWritableDatabase();

        final TextView line_1 = (TextView) findViewById(R.id.line_1);

        final TextView location = (TextView)findViewById(R.id.locationname);


        final TextView test = (TextView) findViewById(R.id.test);


        final ListView listview = (ListView) findViewById(R.id.stationlistview);

        final ArrayList<String> list = new ArrayList<>();

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, list);

        line_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Daejeonlist.class);
                test.setText("1호선");
                intent.putExtra("line_1", line_1.getText().toString());
                intent.putExtra("test", test.getText().toString());
                intent.putExtra("location",location.getText().toString());
                startActivity(intent);
            }
        });

    }
}
