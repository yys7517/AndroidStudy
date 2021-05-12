package com.example.smartvendingmachine.ui.Profile;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.example.smartvendingmachine.R;
import static android.content.Context.MODE_PRIVATE;

public class ProfileFragment extends Fragment {

    private SharedPreferences appData;
    private String sharedNickname;
    private String id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_profile, container, false);
        appData = getActivity().getSharedPreferences("appData", MODE_PRIVATE);
        getSharedLoad();

        return rootView;
    }

    private void getSharedLoad() {
        id = appData.getString("ID", "");
        sharedNickname = appData.getString("NICKNAME", "");
        Log.i("Id 가져오나 확인", "onCreateView : "+ id);
        Log.i("Nickname 가져오나 확인", "onCreateView : "+ sharedNickname);
    }

}