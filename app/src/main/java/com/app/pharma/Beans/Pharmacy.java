package com.app.pharma.Beans;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Pharmacy extends RealmObject {

    @PrimaryKey
    private String id;
    private String name;
    private String address;
    private String longitude;
    private String latitude;
    private String phone;
    private String cityName;
    private String open;

    public Pharmacy(String id, String name, String address, String longitude, String latitude, String phone, String cityName, String open) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
        this.phone = phone;
        this.cityName = cityName;
        this.open = open;
    }


    public Pharmacy() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }



    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
