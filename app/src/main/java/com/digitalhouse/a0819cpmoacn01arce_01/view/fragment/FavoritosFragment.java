package com.digitalhouse.a0819cpmoacn01arce_01.view.fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.digitalhouse.a0819cpmoacn01arce_01.R;
import com.digitalhouse.a0819cpmoacn01arce_01.controller.PeliculaController;
import com.digitalhouse.a0819cpmoacn01arce_01.controller.SerieController;
import com.digitalhouse.a0819cpmoacn01arce_01.model.User;
import com.digitalhouse.a0819cpmoacn01arce_01.model.peliculas.Pelicula;
import com.digitalhouse.a0819cpmoacn01arce_01.model.peliculas.ResultPelicula;
import com.digitalhouse.a0819cpmoacn01arce_01.model.series.ResultSeriesPopulares;
import com.digitalhouse.a0819cpmoacn01arce_01.model.series.Serie;
import com.digitalhouse.a0819cpmoacn01arce_01.utils.ResultListener;
import com.digitalhouse.a0819cpmoacn01arce_01.view.adapter.CarteleraAdapter;
import com.digitalhouse.a0819cpmoacn01arce_01.view.adapter.FavoritosAdapter;
import com.digitalhouse.a0819cpmoacn01arce_01.view.adapter.PeliculaAdapter;
import com.digitalhouse.a0819cpmoacn01arce_01.view.adapter.SerieAdapter;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritosFragment extends Fragment implements SerieAdapter.serieAdapterListener, CarteleraAdapter.peliculaAdapterListener {


    @BindView(R.id.recyclerViewFavoritosFragment)
    RecyclerView recyclerViewFavoritosFragment;
    @BindView(R.id.botonPeliculasFavoritos)
    Button botonPeliculasFavoritos;
    @BindView(R.id.botonSeriesFvoritos)
    Button botonSeriesFvoritos;
    @BindView(R.id.emptyStateFavoritos)
    ImageView emptyStateFavoritos;
    private ResultSeriesPopulares resultSeriesPopulares;
    private ResultPelicula resultPelicula;
    private notificadorSerie elNotificador;
    private notificadorPelicula elNotificadorPelicula;
    public static List<Pelicula> peliculaFavList = new ArrayList<>();
    public static List<Serie> serieFavList = new ArrayList<>();
    private FirebaseAuth mAuth;
    List<Serie> otraSerieList = new ArrayList<>();
    List<Pelicula> otraPeliList = new ArrayList<>();
    private SerieController serieController = new SerieController();
    private PeliculaController peliculaController = new PeliculaController();
    private SerieFragment serieFragment = new SerieFragment();



    public FavoritosFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.elNotificador = (notificadorSerie) context;
        this.elNotificadorPelicula = (notificadorPelicula) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favoritos, container, false);
        ButterKnife.bind(FavoritosFragment.this, view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerViewFavoritosFragment.setLayoutManager(layoutManager);


        getFavLists();




        botonPeliculasFavoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRecyclerPelisFav();
            }
        });

        botonSeriesFvoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRecyclerSeriesFav();
            }
        });


        return view;
    }

    private void getFavLists() {
        peliculaController.getPeliculaFav(new ResultListener<List<Pelicula>>() {
            @Override
            public void onFinish(List<Pelicula> result) {
                peliculaFavList=result;
                setRecyclerPelisFav();
            }
        });
        serieController.getSeriesFav(new ResultListener<List<Serie>>() {
            @Override
            public void onFinish(List<Serie> result) {
                serieFavList=result;
            }
        });
    }

    private void setRecyclerSeriesFav() {
        if (!serieFavList.isEmpty()) {
            emptyStateFavoritos.setVisibility(View.GONE);
        SerieAdapter serieAdapter = new SerieAdapter(serieFavList, FavoritosFragment.this);
        recyclerViewFavoritosFragment.setAdapter(serieAdapter);
        recyclerViewFavoritosFragment.setItemViewCacheSize(20);
        recyclerViewFavoritosFragment.setHasFixedSize(true);}
        else{emptyStateFavoritos.setVisibility(View.VISIBLE);}
    }

    private void setRecyclerPelisFav() {
        if (!peliculaFavList.isEmpty()) {
            emptyStateFavoritos.setVisibility(View.GONE);
        PeliculaAdapter peliculaAdapter = new PeliculaAdapter(peliculaFavList, FavoritosFragment.this);
        recyclerViewFavoritosFragment.setAdapter(peliculaAdapter);
        recyclerViewFavoritosFragment.setItemViewCacheSize(20);
        recyclerViewFavoritosFragment.setHasFixedSize(true);}
        else{emptyStateFavoritos.setVisibility(View.VISIBLE);}
    }


    public void setFavButton(final Serie serie, final ImageView imageViewDislike, final LottieAnimationView lottieLike) {


        imageViewDislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final FirebaseAuth mAuth;
                mAuth = FirebaseAuth.getInstance();
                FirebaseUser currentUser = mAuth.getCurrentUser();
                if (currentUser != null) {

                        writeDocument(view, serie, imageViewDislike, lottieLike);

                } else {
                    Toast.makeText(view.getContext(), "Debe estar Logueado", Toast.LENGTH_SHORT).show();
                }
            }
        });

        lottieLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                removeSerieFav(view, serie, imageViewDislike, lottieLike);

            }
        });
    }



    private void changeFavVisibility(ImageView imageViewDislike, LottieAnimationView lottieLike) {

        if (imageViewDislike.getVisibility() == View.GONE) {
            imageViewDislike.setVisibility(View.VISIBLE);
            lottieLike.setVisibility(View.GONE);
        } else {
            imageViewDislike.setVisibility(View.GONE);
            lottieLike.setVisibility(View.VISIBLE);
        }
    }

    public void setFavVisibility(final Serie serie, final ImageView imageViewDislike, final LottieAnimationView lottieLike) {
        final FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            serieController.getSeriesFav(new ResultListener<List<Serie>>() {
                @Override
                public void onFinish(List<Serie> result) {
                    serieFavList = result;
                    for (Serie favSerie : serieFavList) {
                        if (favSerie.getId().equals(serie.getId())) {
                            imageViewDislike.setVisibility(View.GONE);
                            lottieLike.setVisibility(View.VISIBLE);
                        }

                    }
                }
            });

        }
    }





    public void setFavButton(final Pelicula pelicula, final ImageView imageViewDislike, final LottieAnimationView lottieLike) {

        imageViewDislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final FirebaseAuth mAuth;
                mAuth = FirebaseAuth.getInstance();
                FirebaseUser currentUser = mAuth.getCurrentUser();
                if (currentUser != null) {

                    writeDocument(view, pelicula, imageViewDislike, lottieLike);

                } else {
                    Toast.makeText(view.getContext(), "Debe estar Logueado", Toast.LENGTH_SHORT).show();
                }
            }
        });

        lottieLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                removePeliculaFav(view, pelicula, imageViewDislike, lottieLike);

            }
        });

        /*imageViewDislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final FirebaseAuth mAuth;
                mAuth = FirebaseAuth.getInstance();
                FirebaseUser currentUser = mAuth.getCurrentUser();
                if (currentUser != null) {
                    if (!buscarFavRepetido(pelicula, view)) {

                        writeDocument(view, pelicula, imageViewDislike, lottieLike);

                    }
                } else {
                    Toast.makeText(view.getContext(), "Debe estar Logueado", Toast.LENGTH_SHORT).show();
                }
            }
        });

        lottieLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final FirebaseAuth mAuth;
                mAuth = FirebaseAuth.getInstance();
                FirebaseUser currentUser = mAuth.getCurrentUser();
                if (currentUser != null) {

                    otraPeliList = peliculaFavList;
                    for (Pelicula unaPeli: peliculaFavList ) {
                        if (unaPeli.getId().equals(pelicula.getId())){
                            otraPeliList.remove(unaPeli);
                            return;
                        }

                    }
                    peliculaFavList = otraPeliList;
                    lottieLike.playAnimation();
                    changeFavVisibility(imageViewDislike, lottieLike);
                    Snackbar.make(view, "Eliminado de favoritos!", Snackbar.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(view.getContext(), "Debe estar Logueado", Toast.LENGTH_SHORT).show();
                }

            }
        });*/
    }


    public void setFavVisibility(final Pelicula pelicula, final ImageView imageViewDislike, final LottieAnimationView lottieLike) {
        final FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            peliculaController.getPeliculaFav(new ResultListener<List<Pelicula>>() {
                @Override
                public void onFinish(List<Pelicula> result) {
                    peliculaFavList = result;
                    for (Pelicula favPelicula : peliculaFavList) {
                        if (favPelicula.getId().equals(pelicula.getId())) {
                            imageViewDislike.setVisibility(View.GONE);
                            lottieLike.setVisibility(View.VISIBLE);
                        }

                    }
                }
            });

        }
    }



    private void writeDocument(@NonNull final View itemView, final Pelicula unaPelicula, final ImageView imageViewDislike, final LottieAnimationView lottieLike) {


        peliculaController.addPeliculaFav(new ResultListener<Boolean>() {
            @Override
            public void onFinish(Boolean result) {
                if(result){
                    lottieLike.playAnimation();
                    changeFavVisibility(imageViewDislike, lottieLike);
                    Snackbar.make(itemView, "Agregado a favoritos!", Snackbar.LENGTH_SHORT).show();
                }
            }
        }, unaPelicula );


        /*final FirebaseFirestore db = FirebaseFirestore.getInstance();
        final CollectionReference peliculas = db.collection("peliculas");

        Query query = peliculas.whereEqualTo("id", true);
        query.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (DocumentSnapshot document : queryDocumentSnapshots.getDocuments()) {
                    if (document.getId().equals(unaPelicula.getId())) {
                        Toast.makeText(itemView.getContext(), "ya fue agregado a favoritos", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                db.collection("peliculas")
                        .add(unaPelicula)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                peliculaFavList.add(unaPelicula);
                                lottieLike.playAnimation();
                                changeFavVisibility(imageViewDislike, lottieLike);
                                Snackbar.make(itemView, "Agregado a favoritos!", Snackbar.LENGTH_SHORT).show();
                            }
                        });
            }
        });*/


    }

    private void writeDocument(@NonNull final View itemView, final Serie unaSerie, final ImageView imageViewDislike, final LottieAnimationView lottieLike) {

        serieController.addSerieFav(new ResultListener<Boolean>() {
            @Override
            public void onFinish(Boolean result) {
               if(result){
                lottieLike.playAnimation();
                changeFavVisibility(imageViewDislike, lottieLike);
                Snackbar.make(itemView, "Agregado a favoritos!", Snackbar.LENGTH_SHORT).show();
               }
            }
        }, unaSerie );


    }


    private void removePeliculaFav(@NonNull final View itemView, final Pelicula pelicula, final ImageView imageViewDislike, final LottieAnimationView lottieLike){
        peliculaController.removePeliculaFav(new ResultListener<Boolean>() {
            @Override
            public void onFinish(Boolean result) {
                if(result){
                    changeFavVisibility(imageViewDislike, lottieLike);
                    Snackbar.make(itemView, "Eliminado de favoritos!", Snackbar.LENGTH_SHORT).show();

                }
            }
        }, pelicula);
    }


    private void removeSerieFav(@NonNull final View itemView, final Serie unaSerie, final ImageView imageViewDislike, final LottieAnimationView lottieLike){
        serieController.removeSerieFav(new ResultListener<Boolean>() {
            @Override
            public void onFinish(Boolean result) {
                if(result){
                    changeFavVisibility(imageViewDislike, lottieLike);
                    Snackbar.make(itemView, "Eliminado de favoritos!", Snackbar.LENGTH_SHORT).show();

                }
            }
        }, unaSerie);
    }


    @Override
    public void informarSeleccionSerie(Integer posicion) {
        ResultSeriesPopulares resultSeriesPopulares = new ResultSeriesPopulares();
        resultSeriesPopulares.setSeriesPopularesList(serieFavList);
        elNotificador.enviarNotificacionSeries(resultSeriesPopulares, posicion);

    }


    @Override
    public void informarSeleccionPelicula(Integer posicion) {
        ResultPelicula resultPelicula = new ResultPelicula();
        resultPelicula.setPeliculasResult(peliculaFavList);
        elNotificadorPelicula.enviarNotificacionPeliculas(resultPelicula, posicion);

    }

    @Override
    public void onResume() {
        super.onResume();
        getFavLists();
    }

    public interface notificadorSerie {
        public void enviarNotificacionSeries(ResultSeriesPopulares resultSeriesPopulares, Integer posicion);

    }

    public interface notificadorPelicula {
        public void enviarNotificacionPeliculas(ResultPelicula resultPelicula, Integer posicion);
    }
}
