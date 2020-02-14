package com.digitalhouse.a0819cpmoacn01arce_01.dao;

import androidx.annotation.NonNull;

import com.digitalhouse.a0819cpmoacn01arce_01.model.People;
import com.digitalhouse.a0819cpmoacn01arce_01.model.ResultTodo;

import com.digitalhouse.a0819cpmoacn01arce_01.model.Videos;
import com.digitalhouse.a0819cpmoacn01arce_01.model.User;

import com.digitalhouse.a0819cpmoacn01arce_01.model.peliculas.Pelicula;
import com.digitalhouse.a0819cpmoacn01arce_01.model.series.Serie;
import com.digitalhouse.a0819cpmoacn01arce_01.utils.ResultListener;
import com.digitalhouse.a0819cpmoacn01arce_01.model.peliculas.CastPeliculaReparto;
import com.digitalhouse.a0819cpmoacn01arce_01.model.peliculas.PeliculaCrew;
import com.digitalhouse.a0819cpmoacn01arce_01.model.peliculas.PeliculaReparto;
import com.digitalhouse.a0819cpmoacn01arce_01.model.peliculas.ResultPelicula;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PeliculaDAO extends TmdbDao {

    private FirebaseAuth mAuth;
    private String userId;
    private FirebaseFirestore db;



    public void getPeliculasCartelera(final ResultListener<ResultPelicula> escuchadorDelControlador, Integer page) {
        Call<ResultPelicula> call = this.tmdbService.getPeliculasCartelera(page);
        call.enqueue(new Callback<ResultPelicula>() {
            @Override
            public void onResponse(Call<ResultPelicula> call, Response<ResultPelicula> response) {
                ResultPelicula peliculas = response.body();
                escuchadorDelControlador.onFinish(peliculas);

            }

            @Override
            public void onFailure(Call<ResultPelicula> call, Throwable t) {
                t.printStackTrace();
            }
        });


    }

    public void getPeliculasPopulares(final ResultListener<ResultPelicula> escuchadorDelControlador) {
        Call<ResultPelicula> call = this.tmdbService.getPeliculasPopulares();
        call.enqueue(new Callback<ResultPelicula>() {
            @Override
            public void onResponse(Call<ResultPelicula> call, Response<ResultPelicula> response) {
                ResultPelicula peliculas = response.body();
                escuchadorDelControlador.onFinish(peliculas);


            }

            @Override
            public void onFailure(Call<ResultPelicula> call, Throwable t) {
                t.printStackTrace();
            }
        });


    }

    public void getPeliculasDetalleCartelera(String id, final ResultListener<Pelicula> escuchadorDelControlador) {
        Call<Pelicula> call = this.tmdbService.getPeliculasDetalleCartelera(id);
        call.enqueue(new Callback<Pelicula>() {
            @Override
            public void onResponse(Call<Pelicula> call, Response<Pelicula> response) {
                Pelicula pelicula = response.body();
                escuchadorDelControlador.onFinish(pelicula);
            }

            @Override
            public void onFailure(Call<Pelicula> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void getPeliculaReparto(String id, final ResultListener<List<PeliculaReparto>> escuchadorDelControlador) {
        Call<CastPeliculaReparto> call = this.tmdbService.getPeliculaReparto(id);
        call.enqueue(new Callback<CastPeliculaReparto>() {
            @Override
            public void onResponse(Call<CastPeliculaReparto> call, Response<CastPeliculaReparto> response) {
                List<PeliculaReparto> peliculaRepartos = response.body().getPeliculaRepartoList();
                escuchadorDelControlador.onFinish(peliculaRepartos);
            }

            @Override
            public void onFailure(Call<CastPeliculaReparto> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void getPeliculaCrew(String id, final ResultListener<List<PeliculaCrew>> escuchadorDelControlador) {
        Call<CastPeliculaReparto> call = this.tmdbService.getPeliculaReparto(id);
        call.enqueue(new Callback<CastPeliculaReparto>() {
            @Override
            public void onResponse(Call<CastPeliculaReparto> call, Response<CastPeliculaReparto> response) {
                List<PeliculaCrew> peliculaCrews = response.body().getPeliculaCrewList();
                escuchadorDelControlador.onFinish(peliculaCrews);
            }

            @Override
            public void onFailure(Call<CastPeliculaReparto> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void getActoresPeliculas(String id, final ResultListener<People> escuchadorDelControlador) {
        Call<People> call = this.tmdbService.getPeople(id);
        call.enqueue(new Callback<People>() {
            @Override
            public void onResponse(Call<People> call, Response<People> response) {
                People people = response.body();
                escuchadorDelControlador.onFinish(people);
            }

            @Override
            public void onFailure(Call<People> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void getVideos(String id, final ResultListener<Videos> escuchadorDelControlador) {
        Call<Videos> call = this.tmdbService.getVideos(id);
        call.enqueue(new Callback<Videos>() {
            @Override
            public void onResponse(Call<Videos> call, Response<Videos> response) {
                Videos videos = response.body();
                escuchadorDelControlador.onFinish(videos);
            }

            @Override
            public void onFailure(Call<Videos> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void getBusqueda(final ResultListener<ResultTodo> escuchadorDelController, String key) {

        Call<ResultTodo> call = this.tmdbService.getBusqueda(key);

        if (key.isEmpty()) {
            return;
        }
        call.enqueue(new Callback<ResultTodo>() {
            @Override
            public void onResponse(Call<ResultTodo> call, Response<ResultTodo> response) {
                if (response.isSuccessful()) {
                    ResultTodo resultTodo = response.body();
                    escuchadorDelController.onFinish(resultTodo);
                }
            }

            @Override
            public void onFailure(Call<ResultTodo> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void addPeliculaFav(final ResultListener<Boolean> resultListener,final Pelicula pelicula){

        initFirebaseUser().get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                User user = documentSnapshot.toObject(User.class);
                List<Pelicula> peliculaFavList = user.getPeliculasFavs();
                peliculaFavList.add(pelicula);
                user.setPeliculasFavs(peliculaFavList);
                db.collection("usuarios").document(userId).set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        resultListener.onFinish(true);
                    }


                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        resultListener.onFinish(false);
                    }
                });
            }


        });

    }


    public void removePeliculaFav(final ResultListener<Boolean> resultListener,final Pelicula pelicula){

        initFirebaseUser().get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                User user = documentSnapshot.toObject(User.class);
                List<Pelicula> peliculaFavList = user.getPeliculasFavs();
                List<Pelicula> aPeliculaFavList = peliculaFavList;
                for (Pelicula unaPelicula: aPeliculaFavList ){
                    if (unaPelicula.getId().equals(pelicula.getId())){
                        peliculaFavList.remove(unaPelicula);
                        break;
                    }
                }
                user.setPeliculasFavs(peliculaFavList);
                db.collection("usuarios").document(userId).set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        resultListener.onFinish(true);
                    }


                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        resultListener.onFinish(false);
                    }
                });
            }


        });

    }

    public void getPeliculasFavs(final ResultListener<List<Pelicula>> resultListener){

        initFirebaseUser().get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                User user = documentSnapshot.toObject(User.class);
                List<Pelicula> peliculaFavList = user.getPeliculasFavs();
                resultListener.onFinish(peliculaFavList);

            }


        });

    }




    public DocumentReference initFirebaseUser(){

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        userId = currentUser.getUid();
        DocumentReference docRef = db.collection("usuarios").document(userId);
        return docRef;

    }


}