package com.digitalhouse.a0819cpmoacn01arce_01.view.fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.digitalhouse.a0819cpmoacn01arce_01.R;
import com.digitalhouse.a0819cpmoacn01arce_01.model.Videos;
import com.digitalhouse.a0819cpmoacn01arce_01.model.peliculas.Result;
import com.digitalhouse.a0819cpmoacn01arce_01.utils.ResultListener;
import com.digitalhouse.a0819cpmoacn01arce_01.controller.PeliculaController;
import com.digitalhouse.a0819cpmoacn01arce_01.model.peliculas.PeliculaCrew;
import com.digitalhouse.a0819cpmoacn01arce_01.model.peliculas.Pelicula;
import com.digitalhouse.a0819cpmoacn01arce_01.model.peliculas.PeliculaReparto;
import com.digitalhouse.a0819cpmoacn01arce_01.view.adapter.PeliculaCrewAdapter;
import com.digitalhouse.a0819cpmoacn01arce_01.view.adapter.PeliculaRepartoAdapter;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetallePeliculaFragment extends Fragment implements PeliculaRepartoAdapter.PeopleAdapterListener {

    public static final String KEY_ID = "id";
    public static final String VOTES = " Votos";

    private FavoritosFragment favoritosFragment = new FavoritosFragment();

    @BindView(R.id.youtube_player_view)
    YouTubePlayerView youTubePlayerView;
    @BindView(R.id.image_poster)
    ImageView movie_image;
    @BindView(R.id.image_movie_backdrop)
    ImageView imageView_poster;
    @BindView(R.id.rating_score)
    RatingBar ratingScore;
    @BindView(R.id.text_title)
    TextView text_title;
    @BindView(R.id.text_release_date)
    TextView release_Date;
    @BindView(R.id.text_vote)
    TextView text_vote;
    @BindView(R.id.label_vote)
    TextView label_vote;
    @BindView(R.id.text_language)
    TextView language_text;
    @BindView(R.id.text_overview)
    TextView overview_text;
    @BindView(R.id.contenedorDeReparto)
    RecyclerView contenedorDeReparto;
    @BindView(R.id.contenedorDeCrew)
    RecyclerView contenedorDeCrew;
    /*@BindView(R.id.detallePeliculasFloatingButton)
    FloatingActionButton detallePeliculasFloatingButton;*/
    @BindView(R.id.imageViewDislikeDetallesPelicula)
    ImageView imageViewDislike;
    @BindView(R.id.lottieLikeDetallesPelicula)
    LottieAnimationView lottieLike;
    @BindView(R.id.coordinator_layout)
    CoordinatorLayout coordinatorDetallePeliculas;
   /* @BindView(R.id.text_vote)
    TextView vote_text;
    @BindView(R.id.imagenDeCarga)
    ImageView imagenDeCarga;
*/
    /*@BindView(R.id.imageViewDetallePeliculaTrailer)
    ImageView imageViewDetallePeliculaTrailer;
    @BindView(R.id.imageViewDetallePeliculaPoster)
    ImageView imageViewDetallePeliculaPoster;
    @BindView(R.id.textViewDetallePeliculaNombre)
    TextView textViewDetallePeliculaNombre;
    @BindView(R.id.textViewDetallePeliculaAnio)
    TextView textViewDetallePeliculaAnio;
    *//*@BindView(R.id.textViewDetallePeliculaDuracion)
    TextView textViewDetallePeliculaDuracion;*//*
    @BindView(R.id.textViewDetallePeliculaClasificacion)
    TextView textViewDetallePeliculaClasificacion;
    @BindView(R.id.textViewDetallePeliculaPuntaje)
    TextView textViewDetallePeliculaPuntaje;
    @BindView(R.id.textViewDetallePeliculaSinopsis)
    TextView textViewDetallePeliculaSinopsis;
        @BindView(R.id.botonDetallePeliculaSinopsis)
    Button botonDetallePelicuaSinopsis;
    @BindView(R.id.botonDetallePeliculaReparto)
    Button botonDetallePeliculaReparto;
    @BindView(R.id.botonDetallePeliculaCrew)
    Button botonDetallePeliculaCrew;
    @BindView(R.id.rating_score)
    RatingBar mRatingScore;*/


    private notificadorDetallePelicula notificadorDetallePelicula;

    public static final String KEY_PELICULA = "pelicula";

    public DetallePeliculaFragment() {
        // Required empty public constructor
    }

    public static DetallePeliculaFragment getInstance(Pelicula pelicula) {
        DetallePeliculaFragment detallePeliculaFragment = new DetallePeliculaFragment();
        Bundle args = new Bundle();
        args.putSerializable(KEY_PELICULA, pelicula);
        detallePeliculaFragment.setArguments(args);
        return detallePeliculaFragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.notificadorDetallePelicula = (notificadorDetallePelicula) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_detalle_pelicula, container, false);
        ButterKnife.bind(this, view);

        Bundle bundle = getArguments();
        final Pelicula unaPelicula = (Pelicula) bundle.getSerializable(KEY_PELICULA);
        final String id = unaPelicula.getId();


        final PeliculaController peliculaController = new PeliculaController();
        peliculaController.getPeliculasDetalleCartelera(new ResultListener<Pelicula>() {
            @Override
            public void onFinish(final Pelicula result) {

                Glide.with(DetallePeliculaFragment.this).load(result.getBackdropPath()).into(imageView_poster);
                Glide.with(DetallePeliculaFragment.this).load(result.getPosterPath()).into(movie_image);
                text_title.setText(result.getOriginalTitle());
                release_Date.setText(result.getReleaseDate());
                text_vote.setText(String.valueOf(result.getVoteAverage()));
                language_text.setText(result.getOriginalLanguage());
                overview_text.setText(result.getOverview());
                ratingScore.setRating(result.getVoteAverage() / 2);
                label_vote.setText(String.valueOf(result.getVoteCount()) + VOTES);


                favoritosFragment.setFavButton(result, imageViewDislike, lottieLike);
                favoritosFragment.setFavVisibility(result, imageViewDislike, lottieLike);

                peliculaController.getVideos(new ResultListener<Videos>() {
                    @Override
                    public void onFinish(final Videos result) {
                        if (result.getResults().size() > 0) {
                            youTubePlayerView.setVisibility(View.VISIBLE);
                            YouTubePlayerView youTubePlayerView = view.findViewById(R.id.youtube_player_view);
                            getLifecycle().addObserver(youTubePlayerView);
                            youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                                @Override
                                public void onReady(YouTubePlayer youTubePlayer) {
                                    List<Result> resultList = result.getResults();
                                    String id = resultList.get(0).getKey();
                                    youTubePlayer.loadVideo(id, 0);

                                }
                            });
                        }
                    }
                }, id);


               /* detallePeliculasFloatingButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final FirebaseAuth mAuth;
                        mAuth = FirebaseAuth.getInstance();
                        FirebaseUser currentUser = mAuth.getCurrentUser();
                        if (currentUser != null) {
                            if (!buscarFavRepetido(unaPelicula)) {
                                FavoritosFragment.peliculaFavList.add(unaPelicula);
                                Snackbar.make(coordinatorDetallePeliculas, "Agregado a favoritos!", Snackbar.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(v.getContext(), "Debe estar Logueado", Toast.LENGTH_SHORT).show();
                        }
                    }
                });*/

                //imagenDeCarga.setVisibility(View.GONE);


            }
        }, id);


        final RecyclerView recyclerView = view.findViewById(R.id.contenedorDeReparto);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        peliculaController.getPeliculaReparto(new ResultListener<List<PeliculaReparto>>() {
            @Override
            public void onFinish(List<PeliculaReparto> result) {
                PeliculaRepartoAdapter peliculaRepartoAdapter = new PeliculaRepartoAdapter(result, DetallePeliculaFragment.this);
                recyclerView.setAdapter(peliculaRepartoAdapter);
                recyclerView.setHasFixedSize(true);
                recyclerView.setItemViewCacheSize(20);
            }
        }, id);

        final RecyclerView recyclerViewl = view.findViewById(R.id.contenedorDeCrew);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewl.setLayoutManager(layoutManager1);
        peliculaController.getPeliculaCrew(new ResultListener<List<PeliculaCrew>>() {
            @Override
            public void onFinish(List<PeliculaCrew> result) {
                PeliculaCrewAdapter peliculaCrewAdapter = new PeliculaCrewAdapter(result);
                recyclerViewl.setAdapter(peliculaCrewAdapter);
                recyclerViewl.setHasFixedSize(true);
                recyclerViewl.setItemViewCacheSize(20);
            }
        }, id);


        return view;
    }

    private Boolean buscarFavRepetido(Pelicula unaPelicula) {
        Boolean repe = false;
        for (Pelicula pelicula : FavoritosFragment.peliculaFavList) {
            if (pelicula.getId().equals(unaPelicula.getId())) {
                Toast.makeText(getContext(), "Ya se encuentra en Favoritos", Toast.LENGTH_SHORT).show();
                repe = true;
            }
        }
        return repe;
    }


    public void visibility(View view) {
        if (view.getVisibility() == View.GONE)
            view.setVisibility(View.VISIBLE);
        else view.setVisibility(View.GONE);
    }




    @Override
    public void informarSeleccion(PeliculaReparto peliculaReparto, String id) {
        notificadorDetallePelicula.enviarNotificacionDetallePelicula(peliculaReparto, id);
    }


    public interface notificadorDetallePelicula {
        void enviarNotificacionDetallePelicula(PeliculaReparto peliculaReparto, String id);
    }

}
