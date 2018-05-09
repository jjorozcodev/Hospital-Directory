package com.uca.jj.apps.hospitaldirectory.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class CommentRequest {
    private String message;
    @SerializedName("created_dt")
    private String createdDt;
    @SerializedName("author_id")
    private int authorId;
    @SerializedName("hospital_id")
    private int hospitalId;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCreatedDt() {
        return createdDt;
    }

    public void setCreatedDt(String createdDt) {
        this.createdDt = createdDt;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(int hospitalId) {
        this.hospitalId = hospitalId;
    }
}
