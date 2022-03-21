package com.sungkyul.moji;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sungkyul.moji.subway.Subwaymap;

public class MainActivity extends AppCompatActivity {

    private Toast toast;
    private long backKeyPressedTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
// ------------- 수도권 ---------------------------////
        ImageView Sudokwonimg = (ImageView)findViewById(R.id.sudokwon);
        TextView Sudokwontxt = (TextView)findViewById(R.id.sudokwontext);

        Sudokwonimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Sudokwon.class);
                startActivity(intent);
            }
        });
        Sudokwontxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Sudokwon.class);
                startActivity(intent);
            }
        });
// ------------- 대전 ---------------------------////
        ImageView Daejeonimg = (ImageView)findViewById(R.id.daejeon);
        TextView Daejeontxt = (TextView)findViewById(R.id.daejeontext);

        Daejeonimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Daejeon.class);
                startActivity(intent);
            }
        });
        Daejeontxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Daejeon.class);
                startActivity(intent);
            }
        });
// ------------- 대구 ---------------------------////
        ImageView Daeguimg = (ImageView)findViewById(R.id.daegu);
        TextView Daegutxt = (TextView)findViewById(R.id.daegutext);

        Daeguimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Daegu.class);
                startActivity(intent);
            }
        });
        Daegutxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Daegu.class);
                startActivity(intent);
            }
        });
// ------------- 광주 ---------------------------////
        ImageView Kwangjuimg = (ImageView)findViewById(R.id.kwangju);
        TextView Kwangjutxt = (TextView)findViewById(R.id.kwangjutext);

        Kwangjuimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Kwangju.class);
                startActivity(intent);
            }
        });
        Kwangjutxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Kwangju.class);
                startActivity(intent);
            }
        });
// ------------- 부산 ---------------------------////
        ImageView Busanimg = (ImageView)findViewById(R.id.busan);
        TextView Busantxt = (TextView)findViewById(R.id.busantext);

        Busanimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Busan.class);
                startActivity(intent);
            }
        });
        Busantxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Busan.class);
                startActivity(intent);
            }
        });
        // ------------- 지하철 노선도 ---------------------------////
        ImageView subwayimg = (ImageView)findViewById(R.id.subwaymap);
        TextView subwaytext = (TextView) findViewById(R.id.subwaymaptext);

        subwayimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Subwaymap.class);
                startActivity(intent);
            }
        });
        subwaytext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Subwaymap.class);
                startActivity(intent);
            }
        });
        // ----------- 웹뷰 -----------------------------//
        ImageView webimg = (ImageView)findViewById(R.id.webview);
        TextView webtext = (TextView)findViewById(R.id.webviewtext);
        webimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), KricInfo.class);
                startActivity(intent);
            }
        });
        webtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), KricInfo.class);
                startActivity(intent);
            }
        });
        // ----------- 주변역 찾기 -----------------------------//
        ImageView mapimg = (ImageView)findViewById(R.id.mapimg);
        TextView maptext = (TextView)findViewById(R.id.maptext);
        mapimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LocationMap.class);
                startActivity(intent);
            }
        });
        maptext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LocationMap.class);
                startActivity(intent);
            }
        });
        // ----------- 웹뷰 -----------------------------//
        ImageView helpimg = (ImageView)findViewById(R.id.helpimg);
        TextView helptext = (TextView)findViewById(R.id.helptext);
        helpimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), KricHelp.class);
                startActivity(intent);
            }
        });
        helptext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), KricHelp.class);
                startActivity(intent);
            }
        });


        // ----------- 오픈소스 정보, 정보 제공처 -----------------------------//
        TextView info = (TextView)findViewById(R.id.info);
        TextView opensource = (TextView)findViewById(R.id.opensource);
        final TextView opensource_info = (TextView)findViewById(R.id.opensource_info);
        opensource_info.setMovementMethod(new ScrollingMovementMethod());

        final String opensourcetext, infotext;
        opensourcetext = "지하철 노선도 핀치 줌/아웃 스크롤 PhotoView 라이브러리 : https://github.com/davemorrissey/subsampling-scale-image-view\n"+
                "구글 map지도 API Place : noman.placesapi:placesAPI:1.1.3\n"+
                "구글 map지도 API Service-maps : com.google.android.gms:play-services-maps:17.0.0\n" +
                "구글 map지도 API Service-location : com.google.android.gms:play-services-location:17.0.0\n";
        infotext = "아이콘 저작자 :flaticon.com(플랫아이콘) /  Icons made by a Smashicons, surang, Freepik, photo3idea_studio, fjstudio, Icongeek26, Prosymbols, xnimrodx\n" +
                "iconfinder.com(아이콘파인더) / Nick Roach\n" +
                "수도권 노선도 이미지 : https://www.sisul.or.kr/open_content/skydome/introduce/pop_subway.jsp (서울시설공단)\n" +
                "광주 노선도 이미지 : https://m.map.naver.com/subway/subwayLine.nhn?region=5000 (네이버지하철)\n" +
                "대전 노선도 이미지 : http://www.djet.co.kr/kor/page.do?menuIdx=39 (대전도시철도공사)\n" +
                "대구 노선도 이미지 : https://www.dtro.or.kr/open_content_new/ko/main/Line_view.php (대구도시철도공사)\n" +
                "부산 노선도 이미지 : http://www.humetro.busan.kr/homepage/cyberstation/map.do (휴메트로, 부산교통공사)\n" +
                "정보 제공 : 철도산업정보센터";

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(opensource_info.getText().toString().equals("") || opensource_info.getText().toString().equals(opensourcetext)) {
                    opensource_info.setText(infotext);
                }
                else
                    opensource_info.setText("");
            }
        });


        opensource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(opensource_info.getText().toString().equals("") || opensource_info.getText().toString().equals(infotext))
                {
                    opensource_info.setText(opensourcetext);
                }
                else
                    opensource_info.setText("");



            }
        });
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();

        // 마지막으로 뒤로가기 버튼을 눌렀던 시간에 2초를 더해 현재시간과 비교 후
        // 마지막으로 뒤로가기 버튼을 눌렀던 시간이 2초가 지났으면 Toast Show
        // 2000 milliseconds = 2 seconds
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            toast = Toast.makeText(this, "'뒤로' 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        // 마지막으로 뒤로가기 버튼을 눌렀던 시간에 2초를 더해 현재시간과 비교 후
        // 마지막으로 뒤로가기 버튼을 눌렀던 시간이 2초가 지나지 않았으면 종료
        // 현재 표시된 Toast 취소
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            finish();
            toast.cancel();
        }
    }
}