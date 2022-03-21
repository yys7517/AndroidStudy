package com.sungkyul.osan.map;

import android.os.Bundle;

import java.util.List;

public class LocationThread extends Kakaomap implements Runnable {

    //Handler mHandlerName = HandlerName;
    //Handler mHandlerDistance = HandlerDistance;

    double latitude; //리스트에 담겨진 시설명 위치 변수
    double longitude; //리스트에 담겨진 시설명 위치 변수
    String name;

    ItemResult mItemResult = new ItemResult();

    public LocationThread(double latitude, double longitude, List<Item> items) {
        locationA.setLatitude(latitude); //현재위치
        locationA.setLongitude(longitude); //현재위치
        this.items = items; //DB에 넣은 시설명 list
    }

    @Override
    public void run() {
        for (int i = 0; i < items.size(); i++) {

            /* TODO : 데이터 처리 /
            name = items.get(i).getName();
            latitude = items.get(i).getLatitude();
            longitude = items.get(i).getLongitude();

            locationB.setLatitude(latitude);
            locationB.setLongitude(longitude);

            distance = Math.round(locationA.distanceTo(locationB));

            mItemResult.setName(name);
            mItemResult.setDistance(distance);

            / TODO : 핸들러를 통해 데이터 전송 /
            //Message mMessageName = mHandlerName.obtainMessage();
            //Message mMessageDistance = mHandlerDistance.obtainMessage();

            / TODO : 시설명 전송 /
            Bundle mBundleName = new Bundle();
            mBundleName.putString("name", name);
            //mMessageName.setData(mBundleName);
            //mHandlerName.sendMessage(mMessageName);

            / TODO : 거리 전송 */
            Bundle mBundleDistance = new Bundle();
            mBundleDistance.putDouble("distance", distance);
            //mMessageDistance.setData(mBundleDistance);
            //mHandlerDistance.sendMessage(mMessageDistance);


        }
    }
}