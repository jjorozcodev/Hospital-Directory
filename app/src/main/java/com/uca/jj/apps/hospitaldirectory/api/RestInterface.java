package com.uca.jj.apps.hospitaldirectory.api;

import com.uca.jj.apps.hospitaldirectory.models.AuthorModel;
import com.uca.jj.apps.hospitaldirectory.models.CommentModel;
import com.uca.jj.apps.hospitaldirectory.models.HospitalModel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface RestInterface {
    @GET("hospitals")
    Call<ArrayList<HospitalModel>> allHospitals();

    @GET("hospitals/{id}/comments")
    Call<ArrayList<CommentModel>> commentsHospital(@Path("id") int id);

    @GET("authors/{id}")
    Call<AuthorModel> getAuthor(@Path("id") int id);


}
