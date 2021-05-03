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

        switch (index) {
            case 0:
                return new FragmentGuideA();

            case 1:
                return new FragmentGuideB();

            case 2:
                return new FragmentGuideC();

            default:
                return new FragmentGuideD();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public int getRealPosition(int position) {
        return position % mCount;
    }
}
