package com.example.smartvendingmachine.ui.Home;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.smartvendingmachine.ui.DRAdapter;
import com.example.smartvendingmachine.ui.DRData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import com.example.smartvendingmachine.R;

public class HomeFragment extends Fragment {

    private TextView mTextViewCocacolaStock, mTextViewChilsungStock, mTextViewSsekssekStock, mTextViewFantaStock, mTextViewMountainStock, mTextViewGalbaeStock;
    private static String IP_ADDRESS = "211.211.158.42/yongrun/svm";
    private static String TAG = "phptest";


    private SwipeRefreshLayout swipeRefreshLayout;

    private ArrayList<DRData> mArrayList;
    private DRAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private String mJsonString;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, container, false);

        mTextViewCocacolaStock = (TextView) v.findViewById(R.id.mTextViewCocacolaStock);
        mTextViewChilsungStock = (TextView) v.findViewById(R.id.mTextViewChilsungStock);
        mTextViewSsekssekStock = (TextView) v.findViewById(R.id.mTextViewSsekssekStock);
        mTextViewFantaStock = (TextView) v.findViewById(R.id.mTextViewFantaStock);
        mTextViewMountainStock = (TextView) v.findViewById(R.id.mTextViewMountainStock);
        mTextViewGalbaeStock = (TextView) v.findViewById(R.id.mTextViewGalbaeStock);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.mRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mArrayList = new ArrayList<>();

        mAdapter = new DRAdapter(getActivity(), mArrayList);
        mRecyclerView.setAdapter(mAdapter);

        mArrayList.clear();
        mAdapter.notifyDataSetChanged();

        GetData task = new GetData();
        task.execute("http://" + IP_ADDRESS + "/drink.php", "");

        swipeRefreshLayout = v.findViewById(R.id.refresh_layout);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mArrayList.clear();
                mAdapter.notifyDataSetChanged();

                GetData task = new GetData();
                task.execute("http://" + IP_ADDRESS + "/drink.php", "");
                swipeRefreshLayout.setRefreshing(false); //새로고침표시 없애기
            }
        });


        return v;
    }

    private class GetData extends AsyncTask<String, Void, String> {

        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(getActivity(), "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();

            Log.d(TAG, "response - " + result);

            if (result == null) {

            } else {

                mJsonString = result;
                showResult();
            }
        }


        @Override
        protected String doInBackground(String... params) {

            String serverURL = params[0];
            String postParameters = params[1];


            try {

                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();


                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();


                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "response code - " + responseStatusCode);

                InputStream inputStream;
                if (responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                } else {
                    inputStream = httpURLConnection.getErrorStream();
                }


                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }

                bufferedReader.close();

                return sb.toString().trim();


            } catch (Exception e) {

                Log.d(TAG, "GetData : Error ", e);
                errorString = e.toString();

                return null;
            }

        }
    }

    private void showResult() {

        String TAG_JSON = "drink_data";
        String TAG_DRID = "DRID";
        String TAG_DRCode = "DRCode";
        String TAG_DRStock = "DRStock";


        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject item = jsonArray.getJSONObject(i);

                String DRID = item.getString(TAG_DRID);
                String DRCode = item.getString(TAG_DRCode);
                String DRStock = item.getString(TAG_DRStock);

                DRData personalData = new DRData();

                personalData.setDRID(DRID);
                personalData.setDRCode(DRCode);
                personalData.setDRStock(DRStock);

                mArrayList.add(personalData);
                mAdapter.notifyDataSetChanged();

                switch (mArrayList.get(i).getDRCode()) {
                    case "CocaCola":
                        mTextViewCocacolaStock.setText("현재 재고 : " + mArrayList.get(i).getDRStock() + " 개");
                        break;

                    case "Chilsung Cider":
                        mTextViewChilsungStock.setText("현재 재고 : " + mArrayList.get(i).getDRStock() + " 개");
                        break;

                    case "Ssekssek":
                        mTextViewSsekssekStock.setText("현재 재고 : " + mArrayList.get(i).getDRStock() + " 개");
                        break;

                    case "Fanta Orange":
                        mTextViewFantaStock.setText("현재 재고 : " + mArrayList.get(i).getDRStock() + " 개");
                        break;

                    case "Mountain Dew":
                        mTextViewMountainStock.setText("현재 재고: " + mArrayList.get(i).getDRStock() + " 개");
                        break;

                    case "Galbae":
                        mTextViewGalbaeStock.setText("현재 재고: " + mArrayList.get(i).getDRStock() + " 개");
                        break;

                    default:
                        break;
                }
            }


        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }

    }

}