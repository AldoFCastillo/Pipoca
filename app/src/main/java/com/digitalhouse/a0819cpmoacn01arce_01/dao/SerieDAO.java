package com.digitalhouse.a0819cpmoacn01arce_01.dao;


import androidx.annotation.NonNull;

import com.digitalhouse.a0819cpmoacn01arce_01.model.Videos;
import com.digitalhouse.a0819cpmoacn01arce_01.model.peliculas.Pelicula;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.digitalhouse.a0819cpmoacn01arce_01.model.User;
import com.digitalhouse.a0819cpmoacn01arce_01.model.peliculas.Pelicula;
import com.digitalhouse.a0819cpmoacn01arce_01.model.series.Credits;
import com.digitalhouse.a0819cpmoacn01arce_01.model.series.ResultSeriesPopulares;
import com.digitalhouse.a0819cpmoacn01arce_01.model.series.Serie;
import com.digitalhouse.a0819cpmoacn01arce_01.model.series.SerieDetalles;
import com.digitalhouse.a0819cpmoacn01arce_01.utils.ResultListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SerieDAO extends TmdbDao {

    private FirebaseAuth mAuth;
    private List<Serie> serieFavList = new ArrayList<>();
    private List<Pelicula> peliculaFavList = new ArrayList<>();
    private String userId;

    private FirebaseFirestore db;

    public void getSeries(final ResultListener<ResultSeriesPopulares> resultListener) {

        Call<ResultSeriesPopulares> call = tmdbService.getSeriesPopulares();

        call.enqueue(new Callback<ResultSeriesPopulares>() {
            @Override
            public void onResponse(Call<ResultSeriesPopulares> call, Response<ResultSeriesPopulares> response) {
                ResultSeriesPopulares seriesPopulares = response.body();

                resultListener.onFinish(seriesPopulares);

            }

            @Override
            public void onFailure(Call<ResultSeriesPopulares> call, Throwable t) {
                String message = t.getMessage();
                System.out.println("ha ocurrido un error" + message);
                t.printStackTrace();

            }
        });


    }

    public void getSerieDetalles(final ResultListener<SerieDetalles> resultListener, String id) {

        Call<SerieDetalles> call = tmdbService.getDetalleSeries(id);

        call.enqueue(new Callback<SerieDetalles>() {
            @Override
            public void onResponse(Call<SerieDetalles> call, Response<SerieDetalles> response) {
                SerieDetalles seriesDetalles = response.body();

                resultListener.onFinish(seriesDetalles);

            }

            @Override
            public void onFailure(Call<SerieDetalles> call, Throwable t) {
                String message = t.getMessage();
                System.out.println("ha ocurrido un error" + message);
                t.printStackTrace();

            }
        });

    }

    public void getSerieCredits(final ResultListener<Credits> resultListener, String id) {

        Call<Credits> call = tmdbService.getCredits(id);

        call.enqueue(new Callback<Credits>() {
            @Override
            public void onResponse(Call<Credits> call, Response<Credits> response) {
                Credits seriesCredits = response.body();

                resultListener.onFinish(seriesCredits);

            }

            @Override
            public void onFailure(Call<Credits> call, Throwable t) {
                String message = t.getMessage();
                System.out.println("ha ocurrido un error" + message);
                t.printStackTrace();

            }
        });

    }

    public void getSeriesFavs(final ResultListener<List<Serie>> resultListener) {

        initFirebaseUser().get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                User user = documentSnapshot.toObject(User.class);
                List<Serie> serieFavList = user.getSeriesFavs();
                resultListener.onFinish(serieFavList);

            }


        });

    }

    public DocumentReference initFirebaseUser() {

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        userId = currentUser.getUid();
        DocumentReference docRef = db.collection("usuarios").document(userId);
        return docRef;

    }


    public void addSerieFav(final ResultListener<Boolean> resultListener, final Serie unaSerie) {

        initFirebaseUser().get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                User user = documentSnapshot.toObject(User.class);
                List<Serie> serieFavList = user.getSeriesFavs();
                serieFavList.add(unaSerie);
                user.setSeriesFavs(serieFavList);
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


    public void removeSerieFav(final ResultListener<Boolean> resultListener, final Serie unaSerie) {

        initFirebaseUser().get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                User user = documentSnapshot.toObject(User.class);
                List<Serie> serieFavList = user.getSeriesFavs();
                List<Serie> aSerieFavList = serieFavList;
                for (Serie serie : aSerieFavList) {
                    if (serie.getId().equals(unaSerie.getId())) {
                        serieFavList.remove(serie);
                        break;
                    }
                }
                user.setSeriesFavs(serieFavList);
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

    public void getVideosSeries(String id, final ResultListener<Videos> escuchadorDelControlador){
        Call<Videos> call = tmdbService.getVideosSeries(id);
        call.enqueue(new Callback<Videos>() {
            @Override
            public void onResponse(Call<Videos> call, Response<Videos> response) {
                Videos videos = response.body();
                escuchadorDelControlador.onFinish(videos);
            }

            @Override
            public void onFailure(Call<Videos> call, Throwable t) {

            }
        });
    }

}
