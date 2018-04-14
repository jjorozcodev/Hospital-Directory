package com.uca.jj.apps.hospitaldirectory.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class CommentModel {
    private String text;
    @SerializedName("created_id")
    private String createdDt;
    @SerializedName("user_id")
    private int userId;
    @SerializedName("hospital_id")
    private int hospitalId;
    private int id;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCreatedDt() {
        return createdDt;
    }

    public void setCreatedDt(String createdDt) {
        this.createdDt = createdDt;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(int hospitalId) {
        this.hospitalId = hospitalId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
