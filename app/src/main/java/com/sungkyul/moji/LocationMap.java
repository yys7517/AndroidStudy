package com.sungkyul.moji;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Looper;
import android.os.Vibrator;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.snackbar.Snackbar;
import com.google.maps.android.SphericalUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

import noman.googleplaces.NRPlaces;
import noman.googleplaces.Place;
import noman.googleplaces.PlaceType;
import noman.googleplaces.PlacesException;
import noman.googleplaces.PlacesListener;

public class LocationMap extends AppCompatActivity implements OnMapReadyCallback, ActivityCompat.OnRequestPermissionsResultCallback, PlacesListener {

    //지도 변수
    private GoogleMap mMap;

    //현재 위치 찍히는 마커 변수
    private Marker currentMarker = null;

    //검색 변수
    private EditText editmap;
    //진동 변수
    private Vibrator vibrator;

    private static final String TAG = "googlemap_example";
    private static final int GPS_ENABLE_REQUEST_CODE = 2001;
    private static final int UPDATE_INTERVAL_MS = 3000; // 3초
    private static final int FASTEST_UPDATE_INTERVAL_MS = 3000; // 3000->3초   36000 ->1시간

    // onRequestPermissionsResult에서 수신된 결과에서 ActivityCompat.requestPermissions를 사용한 퍼미션 요청을 구별하기 위해 사용됩니다.
    private static final int PERMISSIONS_REQUEST_CODE = 100;
    boolean needRequest = false;
    // boolean mMoveMapByAPI = true;
    // boolean mMoveMapByUser = true;

    // 앱을 실행하기 위해 필요한 퍼미션을 정의합니다.
    String[] REQUIRED_PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}; // 외부 저장소



    Location mCurrentLocatiion;
    //위도 경도값 구하기 위해 필요한 변수
    LatLng currentPosition,point,point2;

    // 거리계산할때 필요한 변수
    LatLng previousPosition = null;
    Marker addedMarker = null;
    Marker item = null;
    int tracking = 0;

    //키보드 내리기 전역변수
    private InputMethodManager imm;

    //Toast변수
    private Toast mToast=null;
    private String mMasage=null;
    private int count=0;

    private FusedLocationProviderClient mFusedLocationClient;
    private LocationRequest locationRequest;
    private Location location;

    private View mLayout; // Snackbar 사용하기 위해서는 View가 필요합니다.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setContentView(R.layout.subway_location_map);

        //Vibrator객체 정의
        vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

        previous_marker = new ArrayList<Marker>();
        Toast.makeText(this, "GPS를 허용하였다면 지도의 오른쪽 상단의 버튼을 눌러 현재 내 위치를 확인해보세요.", Toast.LENGTH_LONG).show();

        //상단의 주소 검색TextView
        TextView txtmap = (TextView) findViewById(R.id.txtmap);
        txtmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Serchlocation();
            }
        });

        //상단의 주소 검색창 clear TextView
        final TextView txtclear = (TextView) findViewById(R.id.txtclear);
        txtclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editmap.setText("");
            }
        });

        //키보드에 검색버튼 추가
        editmap = (EditText)findViewById(R.id.editmap);
        editmap.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        editmap.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionid, KeyEvent keyEvent) {
                if (actionid==EditorInfo.IME_ACTION_SEARCH){
                    //상단의 주소 검색TextView
                    Serchlocation();
                    return true;
                }
                return false;
            }
        });

        //키보드 내리기
        imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);

        //검색창 포커스 상태에 따른 txtclear버튼 활성화 비활성화
        editmap.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(hasFocus)
                    txtclear.setVisibility(View.VISIBLE);
                else
                    txtclear.setVisibility(View.INVISIBLE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        });

        //상단의 현재위치 주변의 지하철 검색 TextView
        TextView subwayfind = (TextView)findViewById(R.id.subwayfind);
        subwayfind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                showPlaceInformation(currentPosition);
                Toast.makeText(getApplicationContext(), "반경 1KM 안에 지하철역을 검색합니다.", Toast.LENGTH_SHORT).show();

            }
        });

        //상단의 검색주소 주변 지하철 검색 TextView
        TextView serchfind = (TextView)findViewById(R.id.serchfind);
        serchfind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = editmap.getText().toString();

                if (point != null) {
                    showPlaceInformation(point);
                    Toast.makeText(getApplicationContext(), "반경 1KM 안에 지하철역을 검색합니다.", Toast.LENGTH_SHORT).show();
                }else if(str.equals("") || point == null){
                    Toast.makeText(getApplicationContext(), "해당위치를 먼저 검색하여주세요", Toast.LENGTH_SHORT).show();
                }

            }
        });

        //거리계산 버튼 이벤트
        final TextView distancefind = (TextView) findViewById(R.id.distancefind);
        distancefind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tracking = 1 - tracking;

                if ( tracking == 1){
                    distancefind.setText("거리계산 끄기");
                  mToast = Toast.makeText(getApplicationContext(), "거리계산을 활성화 합니다.\n목적지 설정이 완료가 된다면 현재위치 중심으로 해당지역까지 거리를 측정합니다.", Toast.LENGTH_LONG);
                    mToast.show();
                }
                else {
                    distancefind.setText("거리계산 켜기");
                    for(int i=0; i<100; i++){
                        mToast.cancel();
                        Log.d(TAG,"cancle");
                    }
                    mToast = Toast.makeText(getApplicationContext(), "거리계산을 비활성화 합니다.", Toast.LENGTH_SHORT);
                    mToast.show();
                }

            }
        });


        mLayout = findViewById(R.id.layout_main);

        locationRequest = new LocationRequest()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(UPDATE_INTERVAL_MS)
                .setFastestInterval(FASTEST_UPDATE_INTERVAL_MS);


        LocationSettingsRequest.Builder builder =
                new LocationSettingsRequest.Builder();

        builder.addLocationRequest(locationRequest);


        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    //검색 함수
    public void Serchlocation(){
        Geocoder geocoder = new Geocoder(LocationMap.this);
        String str = editmap.getText().toString();

        List<Address> addresses = null;
        try {
            // editText에 입력한 텍스트(주소, 지역, 장소 등)을 지오 코딩을 이용해 변환
            addresses = geocoder.getFromLocationName(
                    str, // 주소
                    10); // 최대 검색 결과 개수
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        mMap.clear();
        if (previous_marker != null)
            previous_marker.clear();//지역정보 마커 클리어

        if (addresses!=null){
            if(addresses.size()==0){
                Toast.makeText(getApplicationContext(),"해당되는 주소 정보가 없습니다.", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "검색결과가 없습니다.");
            }
            else {
                System.out.println(addresses.get(0).toString());
                // 콤마를 기준으로 split
                String[] splitStr = addresses.get(0).toString().split(",");
                String address = splitStr[0].substring(splitStr[0].indexOf("\"") + 1, splitStr[0].length() - 2); // 주소
                System.out.println(address);

                String latitude = splitStr[10].substring(splitStr[10].indexOf("=") + 1); // 위도
                String longitude = splitStr[12].substring(splitStr[12].indexOf("=") + 1); // 경도
                System.out.println(latitude);
                System.out.println(longitude);
                // 좌표(위도, 경도) 생성
                point = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));
                // 마커 생성
                MarkerOptions mOptions2 = new MarkerOptions();
                mOptions2.title("검색결과 : " + str);
                mOptions2.snippet(address);
                mOptions2.position(point);

                //마커 이미지 변경
                BitmapDrawable bitmapdraw=(BitmapDrawable)getResources().getDrawable(R.drawable.search_location_icon);
                Bitmap b=bitmapdraw.getBitmap();
                Bitmap smallMarker = Bitmap.createScaledBitmap(b, 100, 100, false);
                mOptions2.icon(BitmapDescriptorFactory.fromBitmap(smallMarker));

                // 마커 추가
                mMap.addMarker(mOptions2);
                // 해당 좌표로 화면 줌
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(point, 15));
                Toast.makeText(getApplicationContext(),str + " 검색합니다.", Toast.LENGTH_SHORT).show();
            }
        }
        else if(str.equals("")){
            Toast.makeText(getApplicationContext(),"주소를 입력하여주세요.", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "주소값을 입력하여주세요");
        }

    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        Log.d(TAG, "onMapReady :");

        mMap = googleMap;

        //다이얼로그 목적지 설정
        TextView dialog_text = (TextView)findViewById(R.id.dialog_text);
        dialog_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(LocationMap.this);
                LayoutInflater inflater = getLayoutInflater();
                view = inflater.inflate(R.layout.place_info_dialog, null);

                final Button button_submit = (Button) view.findViewById(R.id.button_dialog_placeInfo);
                final EditText editText_placeTitle = (EditText) view.findViewById(R.id.editText_dialog_placeTitle);
                builder.setView(view);

                final AlertDialog dialog = builder.create();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));dialog.setCanceledOnTouchOutside(false);

                button_submit.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {

                        Geocoder geocoder = new Geocoder(LocationMap.this);
                        String string_placeTitle = editText_placeTitle.getText().toString();

                        List<Address> addresses2 = null;
                        try {
                            // editText에 입력한 텍스트(주소, 지역, 장소 등)을 지오 코딩을 이용해 변환
                            addresses2 = geocoder.getFromLocationName(
                                    string_placeTitle, // 주소
                                    10); // 최대 검색 결과 개수
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }

                        mMap.clear();
                        if (previous_marker != null)
                            previous_marker.clear();//지역정보 마커 클리어

                        if (addresses2!=null){
                            if(addresses2.size()==0){
                                Toast.makeText(getApplicationContext(),"해당되는 주소 정보가 없습니다.", Toast.LENGTH_SHORT).show();
                                Log.d(TAG, "검색결과가 없습니다.");
                            }
                            else {
                                System.out.println(addresses2.get(0).toString());
                                // 콤마를 기준으로 split
                                String[] splitStr = addresses2.get(0).toString().split(",");
                                String address = splitStr[0].substring(splitStr[0].indexOf("\"") + 1, splitStr[0].length() - 2); // 주소
                                System.out.println(address);

                                String latitude = splitStr[10].substring(splitStr[10].indexOf("=") + 1); // 위도
                                String longitude = splitStr[12].substring(splitStr[12].indexOf("=") + 1); // 경도
                                System.out.println(latitude);
                                System.out.println(longitude);
                                // 좌표(위도, 경도) 생성
                                point2 = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));

                                // 마커 생성
                                MarkerOptions mOptions3 = new MarkerOptions();
                                mOptions3.title("목적지 : " + string_placeTitle);
                                mOptions3.snippet(address);
                                mOptions3.position(point2);

                                //icon 변경
                                BitmapDrawable bitmapdraw=(BitmapDrawable)getResources().getDrawable(R.drawable.flag_location_icon);
                                Bitmap b=bitmapdraw.getBitmap();
                                Bitmap smallMarker = Bitmap.createScaledBitmap(b, 100, 100, false);
                                mOptions3.icon(BitmapDescriptorFactory.fromBitmap(smallMarker));

                                // 마커 추가
                                if ( addedMarker != null )
                                    mMap.clear();
                                addedMarker = mMap.addMarker(mOptions3);
                                // 해당 좌표로 화면 줌
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(point2, 15));
                                Toast.makeText(LocationMap.this, "목적지 선택완료, 거리계산을 활성화 시켜보세요",Toast.LENGTH_LONG).show();
                            }
                        }
                        else if(string_placeTitle.equals("")){
                            Toast.makeText(getApplicationContext(),"주소값을 입력하고 완료 버튼을 눌러주세요.", Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "주소값을 입력하여주세요");
                        }


                        dialog.dismiss();
                    }
                });

                dialog.show();

            }
        });




//런타임 퍼미션 요청 대화상자나 GPS 활성 요청 대화상자 보이기전에
//지도의 초기위치를 서울로 이동
        setDefaultLocation();



//런타임 퍼미션 처리
// 1. 위치 퍼미션을 가지고 있는지 체크합니다.
        int hasFineLocationPermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION);



        if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED &&
                hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED ) {

// 2. 이미 퍼미션을 가지고 있다면
// ( 안드로이드 6.0 이하 버전은 런타임 퍼미션이 필요없기 때문에 이미 허용된 걸로 인식합니다.)


            startLocationUpdates(); // 3. 위치 업데이트 시작


        }else { //2. 퍼미션 요청을 허용한 적이 없다면 퍼미션 요청이 필요합니다. 2가지 경우(3-1, 4-1)가 있습니다.

// 3-1. 사용자가 퍼미션 거부를 한 적이 있는 경우에는
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[0])) {

// 3-2. 요청을 진행하기 전에 사용자가에게 퍼미션이 필요한 이유를 설명해줄 필요가 있습니다.
                Snackbar.make(mLayout, "이 앱을 실행하려면 위치 접근 권한이 필요합니다.",
                        Snackbar.LENGTH_INDEFINITE).setAction("확인", new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {

// 3-3. 사용자게에 퍼미션 요청을 합니다. 요청 결과는 onRequestPermissionResult에서 수신됩니다.
                        ActivityCompat.requestPermissions( LocationMap.this, REQUIRED_PERMISSIONS,
                                PERMISSIONS_REQUEST_CODE);
                    }
                }).show();


            } else {
// 4-1. 사용자가 퍼미션 거부를 한 적이 없는 경우에는 퍼미션 요청을 바로 합니다.
// 요청 결과는 onRequestPermissionResult에서 수신됩니다.
                ActivityCompat.requestPermissions( this, REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE);
            }

        }



        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng latLng) {
                Log.d( TAG, "onMapClick :");
            }
        });
    }

    LocationCallback locationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            super.onLocationResult(locationResult);

            List<Location> locationList = locationResult.getLocations();

            if (locationList.size() > 0) {
                location = locationList.get(locationList.size() - 1);

                //거리계산-------------------------------------------//
                previousPosition = currentPosition;

                currentPosition = new LatLng(location.getLatitude(), location.getLongitude());

                if ( addedMarker != null ) {
                    Log.d(TAG, "item" + addedMarker.getPosition());
                    Log.d(TAG, "item" + addedMarker.getTitle());
                }
                if (previousPosition == null) previousPosition = currentPosition;

                if ( (addedMarker != null) && tracking == 1 ) {
                    double radius = 100; // 100m distance. 10km 범위안에 역찾음
                    double distance = SphericalUtil.computeDistanceBetween(currentPosition, addedMarker.getPosition());

                    if ( distance> radius && (!previousPosition.equals(currentPosition))) {
                        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(currentPosition, 15);
                        mMap.moveCamera(cameraUpdate);
                        mMasage=addedMarker.getTitle() + "까지" + (int) distance + "m 남음";
                        mToast = Toast.makeText(LocationMap.this, mMasage, Toast.LENGTH_LONG);
                        mToast.show();
                    } else if (distance < radius && (!previousPosition.equals(currentPosition))){  //목적지 도착
                        vibrator.vibrate(1000);
                        mMasage = addedMarker.getTitle() + " 도착";
                        mToast = Toast.makeText(LocationMap.this, mMasage, Toast.LENGTH_LONG);
                        mToast.show();
                        Log.d(TAG, "no cancle" );
                    }
                }
                //-------------------------------------------//

                String markerTitle = getCurrentAddress(currentPosition);
                String markerSnippet = "위도:" + String.valueOf(location.getLatitude())
                        + " 경도:" + String.valueOf(location.getLongitude());

                Log.d(TAG, "onLocationResult : " + markerSnippet);


//현재 위치에 마커 생성하고 이동
                setCurrentLocation(location, markerTitle, markerSnippet);

                mCurrentLocatiion = location;
            }


        }

    };



    private void startLocationUpdates() {

        if (!checkLocationServicesStatus()) {

            Log.d(TAG, "startLocationUpdates : call showDialogForLocationServiceSetting");
            showDialogForLocationServiceSetting();
        }else {

            int hasFineLocationPermission = ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION);
            int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION);



            if (hasFineLocationPermission != PackageManager.PERMISSION_GRANTED ||
                    hasCoarseLocationPermission != PackageManager.PERMISSION_GRANTED ) {

                Log.d(TAG, "startLocationUpdates : 퍼미션 안가지고 있음");
                return;
            }


            Log.d(TAG, "startLocationUpdates : call mFusedLocationClient.requestLocationUpdates");

            mFusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper());

            if (checkPermission())
                mMap.setMyLocationEnabled(true);

        }

    }


    @Override
    protected void onStart() {
        super.onStart();

        Log.d(TAG, "onStart");

        if (checkPermission()) {

            Log.d(TAG, "onStart : call mFusedLocationClient.requestLocationUpdates");
            mFusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);

            if (mMap!=null)
                mMap.setMyLocationEnabled(true);
        }


    }


    @Override
    protected void onStop() {

        super.onStop();

        if (mFusedLocationClient != null) {

            Log.d(TAG, "onStop : call stopLocationUpdates");
            mFusedLocationClient.removeLocationUpdates(locationCallback);
        }
    }




    public String getCurrentAddress(LatLng latlng) {

//지오코더... GPS를 주소로 변환
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        List<Address> addresses;

        try {

            addresses = geocoder.getFromLocation(
                    latlng.latitude,
                    latlng.longitude,
                    1);
        } catch (IOException ioException) {
//네트워크 문제
            Toast.makeText(this, "지오코더 서비스 사용불가", Toast.LENGTH_LONG).show();
            return "지오코더 서비스 사용불가";
        } catch (IllegalArgumentException illegalArgumentException) {
            Toast.makeText(this, "잘못된 GPS 좌표", Toast.LENGTH_LONG).show();
            return "잘못된 GPS 좌표";

        }


        if (addresses == null || addresses.size() == 0) {
            return "주소 미발견";

        } else {
            Address address = addresses.get(0);
            return address.getAddressLine(0).toString();
        }

    }


    public boolean checkLocationServicesStatus() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }


    public void setCurrentLocation(Location location, String markerTitle, String markerSnippet) {


        if (currentMarker != null) currentMarker.remove();


        LatLng currentLatLng = new LatLng(location.getLatitude(), location.getLongitude());

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(currentLatLng);
        markerOptions.title("현재 위치 :"+markerTitle);
        markerOptions.snippet(markerSnippet);
        markerOptions.draggable(true);

        currentMarker = mMap.addMarker(markerOptions);

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLng(currentLatLng);
        //mMap.moveCamera(cameraUpdate);
    }


    public void setDefaultLocation() {


//디폴트 위치, Seoul
        LatLng DEFAULT_LOCATION = new LatLng(37.56, 126.97);
        String markerTitle = "위치정보 가져올 수 없음";
        String markerSnippet = "위치 퍼미션과 GPS 활성 요부 확인하세요";


        if (currentMarker != null) currentMarker.remove();

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(DEFAULT_LOCATION);
        markerOptions.title(markerTitle);
        markerOptions.snippet(markerSnippet);
        markerOptions.draggable(true);
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        currentMarker = mMap.addMarker(markerOptions);

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(DEFAULT_LOCATION, 15);
        mMap.moveCamera(cameraUpdate);

    }


    //여기부터는 런타임 퍼미션 처리을 위한 메소드들
    private boolean checkPermission() {

        int hasFineLocationPermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION);



        if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED &&
                hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED ) {
            return true;
        }

        return false;

    }



    /*
     * ActivityCompat.requestPermissions를 사용한 퍼미션 요청의 결과를 리턴받는 메소드입니다.
     */
    @Override
    public void onRequestPermissionsResult(int permsRequestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grandResults) {

        if ( permsRequestCode == PERMISSIONS_REQUEST_CODE && grandResults.length == REQUIRED_PERMISSIONS.length) {

// 요청 코드가 PERMISSIONS_REQUEST_CODE 이고, 요청한 퍼미션 개수만큼 수신되었다면

            boolean check_result = true;


// 모든 퍼미션을 허용했는지 체크합니다.

            for (int result : grandResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    check_result = false;
                    break;
                }
            }


            if ( check_result ) {

// 퍼미션을 허용했다면 위치 업데이트를 시작합니다.
                startLocationUpdates();
            }
            else {
// 거부한 퍼미션이 있다면 앱을 사용할 수 없는 이유를 설명해주고 앱을 종료합니다.2 가지 경우가 있습니다.

                if (ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[0])
                        || ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[1])) {


// 사용자가 거부만 선택한 경우에는 앱을 다시 실행하여 허용을 선택하면 앱을 사용할 수 있습니다.
                    Snackbar.make(mLayout, "퍼미션이 거부되었습니다. 앱을 다시 실행하여 퍼미션을 허용해주세요. ",
                            Snackbar.LENGTH_INDEFINITE).setAction("확인", new View.OnClickListener() {

                        @Override
                        public void onClick(View view) {

                            finish();
                        }
                    }).show();

                }else {


// "다시 묻지 않음"을 사용자가 체크하고 거부를 선택한 경우에는 설정(앱 정보)에서 퍼미션을 허용해야 앱을 사용할 수 있습니다.
                    Snackbar.make(mLayout, "퍼미션이 거부되었습니다. 설정(앱 정보)에서 퍼미션을 허용해야 합니다. ",
                            Snackbar.LENGTH_INDEFINITE).setAction("확인", new View.OnClickListener() {

                        @Override
                        public void onClick(View view) {

                            finish();
                        }
                    }).show();
                }
            }

        }
    }


    //여기부터는 GPS 활성화를 위한 메소드들
    private void showDialogForLocationServiceSetting() {

        AlertDialog.Builder builder = new AlertDialog.Builder(LocationMap.this);
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

                        Log.d(TAG, "onActivityResult : GPS 활성화 되있음");


                        needRequest = true;
                        return;
                    }
                }

                break;
        }
    }


    List<Marker> previous_marker = null;

    @Override
    public void onPlacesFailure(PlacesException e) {

    }

    @Override
    public void onPlacesStart() {

    }

    @Override
    public void onPlacesSuccess(final List <Place> places) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for (Place place : places) {

                    LatLng latLng
                            = new LatLng(place.getLatitude()
                            , place.getLongitude());

                    String markerSnippet = getCurrentAddress(latLng);

                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(latLng);
                    markerOptions.title(place.getName());
                    markerOptions.snippet(markerSnippet);

                    BitmapDrawable bitmapdraw=(BitmapDrawable)getResources().getDrawable(R.drawable.subway_location_icon);
                    Bitmap b=bitmapdraw.getBitmap();
                    Bitmap smallMarker = Bitmap.createScaledBitmap(b, 100, 100, false);
                    markerOptions.icon(BitmapDescriptorFactory.fromBitmap(smallMarker));

                    item = mMap.addMarker(markerOptions);
                    previous_marker.add(item);

                }

//중복 마커 제거

                HashSet<Marker> hashSet = new HashSet<Marker>();
                hashSet.addAll(previous_marker);
                previous_marker.clear();
                previous_marker.addAll(hashSet);

            }
        });
    }
    public void showPlaceInformation(LatLng location)
    {
/*
        mMap.clear();//지도 클리어

        if (previous_marker != null)
            previous_marker.clear();//지역정보 마커 클리어

 */
        new NRPlaces.Builder()
                .listener(LocationMap.this)
                .key("AIzaSyAGBcxJu4qcrbg1zq7EbWofDVG1iCSRcvg")
                .latlng(location.latitude, location.longitude)//현재 위치
                .radius(1000) //1000 미터 내에서 검색
                .type(PlaceType.SUBWAY_STATION) //지하철
                .language("ko", "KR")
                .build()
                .execute();
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15));
    }
    @Override
    public void onPlacesFinished() {

    }
}