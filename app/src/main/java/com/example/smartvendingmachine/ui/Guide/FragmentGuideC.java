package com.example.smartvendingmachine.ui.Guide;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.smartvendingmachine.R;
import com.example.smartvendingmachine.LoginActivity;


public class FragmentGuideC extends Fragment {
    private Button mButtonStart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_introc, container, false);

        mButtonStart = (Button) rootView.findViewById(R.id.mButtonStart);
        mButtonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntentLogin = new Intent(getActivity(), LoginActivity.class); //시작하기 버튼 클릭 시 로그인 화면으로 이동.
                startActivity(mIntentLogin);
            }
        });

        return rootView;
    }
}