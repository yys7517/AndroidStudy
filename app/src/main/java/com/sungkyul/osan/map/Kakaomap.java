package com.sungkyul.osan.map;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.sungkyul.osan.R;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapReverseGeoCoder;
import net.daum.mf.map.api.MapView;

import java.util.List;

public class Kakaomap extends Activity implements MapView.CurrentLocationEventListener, MapReverseGeoCoder.ReverseGeoCodingResultListener {

    private static final String LOG_TAG = "Kakaomap";

    MapListViewAdapter adapter = new MapListViewAdapter();

    private MapView mMapView;
    DataLocation DataLocation;
    double distance; //거리계산 변수
    double lati; //현재위치 lati
    double longi; //현재위치 longi
    double lati2; //리스트 lati
    double longi2; //리스트 longi2
    String name2; //리스트 시설 이름
    public static double thread_distance = 0.0; //Thread 에서 넘어온 계산된 거리
    public static String thread_name;

    String Name;
    Double Distance;

    int count = 0;

    private static final int GPS_ENABLE_REQUEST_CODE = 2001;
    private static final int PERMISSIONS_REQUEST_CODE = 100;
    String[] REQUIRED_PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION};

    Location locationA = new Location("현재위치"); //현재위치
    Location locationB = new Location("나중위치"); //신호등위치

    //db 넣어줄 리스트
    List<Item> items;
    List<MapListViewItem> mMapListViewItems;

    MapListViewItem mMapListViewItem = new MapListViewItem();

    DataAdapter mDataAdapter;

    ListView mListView;


    /* TODO : 핸들러
    Handler HandlerName = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Bundle mBundleName = msg.getData();
            String Name = mBundleName.getString("name");

            Log.d("이름 가져오나 확인", "시설명 : " + Name);
            thread_name = Name;
        }
    };

    Handler HandlerDistance = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Bundle mBundleDistance = msg.getData();
            double Distance = mBundleDistance.getDouble("distance");

            Log.d("거리 가져오나 확인", "계산된 거리 : " + Distance);
            thread_distance = Distance;
        }
    };
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        mMapView = (MapView) findViewById(R.id.map_view);
        mListView = (ListView) findViewById(R.id.mListView);

        // 리스트뷰 참조 및 Adapter달기
        ListView mListView = (ListView) findViewById(R.id.mListView);
        mListView.setAdapter(adapter);

        mMapView.setCurrentLocationEventListener(this);

        if (!checkLocationServicesStatus()) {
            showDialogForLocationServiceSetting();
        } else {

            checkRunTimePermission();
        }
        mDataAdapter = new DataAdapter(getApplicationContext());

        mDataAdapter.open();
        items = mDataAdapter.getTableData("SELECT name,latitude,longitude FROM facility");
        mDataAdapter.close();

        pin();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOff);
        mMapView.setShowCurrentLocationMarker(false);
    }

    @Override
    public void onCurrentLocationUpdate(MapView mapView, MapPoint currentLocation, float accuracyInMeters) {

        MapPoint.GeoCoordinate mapPointGeo = currentLocation.getMapPointGeoCoord();
        Log.i(LOG_TAG, String.format("MapView onCurrentLocationUpdate (%f,%f) accuracy (%f)", mapPointGeo.latitude, mapPointGeo.longitude, accuracyInMeters));

        //라티 롱기 추출
        lati = (mapPointGeo.latitude);
        longi = (mapPointGeo.longitude);

        count = 0;

        /* TODO : 쓰레드
        LocationThread r1 = new LocationThread(lati, longi, items);
        Thread t1 = new Thread(r1);
        t1.setDaemon(true); //데몬 쓰레드 지정
        t1.start();
         */

        locationA.setLatitude(lati);
        locationA.setLongitude(longi);

        if (adapter.getCount() > 0) {
            adapter.clearItem();
        }

        for (int i = 0; i < items.size(); i++) {
            Name = items.get(i).getName();
            locationB.setLatitude(items.get(i).getLatitude());
            locationB.setLongitude(items.get(i).getLongitude());
            Distance = Double.valueOf(locationA.distanceTo(locationB) / 100) / 10.0;

            if (items.get(i).getLatitude() != 0.0 && items.get(i).getLongitude() != 0.0) {
                adapter.addItem(Name, Distance);
            }
        }

        mListView.setAdapter(adapter);
    }


    @Override
    public void onCurrentLocationDeviceHeadingUpdate(MapView mapView, float v) {

    }

    @Override
    public void onCurrentLocationUpdateFailed(MapView mapView) {

    }

    @Override
    public void onCurrentLocationUpdateCancelled(MapView mapView) {

    }

    @Override
    public void onReverseGeoCoderFoundAddress(MapReverseGeoCoder mapReverseGeoCoder, String s) {
        mapReverseGeoCoder.toString();
        onFinishReverseGeoCoding(s);
    }

    @Override
    public void onReverseGeoCoderFailedToFindAddress(MapReverseGeoCoder mapReverseGeoCoder) {
        onFinishReverseGeoCoding("Fail");
    }

    private void onFinishReverseGeoCoding(String result) {
//        Toast.makeText(LocationDemoActivity.this, "Reverse Geo-coding : " + result, Toast.LENGTH_SHORT).show();
    }


    /*
     * ActivityCompat.requestPermissions를 사용한 퍼미션 요청의 결과를 리턴받는 메소드입니다.
     */
    @Override
    public void onRequestPermissionsResult(int permsRequestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grandResults) {

        if (permsRequestCode == PERMISSIONS_REQUEST_CODE && grandResults.length == REQUIRED_PERMISSIONS.length) {

            // 요청 코드가 PERMISSIONS_REQUEST_CODE 이고, 요청한 퍼미션 개수만큼 수신되었다면

            boolean check_result = true;


            // 모든 퍼미션을 허용했는지 체크합니다.

            for (int result : grandResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    check_result = false;
                    break;
                }
            }


            if (check_result) {
                Log.d("@@@", "start");
                //위치 값을 가져올 수 있음
                mMapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading);
            } else {
                // 거부한 퍼미션이 있다면 앱을 사용할 수 없는 이유를 설명해주고 앱을 종료합니다.2 가지 경우가 있습니다.

                if (ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[0])) {

                    Toast.makeText(this, "퍼미션이 거부되었습니다. 앱을 다시 실행하여 퍼미션을 허용해주세요.", Toast.LENGTH_LONG).show();
                    finish();


                } else {

                    Toast.makeText(this, "퍼미션이 거부되었습니다. 설정(앱 정보)에서 퍼미션을 허용해야 합니다. ", Toast.LENGTH_LONG).show();

                }
            }

        }
    }

    void checkRunTimePermission() {

        //런타임 퍼미션 처리
        // 1. 위치 퍼미션을 가지고 있는지 체크합니다.
        int hasFineLocationPermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);


        if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED) {

            // 2. 이미 퍼미션을 가지고 있다면
            // ( 안드로이드 6.0 이하 버전은 런타임 퍼미션이 필요없기 때문에 이미 허용된 걸로 인식합니다.)


            // 3.  위치 값을 가져올 수 있음
            mMapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading);


        } else {  //2. 퍼미션 요청을 허용한 적이 없다면 퍼미션 요청이 필요합니다. 2가지 경우(3-1, 4-1)가 있습니다.

            // 3-1. 사용자가 퍼미션 거부를 한 적이 있는 경우에는
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[0])) {

                // 3-2. 요청을 진행하기 전에 사용자가에게 퍼미션이 필요한 이유를 설명해줄 필요가 있습니다.
                Toast.makeText(this, "이 앱을 실행하려면 위치 접근 권한이 필요합니다.", Toast.LENGTH_LONG).show();
                // 3-3. 사용자게에 퍼미션 요청을 합니다. 요청 결과는 onRequestPermissionResult에서 수신됩니다.
                ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE);


            } else {
                // 4-1. 사용자가 퍼미션 거부를 한 적이 없는 경우에는 퍼미션 요청을 바로 합니다.
                // 요청 결과는 onRequestPermissionResult에서 수신됩니다.
                ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE);
            }

        }

    }


    //여기부터는 GPS 활성화를 위한 메소드들
    private void showDialogForLocationServiceSetting() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("위치 서비스 비활성화");
        builder.setMessage("앱을 사용하기 위해서는 위치 서비스가 필요합니다.\n"
                + "위치 설정을 수정하실래요?");
        builder.setCancelable(true);
        builder.setPositiveButton("설정", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Intent callGPSSettingIntent
                        = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivityForResult(callGPSSettingIntent, GPS_ENABLE_REQUEST_CODE);
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case GPS_ENABLE_REQUEST_CODE:

                //사용자가 GPS 활성 시켰는지 검사
                if (checkLocationServicesStatus()) {
                    if (checkLocationServicesStatus()) {

                        Log.d("@@@", "onActivityResult : GPS 활성화 되있음");
                        checkRunTimePermission();
                        return;
                    }
                }

                break;
        }
    }

    public boolean checkLocationServicesStatus() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    public void pin() {
        for (int i = 0; i < items.size(); i++) {
            lati2 = items.get(i).getLatitude();
            longi2 = items.get(i).getLongitude();
            name2 = items.get(i).getName();
            //마커 찍기
            MapPoint MARKER_POINT = MapPoint.mapPointWithGeoCoord(lati2, longi2);
            MapPOIItem marker = new MapPOIItem();
            //marker.setCustomImageResourceId(R.drawable.redpin);
            marker.setItemName(name2); //핀 선택시 말풍선으로 나오는 이름
            marker.setShowDisclosureButtonOnCalloutBalloon(false);
            marker.setTag(0);
            marker.setMapPoint(MARKER_POINT);
            marker.setMarkerType(MapPOIItem.MarkerType.BluePin); //기본핀 마커 모양
            marker.setShowAnimationType(MapPOIItem.ShowAnimationType.DropFromHeaven); //하늘에서 떨어지는 애니메이션
            marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.

            mMapView.addPOIItem(marker);


            /*
            //마커 찍기
            MapPoint MARKER_POINT = MapPoint.mapPointWithGeoCoord(lati2,longi2);
            MapPOIItem marker = new MapPOIItem();
            marker.setCustomImageResourceId(R.drawable.redpin);
            marker.setItemName("시설 위치");
            marker.setTag(0);
            marker.setMapPoint(MARKER_POINT);
            marker.setMarkerType(MapPOIItem.MarkerType.CustomImage);
            marker.setShowAnimationType(MapPOIItem.ShowAnimationType.DropFromHeaven); //하늘에서 떨어지는 애니메이션
            marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.

            mMapView.addPOIItem(marker);
             */
        }
    }
}