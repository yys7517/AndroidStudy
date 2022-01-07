package com.sungkyul.osan;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
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

import com.sungkyul.osan.MyDBHelper;

public class FacilInfo extends AppCompatActivity {

    SQLiteDatabase db;
    private MyDBHelper mHelper;
    private final static int DATABASE_VERSION = 1;

    private TextView facilname, contact, address, disabledtoilet, wheellift, charge, description, cost, homepage;
    private ImageView phonecall;

    private static int REQUEST_ACCESS_CALL_PHONE = 1000;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.facilinfo);

        Intent intent = getIntent();

        final String product = intent.getStringExtra("product");


        mHelper = new MyDBHelper(this, DATABASE_VERSION);
        db = mHelper.getWritableDatabase();

        facilname = findViewById(R.id.facil_name);
        contact = findViewById(R.id.contact_number);
        address = findViewById(R.id.address);
        disabledtoilet = (TextView) findViewById(R.id.disabledtoilet);
        wheellift = (TextView) findViewById(R.id.wheellift);
        charge = (TextView) findViewById(R.id.charge);
        description = findViewById(R.id.description);
        cost = findViewById(R.id.cost);
        homepage = findViewById(R.id.homepage);

        phonecall = findViewById(R.id.phonecall);

        facilname.setText(product);

// ----------- 주소 ---------------------------------------------//
        while (address.getText().toString().equals("")) {
            String raddress = "";
            Cursor cursor;
            cursor = db.rawQuery("SELECT `address` FROM `facility` where `name` = '" + product + "' ", null);

            while (cursor.moveToNext()) {
                String address = cursor.getString(0);
                if (address == null) {
                    raddress += "";
                } else
                    raddress += address;
            }

            if (raddress.equals("")) {
                address.setText("정보 없음");
            } else
                address.setText(raddress);
        }

// ----------- 장애인화장실 ---------------------------------------------//
        while (disabledtoilet.getText().toString().equals("")) {
            String rexist = "";
            Cursor cursor;
            cursor = db.rawQuery("SELECT `toilet` FROM `facility` where `name` = '" + product + "' ", null);
            while (cursor.moveToNext()) {
                String exist = cursor.getString(0);
                if (exist == null) {
                    rexist += "";
                } else
                    rexist += exist;
            }

            if (rexist.equals("true")) {
                disabledtoilet.setText("O");
            } else if (rexist.equals("false")) {
                disabledtoilet.setText("X");
            }
        }
// ----------- 휠체어 리프트 ---------------------------------------------//
        while (wheellift.getText().toString().equals("")) {
            String rexist = "";
            Cursor cursor;
            cursor = db.rawQuery("SELECT `lift` FROM `facility` where `name` = '" + product + "' ", null);
            while (cursor.moveToNext()) {
                String exist = cursor.getString(0);
                if (exist == null) {
                    rexist += "";
                } else
                    rexist += exist;
            }

            if (rexist.equals("true")) {
                wheellift.setText("O");
            } else if (rexist.equals("false")) {
                wheellift.setText("X");
            }

        }
// ----------- 휠체어 충전설비 ---------------------------------------------//
        while (charge.getText().toString().equals("")) {
            String rexist = "";
            Cursor cursor;
            cursor = db.rawQuery("SELECT `charger` FROM `facility` where `name` = '" + product + "' ", null);
            while (cursor.moveToNext()) {
                String exist = cursor.getString(0);
                if (exist == null) {
                    rexist += "";
                } else
                    rexist += exist;
            }

            if (rexist.equals("true")) {
                charge.setText("O");
            } else if (rexist.equals("false")) {
                charge.setText("X");
            }
        }

        // ----------- 홈페이지 ---------------------------------------------//
        while (homepage.getText().toString().equals("")) {
            String rurl = "";
            Cursor cursor;
            cursor = db.rawQuery("SELECT `homepage` FROM `facility` where `name` = '" + product + "'", null);
            while (cursor.moveToNext()) {
                String url = cursor.getString(0);
                if (url == null) {
                    rurl += "";
                } else
                    rurl += url;
            }

            SpannableString content = new SpannableString(rurl);
            content.setSpan(new UnderlineSpan(), 0, rurl.length(), 0);

            if (rurl.equals("정보없음")) {
                homepage.setText("정보없음");
                homepage.setTextColor(Color.BLACK);
            } else
                homepage.setText(content);
        }
        homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (homepage.getText().toString().trim().equals("정보없음")) {
                    Toast.makeText(getApplicationContext(),"홈페이지 URL에 대한 정보가 없습니다.",Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(getApplicationContext(), WebViewActivity.class);
                    intent.putExtra("facilname",product); // 시설이름 넘겨주기
                    intent.putExtra("homepage",homepage.getText().toString().trim()); // url 넘겨주기
                    startActivity(intent);
                }

            }
        });

// ----------- 연락처 ---------------------------------------------//
        while (contact.getText().toString().equals("")) {
            String rphone = "";
            Cursor cursor;
            cursor = db.rawQuery("SELECT `contact` FROM `facility` where `name` = '" + product + "'", null);
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
                    int permissionCheck = ContextCompat.checkSelfPermission(FacilInfo.this, Manifest.permission.CALL_PHONE);
                    if (permissionCheck == PackageManager.PERMISSION_DENIED) {
                        // 권한 없음
                        ActivityCompat.requestPermissions(FacilInfo.this,
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
                    int permissionCheck = ContextCompat.checkSelfPermission(FacilInfo.this, Manifest.permission.CALL_PHONE);
                    if (permissionCheck == PackageManager.PERMISSION_DENIED) {
                        // 권한 없음
                        ActivityCompat.requestPermissions(FacilInfo.this,
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

        // ----------- 설명 ---------------------------------------------//
        while (description.getText().toString().equals("")) {
            String rdes = "";
            Cursor cursor;
            cursor = db.rawQuery("SELECT `description` FROM `facility` where `name` = '" + product + "'", null);
            while (cursor.moveToNext()) {
                String des = cursor.getString(0);
                if (des == null) {
                    rdes += "";
                } else
                    rdes += des;
            }

            if (rdes.equals("")) {
                description.setText("기관에 문의하여주세요.");
            } else
                description.setText(rdes);
        }

        // ----------- 설명 ---------------------------------------------//
        while (cost.getText().toString().equals("")) {
            String rcost = "";
            Cursor cursor;
            cursor = db.rawQuery("SELECT `cost` FROM `facility` where `name` = '" + product + "'", null);
            while (cursor.moveToNext()) {
                String cost = cursor.getString(0);
                if (cost == null) {
                    rcost += "";
                } else
                    rcost += cost;
            }

            if (rcost.equals("0")) {
                cost.setText("기관에 문의하여 주세요.");
            } else
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
