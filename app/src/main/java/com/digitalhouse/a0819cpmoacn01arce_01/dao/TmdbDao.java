package com.digitalhouse.a0819cpmoacn01arce_01.dao;

import com.digitalhouse.a0819cpmoacn01arce_01.service.TmdbService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TmdbDao {

    public static final String LANGUAGE = "Es-Ar";
    public static final String API_KEY = "e00143389939e722c4dd551059122bbf";
    public static final String BASE_URL = "https://api.themoviedb.org/3/";

    protected Retrofit retrofit;
    protected TmdbService tmdbService;


    public TmdbDao() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        tmdbService = retrofit.create(TmdbService.class);

    }
}
