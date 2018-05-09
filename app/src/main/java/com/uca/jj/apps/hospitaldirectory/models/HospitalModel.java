package com.uca.jj.apps.hospitaldirectory.models;

import io.realm.RealmObject;

public class HospitalModel extends RealmObject {
    private int id;
    private String name;
    private String slogan;
    private String description;
    private int telephone;
    private String address;
    private String website;
    private float latitude;
    private float length;
    private String urlImgLarge;
    private String urlImgSmall;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public String getUrlImgLarge() {
        return urlImgLarge;
    }

    public void setUrlImgLarge(String urlImgLarge) {
        this.urlImgLarge = urlImgLarge;
    }

    public String getUrlImgSmall() {
        return urlImgSmall;
    }

    public void setUrlImgSmall(String urlImgSmall) {
        this.urlImgSmall = urlImgSmall;
    }
}
