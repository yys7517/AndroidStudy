package com.sungkyul.osan.map;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sungkyul.osan.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MapListViewAdapter extends BaseAdapter {

    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<MapListViewItem> items = new ArrayList<MapListViewItem>() ;

    // ListViewAdapter의 생성자
    public MapListViewAdapter() {

    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_map, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        TextView txtName = (TextView) convertView.findViewById(R.id.txtName);
        TextView txtDistance = (TextView) convertView.findViewById(R.id.txtDistance);

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        MapListViewItem mMapListViewItem = items.get(position);


        DecimalFormat formatter = new DecimalFormat("###,###.#");

        // 아이템 내 각 위젯에 데이터 반영
        txtName.setText(mMapListViewItem.getName());
        txtDistance.setText(formatter.format(mMapListViewItem.getDistance()) + "KM");

        return convertView;
    }

    // 아이템 데이터 추가
    public void addItem(String Name, Double Distance) {
        MapListViewItem mMapListViewItem = new MapListViewItem();
        mMapListViewItem.setName(Name);
        mMapListViewItem.setDistance(Distance);
        items.add(mMapListViewItem);
        Log.i("리스트", "시설명: " + Name + " 거리: " + Distance);
    }

    public void clearItem() {
        items.clear();
    }
}
