package com.uca.jj.apps.hospitaldirectory.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Rest {

    private final static String URL_BASE = "https://hospital-directory.herokuapp.com/api/";

    public static RestInterface instance() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(RestInterface.class);
    }

}
