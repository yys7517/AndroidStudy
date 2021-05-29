package com.example.smartvendingmachine;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Toast;

import com.example.smartvendingmachine.R;
import com.example.smartvendingmachine.ui.Home.HomeFragment;
import com.example.smartvendingmachine.ui.Profile.ProfileFragment;
import com.example.smartvendingmachine.ui.board.BoardFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {


    private BottomNavigationView bottomNavigationView;
    BoardFragment boardFragment;
    HomeFragment homeFragment;
    ProfileFragment profileFragment;

    private SharedPreferences appData;

    private final long FINISH_INTERVAL_TIME = 2000;
    private long   backPressedTime = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appData = getSharedPreferences("appData", MODE_PRIVATE);

        String USER_NICKNAME =  appData.getString("NICKNAME", "");          // 로그인 한 사용자 닉네임 정보 값 가져오기
        Toast.makeText(getApplicationContext(), USER_NICKNAME + " 님 어서오세요.", Toast.LENGTH_SHORT).show();

        //프래그먼트 생성
        bottomNavigationView = findViewById(R.id.nav_view);

        // 처음에 띄울화면 홈 화면으로
        homeFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, homeFragment,"home").commit();

        // 바텀 내비게이션 뷰 초기 선택 값 (홈)
        bottomNavigationView.setSelectedItemId(R.id.frag_navigation_home);

        //bottomnavigationview의 아이콘을 선택 했을때 원하는 프래그먼트가 띄워질 수 있도록 리스터 추가
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();

                switch (menuItem.getItemId()) {
                    case R.id.frag_navigation_home: {
                        fm.popBackStack("home",FragmentManager.POP_BACK_STACK_INCLUSIVE);
                        homeFragment = new HomeFragment();
                        transaction.replace(R.id.nav_host_fragment, homeFragment,"home");
                        transaction.addToBackStack("home");
                        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                        transaction.commit();
                        transaction.isAddToBackStackAllowed();
                        //transaction.replace(R.id.nav_host_fragment, homeFragment,"home").addToBackStack(null).commitAllowingStateLoss();
                        return true;
                    }

                    case R.id.frag_navigation_board: {
                        fm.popBackStack("board",FragmentManager.POP_BACK_STACK_INCLUSIVE);
                        boardFragment = new BoardFragment();
                        transaction.replace(R.id.nav_host_fragment, boardFragment,"board");
                        transaction.addToBackStack("board");
                        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                        transaction.commit();
                        transaction.isAddToBackStackAllowed();
                        //transaction.replace(R.id.nav_host_fragment, boardFragment,"board").addToBackStack(null).commitAllowingStateLoss();
                        return true;
                    }


                    case R.id.frag_navigation_profile:{
                        fm.popBackStack("profile",FragmentManager.POP_BACK_STACK_INCLUSIVE);
                        profileFragment = new ProfileFragment();
                        transaction.replace(R.id.nav_host_fragment, profileFragment,"profile");
                        transaction.addToBackStack("profile");
                        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                        transaction.commit();
                        transaction.isAddToBackStackAllowed();
                        //transaction.replace(R.id.nav_host_fragment, profileFragment,"profile").addToBackStack(null).commitAllowingStateLoss();
                        return true;
                    }
                    default:
                        return false;

                }
            }
        });

    }

    // 화면 전환 시 바텀 내비게이션 바 메뉴 선택 정보 갱신
    public void updateBottomMenu (BottomNavigationView navigation)
    {
        if(getSupportFragmentManager().findFragmentByTag("home") != null && getSupportFragmentManager().findFragmentByTag("home").isVisible() ) {
            bottomNavigationView.getMenu().findItem(R.id.frag_navigation_home).setChecked(true);
        }
        else if(getSupportFragmentManager().findFragmentByTag("board") != null && getSupportFragmentManager().findFragmentByTag("board").isVisible() ) {
            bottomNavigationView.getMenu().findItem(R.id.frag_navigation_board).setChecked(true);
        }
        else if(getSupportFragmentManager().findFragmentByTag("profile") != null && getSupportFragmentManager().findFragmentByTag("profile").isVisible() ) {
            bottomNavigationView.getMenu().findItem(R.id.frag_navigation_profile).setChecked(true);
        }
    }


    //뒤로가기 버튼 눌렀을 때
    @Override
    public void onBackPressed() {

        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;

        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            if (!(0 > intervalTime || FINISH_INTERVAL_TIME < intervalTime)) {
                finishAffinity();
                System.runFinalization();
                System.exit(0);
            } else {
                backPressedTime = tempTime;
                Toast.makeText(this, "'뒤로' 버튼을 한 번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        super.onBackPressed();
        BottomNavigationView bnv = findViewById(R.id.nav_view);
        updateBottomMenu(bnv);
    }

    @Override
    public void supportFinishAfterTransition() {
        ActivityCompat.finishAfterTransition(this);
    }
}