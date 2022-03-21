package com.example.smartvendingmachine.ui.Guide;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FragmentAdapter extends FragmentStateAdapter {

    public int mCount;

    public FragmentAdapter(FragmentActivity fa, int count) {
        super(fa);
        mCount = count;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        int index = getRealPosition(position);

        switch (index) {            // 가이드 화면 순서

            case 0:
                return new FragmentGuideA();

            case 1:
                return new FragmentGuideB();

            default:
                return new FragmentGuideC();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }           // 화면 개수

    public int getRealPosition(int position) {
        return position % mCount;
    }       // 화면 순서 위치
}
