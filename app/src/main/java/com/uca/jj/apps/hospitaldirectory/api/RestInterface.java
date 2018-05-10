package com.uca.jj.apps.hospitaldirectory.api;

import com.uca.jj.apps.hospitaldirectory.models.AuthorModel;
import com.uca.jj.apps.hospitaldirectory.models.CommentModel;
import com.uca.jj.apps.hospitaldirectory.models.CommentRequest;
import com.uca.jj.apps.hospitaldirectory.models.HospitalModel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;


public interface RestInterface {
    @GET("hospitals")
    Call<ArrayList<HospitalModel>> allHospitals();

    @GET("comments")
    Call<ArrayList<CommentModel>> allComments();

    @GET("authors")
    Call<ArrayList<AuthorModel>> allAuthors();

    @DELETE("comments/{id}")
    Call<CommentModel> deleteComment(@Path("id") int id);

    @PUT("comments/{id}")
    Call<CommentModel> updateComment(@Path("id") int id, @Body CommentModel commentModel);

    @POST("comments")
    Call<CommentModel> postComment(@Body CommentRequest commentRequest);
}
