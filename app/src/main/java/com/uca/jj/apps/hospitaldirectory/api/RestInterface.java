package com.uca.jj.apps.hospitaldirectory.api;

import com.uca.jj.apps.hospitaldirectory.models.HospitalModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface RestInterface {
    @GET("hospitals")
    Call<List<HospitalModel>> allHospitals();
}
