package com.sungkyul.osan.map;

public class DataLocation {
    private double latitude; //위도
    private double longitude; //경도
    private String name; //시설명

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
