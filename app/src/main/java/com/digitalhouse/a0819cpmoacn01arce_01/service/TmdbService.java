package com.digitalhouse.a0819cpmoacn01arce_01.service;

import com.digitalhouse.a0819cpmoacn01arce_01.model.Extra;
import com.digitalhouse.a0819cpmoacn01arce_01.model.Extras;
import com.digitalhouse.a0819cpmoacn01arce_01.model.People;
import com.digitalhouse.a0819cpmoacn01arce_01.model.ResultExtras;
import com.digitalhouse.a0819cpmoacn01arce_01.model.ResultTodo;
import com.digitalhouse.a0819cpmoacn01arce_01.model.Videos;
import com.digitalhouse.a0819cpmoacn01arce_01.model.peliculas.CastPeliculaReparto;
import com.digitalhouse.a0819cpmoacn01arce_01.model.peliculas.Pelicula;
import com.digitalhouse.a0819cpmoacn01arce_01.model.peliculas.ResultPelicula;
import com.digitalhouse.a0819cpmoacn01arce_01.model.series.Credits;
import com.digitalhouse.a0819cpmoacn01arce_01.model.series.ResultSeriesPopulares;
import com.digitalhouse.a0819cpmoacn01arce_01.model.series.SerieDetalles;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TmdbService {

    @GET("movie/now_playing?api_key=e00143389939e722c4dd551059122bbf&language=es-Ar&page=1&region=AR")
    Call<ResultPelicula> getPeliculasCartelera(
            @Query("page") Integer page
    );

    @GET("movie/popular?api_key=e3b80491b9d16d2154e7064f609cbcd5&language=es-AR&page=1&region=AR")
    Call<ResultPelicula> getPeliculasPopulares();

    @GET("movie/{movie_id}?api_key=e00143389939e722c4dd551059122bbf&language=es-AR")
    Call<Pelicula> getPeliculasDetalleCartelera(@Path("movie_id") String id);

    @GET("movie/{movie_id}/credits?api_key=e00143389939e722c4dd551059122bbf")
    Call<CastPeliculaReparto> getPeliculaReparto(@Path("movie_id") String id);

    @GET("movie/{movie_id}/videos?api_key=e00143389939e722c4dd551059122bbf")
    Call<Videos> getVideos(@Path("movie_id") String id);

    @GET("tv/{tv_id}/videos?api_key=e00143389939e722c4dd551059122bbf")
    Call<Videos> getVideosSeries(@Path("tv_id") String id);

    @GET("tv/popular?api_key=e00143389939e722c4dd551059122bbf&language=en-US&page=1")
    Call<ResultSeriesPopulares> getSeriesPopulares();

    @GET("tv/{tv_id}?api_key=e00143389939e722c4dd551059122bbf&language=es")
    Call<SerieDetalles> getDetalleSeries(@Path("tv_id") String id);

    @GET("tv/{tv_id}/credits?api_key=e00143389939e722c4dd551059122bbf&language=es")
    Call<Credits> getCredits(@Path("tv_id") String id);

    @GET("person/{person_id}?api_key=e00143389939e722c4dd551059122bbf&language=es-AR")
    Call<People> getPeople(@Path("person_id") String id);

    @GET("search/multi?api_key=e00143389939e722c4dd551059122bbf&language=es-AR&page=1&include_adult=false")
    Call<ResultTodo> getBusqueda(@Query("query") String key);

    @GET("movie/ {movie_id}")
    Call<Extra> getExtras(@Path("movie_id") int id,
                          @Query("api_key") String api_key);



}

