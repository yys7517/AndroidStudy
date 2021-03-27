package com.example.smartvendingmachine.ui;

import android.app.FragmentTransaction;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
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

    private FragmentTransaction transaction;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //프래그먼트 생성
        boardFragment = new BoardFragment();
        homeFragment = new HomeFragment();
        profileFragment = new ProfileFragment();

        bottomNavigationView = findViewById(R.id.nav_view);

        // 처음에 띄울화면 이걸로 기릿~
        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, homeFragment,"home").commitAllowingStateLoss();

        // 바텀 내비게이션 뷰 초기 선택 값 (홈)
        bottomNavigationView.setSelectedItemId(R.id.frag_navigation_home);

        //bottomnavigationview의 아이콘을 선택 했을때 원하는 프래그먼트가 띄워질 수 있도록 리스터 추가
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.frag_navigation_home: {
                        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, homeFragment,"home").addToBackStack(null).commitAllowingStateLoss();
                        return true;
                    }
                    case R.id.frag_navigation_board: {
                        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, boardFragment,"board").addToBackStack(null).commitAllowingStateLoss();
                        return true;
                    }
                    case R.id.frag_navigation_profile: {
                        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, profileFragment,"profile").addToBackStack(null).commitAllowingStateLoss();
                        return true;
                    }

                    default:
                        return false;
                }
            }
        });



    }

    public void updateBottomMenu (BottomNavigationView navigation)
    {
        if(getSupportFragmentManager().findFragmentByTag("home") != null && getSupportFragmentManager().findFragmentByTag("home").isVisible() ) {
            bottomNavigationView.getMenu().findItem(R.id.frag_navigation_home).setChecked(true);
            //bottomNavigationView.setSelectedItemId(R.id.frag_navigation_home);
        }
        else if(getSupportFragmentManager().findFragmentByTag("board") != null && getSupportFragmentManager().findFragmentByTag("board").isVisible() ) {
            bottomNavigationView.getMenu().findItem(R.id.frag_navigation_board).setChecked(true);
            //bottomNavigationView.setSelectedItemId(R.id.frag_navigation_board);
        }
        else if(getSupportFragmentManager().findFragmentByTag("profile") != null && getSupportFragmentManager().findFragmentByTag("profile").isVisible() ) {
            bottomNavigationView.getMenu().findItem(R.id.frag_navigation_profile).setChecked(true);
           //bottomNavigationView.setSelectedItemId(R.id.frag_navigation_profile);
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        BottomNavigationView bnv = findViewById(R.id.nav_view);
        updateBottomMenu(bnv);

    }

}