package com.sungkyul.osan;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class CarServiceInfo extends AppCompatActivity {

    SQLiteDatabase db;
    private MyDBHelper mHelper;
    private final static int DATABASE_VERSION = 1;

    private TextView institutution, contact, targettext, service, serviceinfo, cost;
    private ImageView phonecall;

    private static int REQUEST_ACCESS_CALL_PHONE = 1000;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carserviceinfo);

        Intent intent = getIntent();
        final String product = intent.getStringExtra("product");


        mHelper = new MyDBHelper(this, DATABASE_VERSION);
        db = mHelper.getWritableDatabase();

        institutution = findViewById(R.id.instituition_name);
        contact = findViewById(R.id.contact_number);
        targettext = findViewById(R.id.target);
        serviceinfo = findViewById(R.id.service_info);
        service = (TextView) findViewById(R.id.service);
        cost = findViewById(R.id.cost);

        phonecall = findViewById(R.id.phonecall);

        institutution.setText(product); // 기관명

// ----------- 서비스 대상 ---------------------------------------------//
        while (targettext.getText().toString().equals("")) {
            String rtarget = "";
            Cursor cursor;
            cursor = db.rawQuery("SELECT `target` FROM `vehicle` where `institutution` = '" + product + "' ", null);

            while (cursor.moveToNext()) {
                String target = cursor.getString(0);
                if (target == null) {
                    rtarget += "";
                } else
                    rtarget += target;
            }

            if (rtarget.equals("")) {
                targettext.setText("정보 없음");
            } else
                targettext.setText(rtarget);
        }

// ----------- 추가 제공 서비스 ---------------------------------------------//
        while (service.getText().toString().equals("")) {
            String rservice = "";
            Cursor cursor;
            cursor = db.rawQuery("SELECT `sort` FROM `vehicle` where `institutution` = '" + product + "' ", null);
            while (cursor.moveToNext()) {
                String service = cursor.getString(0);
                if (service == null) {
                    rservice += "";
                } else
                    rservice += service;
            }

            if (rservice.equals("")) {
                service.setText("정보 없음");
            } else
                service.setText(rservice);

        }
// ----------- 서비스 부연설명 ---------------------------------------------//
        while (serviceinfo.getText().toString().equals("")) {
            String rserviceinfo = "";
            Cursor cursor;
            cursor = db.rawQuery("SELECT `way` FROM `vehicle` where `institutution` = '" + product + "' ", null);
            while (cursor.moveToNext()) {
                String serviceinfo = cursor.getString(0);
                if (serviceinfo == null) {
                    rserviceinfo += "";
                } else
                    rserviceinfo += serviceinfo;
            }

            if (rserviceinfo.equals("")) {
                serviceinfo.setText("정보 없음");
            } else
                serviceinfo.setText(rserviceinfo);


        }
// ----------- 연락처 ---------------------------------------------//
        while (contact.getText().toString().equals("")) {
            String rphone = "";
            Cursor cursor;
            cursor = db.rawQuery("SELECT `contact` FROM `vehicle` where `institutution` = '" + product + "'", null);
            while (cursor.moveToNext()) {
                String phone = cursor.getString(0);
                if (phone == null) {
                    rphone += "";
                } else
                    rphone += phone;
            }

            SpannableString content = new SpannableString(rphone);
            content.setSpan(new UnderlineSpan(), 0, rphone.length(), 0);

            if (rphone.equals("")) {
                contact.setText("정보없음");
            } else
                contact.setText(content);
        }
        //---------------------------전화번호 다이얼 입력-----------------------------//
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    int permissionCheck = ContextCompat.checkSelfPermission(CarServiceInfo.this, Manifest.permission.CALL_PHONE);
                    if (permissionCheck == PackageManager.PERMISSION_DENIED) {
                        // 권한 없음
                        ActivityCompat.requestPermissions(CarServiceInfo.this,
                                new String[]{Manifest.permission.CALL_PHONE},
                                REQUEST_ACCESS_CALL_PHONE);
                    } else {
                        // ACCESS_FINE_LOCATION 에 대한 권한이 이미 있음.
                        Intent tt = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + contact.getText().toString().trim()));
                        startActivity(tt);
                        Toast.makeText(getApplicationContext(),product+" 전화번호 입니다",Toast.LENGTH_LONG).show();
                    }
                }
// OS가 Marshmallow 이전일 경우 권한체크를 하지 않는다.
                else {

                }
            }
        });
        //---------------------------전화걸기-----------------------------//
        phonecall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    int permissionCheck = ContextCompat.checkSelfPermission(CarServiceInfo.this, Manifest.permission.CALL_PHONE);
                    if (permissionCheck == PackageManager.PERMISSION_DENIED) {
                        // 권한 없음
                        ActivityCompat.requestPermissions(CarServiceInfo.this,
                                new String[]{Manifest.permission.CALL_PHONE},
                                REQUEST_ACCESS_CALL_PHONE);
                    } else {
                        // ACCESS_FINE_LOCATION 에 대한 권한이 이미 있음.
                        Intent tt = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + contact.getText().toString().trim()));
                        startActivity(tt);
                    }
                }
// OS가 Marshmallow 이전일 경우 권한체크를 하지 않는다.
                else {

                }
            }
        });


        // ----------- 비용 ---------------------------------------------//
        while (cost.getText().toString().equals("")) {
            String rcost = "";
            Cursor cursor;
            cursor = db.rawQuery("SELECT `cost` FROM `vehicle` where `institutution` = '" + product + "'", null);
            while (cursor.moveToNext()) {
                String cost = cursor.getString(0);
                if (cost == null) {
                    rcost += "";
                } else
                    rcost += cost;
            }
            if(rcost.equals("")){
                cost.setText("정보없음");
            }
            else
                cost.setText(rcost);
        }


    }

    //마쉬멜로 이상 체크함수
    public void check() {
        // OS가 Marshmallow 이상일 경우 권한체크를 해야 합니다.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);

            if (permissionCheck == PackageManager.PERMISSION_DENIED) {

                // 권한 없음
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CALL_PHONE},
                        REQUEST_ACCESS_CALL_PHONE);


            } else {

                // ACCESS_FINE_LOCATION 에 대한 권한이 이미 있음.

            }


        }

// OS가 Marshmallow 이전일 경우 권한체크를 하지 않는다.
        else {

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // grantResults[0] 거부 -> -1
        // grantResults[0] 허용 -> 0 (PackageManager.PERMISSION_GRANTED)

        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // ACCESS_FINE_LOCATION 에 대한 권한 획득.

        } else {
            // ACCESS_FINE_LOCATION 에 대한 권한 거부.

        }
    }
}
