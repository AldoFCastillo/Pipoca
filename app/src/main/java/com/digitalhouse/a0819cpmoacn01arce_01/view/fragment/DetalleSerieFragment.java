package com.digitalhouse.a0819cpmoacn01arce_01.view.fragment;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.digitalhouse.a0819cpmoacn01arce_01.R;
import com.digitalhouse.a0819cpmoacn01arce_01.controller.SerieController;
import com.digitalhouse.a0819cpmoacn01arce_01.model.Videos;
import com.digitalhouse.a0819cpmoacn01arce_01.model.peliculas.Pelicula;
import com.digitalhouse.a0819cpmoacn01arce_01.model.peliculas.Result;
import com.digitalhouse.a0819cpmoacn01arce_01.model.series.Cast;
import com.digitalhouse.a0819cpmoacn01arce_01.model.series.CreatedBy;
import com.digitalhouse.a0819cpmoacn01arce_01.model.series.Credits;
import com.digitalhouse.a0819cpmoacn01arce_01.model.series.Genres;
import com.digitalhouse.a0819cpmoacn01arce_01.model.series.Network;
import com.digitalhouse.a0819cpmoacn01arce_01.model.series.ResultSeriesPopulares;
import com.digitalhouse.a0819cpmoacn01arce_01.model.series.Serie;
import com.digitalhouse.a0819cpmoacn01arce_01.model.series.SerieDetalles;
import com.digitalhouse.a0819cpmoacn01arce_01.utils.ResultListener;
import com.digitalhouse.a0819cpmoacn01arce_01.view.adapter.SerieAdapter;
import com.digitalhouse.a0819cpmoacn01arce_01.view.adapter.SerieCastAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetalleSerieFragment extends Fragment implements SerieCastAdapter.serieCastAdapterListener {

    public static final String KEY_ID = "id";
    String generos = "";
    String creadores = "";
    String plataformas = "";
    @BindView(R.id.imageViewDetalleSerieTrailer)
    ImageView imageViewDetalleSerieTrailer;
    @BindView(R.id.imageViewDetalleSeriePoster)
    ImageView imageViewDetalleSeriePoster;
    @BindView(R.id.textViewDetalleSerieNombre)
    TextView textViewDetalleSerieNombre;
    @BindView(R.id.textViewDetalleSerieAnio)
    TextView textViewDetalleSerieAnio;
    @BindView(R.id.textViewDetalleSerieDuracion)
    TextView textViewDetalleSerieDuracion;
    @BindView(R.id.textViewDetalleSerieClasificacion)
    TextView textViewDetalleSerieClasificacion;
    @BindView(R.id.textViewDetalleSerieSinopsis)
    TextView textViewDetalleSerieSinopsis;
    @BindView(R.id.textViewDetalleSeriePuntaje)
    TextView textViewDetalleSeriePuntaje;
    @BindView(R.id.botonDetalleSeriesReparto)
    Button botonDetallesReparto;
    /*@BindView(R.id.spinnerSeriesTemporadas)
    Spinner spinner;*/
    @BindView(R.id.recyclerSerieReparto)
    RecyclerView recyclerSerieReparto;
    @BindView(R.id.botonDetalleSeriesSinopsis)
    Button botonDetalleSeriesSinopsis;
    @BindView(R.id.botonDetalleSeriesDetalles)
    Button botonDetalleSeriesDetalles;
    @BindView(R.id.linearDetalleSerieDetalles)
    LinearLayout linearDetalleSerieDetalles;
    @BindView(R.id.textViewDetalleSerieGeneros)
    TextView textViewDetalleSerieGeneros;
    @BindView(R.id.textViewDetalleSerieFechaEstreno)
    TextView textViewDetalleSerieFechaEstreno;
    @BindView(R.id.textViewDetalleSeriePaisOrigen)
    TextView textViewDetalleSeriePaisOrigen;
    @BindView(R.id.textViewDetalleSerieIdioma)
    TextView textViewDetalleSerieIdioma;
    @BindView(R.id.textViewDetalleSerieCreadoPor)
    TextView textViewDetalleSerieCreadoPor;
    @BindView(R.id.textViewDetalleSeriePagina)
    TextView textViewDetalleSeriePagina;
    @BindView(R.id.textViewDetalleSeriePlataforma)
    TextView textViewDetalleSeriePlataforma;
    @BindView(R.id.imagenDeCargaSerieDetalles)
    ImageView imagenDeCargaSerieDetalles;
    @BindView(R.id.youtube_player_view_serie)
    YouTubePlayerView youTubePlayerView;
    /*@BindView(R.id.floatingButtonFavoritosDetalleSerie)
    FloatingActionButton floatingButtonFavoritosDetalleSerie;*/
    @BindView(R.id.imageViewDislikeDetallesPelicula)
    ImageView imageViewDislike;
    @BindView(R.id.lottieLikeDetallesPelicula)
    LottieAnimationView lottieLike;
    @BindView(R.id.frameLayoutDetalleSerieFrag)
    FrameLayout frameLayoutDetalleSerieFrag;
    private notificadorCast notificador;
    private FavoritosFragment favoritosFragment = new FavoritosFragment();


    public static final String KEY_SERIE = "serie";


    public DetalleSerieFragment() {
        // Required empty public constructor

    }

    public static DetalleSerieFragment getInstance(Serie serie) {
        DetalleSerieFragment detalleSerieFragment = new DetalleSerieFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_SERIE, serie);
        detalleSerieFragment.setArguments(bundle);
        return detalleSerieFragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {

        super.onAttach(context);
        this.notificador = (notificadorCast) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_detalle_serie, container, false);
        final RecyclerView recyclerView = view.findViewById(R.id.recyclerSerieReparto);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);


        ButterKnife.bind(this, view);

        Bundle bundle = getArguments();

        final Serie serie = (Serie) bundle.getSerializable(KEY_SERIE);
        final String id = serie.getId();


        final SerieController serieController = new SerieController();
        serieController.getDetallesSerie(new ResultListener<SerieDetalles>() {
            @Override
            public void onFinish(final SerieDetalles result) {
                /* serieSpinner(result.getSeasons());*/
                botonReparto();
                botonDetalles();
                botonSinopsis();
                Glide.with(DetalleSerieFragment.this).load(result.getBackdropPath()).into(imageViewDetalleSerieTrailer);
                Glide.with(DetalleSerieFragment.this).load(result.getPosterPath()).into(imageViewDetalleSeriePoster);
                textViewDetalleSerieNombre.setText(result.getName());
                String fechaInicio = result.getFirstAirDate();
                String anioInicio = fechaInicio.substring(0, 4);
                textViewDetalleSerieAnio.setText(anioInicio);
                String lenguaje = "(" + result.getOriginalLanguage() + ")";
                textViewDetalleSerieClasificacion.setText(lenguaje);
                textViewDetalleSerieDuracion.setText(String.valueOf(result.getEpisodeRunTime()));
                textViewDetalleSeriePuntaje.setText(result.getVoteAverage());
                textViewDetalleSerieSinopsis.setText(result.getOverview());
                setGeneros(result);
                textViewDetalleSerieFechaEstreno.setText(result.getFirstAirDate());
                textViewDetalleSeriePaisOrigen.setText(result.getOriginCountry().get(0));
                setIdiomaDetalles(lenguaje);
                setCreadores(result);
                textViewDetalleSeriePagina.setText(result.getHomepage());
                setPlataformas(result);
                armadoDeRecyclerCast(recyclerView, id);
                /*floatingButtonFavoritosDetalleSerie.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final FirebaseAuth mAuth;
                        mAuth = FirebaseAuth.getInstance();
                        FirebaseUser currentUser = mAuth.getCurrentUser();
                        if (currentUser != null) {
                            if (!buscarFavRepetido(serie)) {
                                FavoritosFragment.serieFavList.add(serie);
                                Snackbar.make(frameLayoutDetalleSerieFrag, "Agregado a favoritos!", Snackbar.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(v.getContext(), "Debe estar Logueado", Toast.LENGTH_SHORT).show();
                        }
                    }
                });*/

                favoritosFragment.setFavButton(serie, imageViewDislike, lottieLike);
                favoritosFragment.setFavVisibility(serie, imageViewDislike, lottieLike);
                imagenDeCargaSerieDetalles.setVisibility(View.GONE);

                serieController.getVideosSeries(new ResultListener<Videos>() {
                    @Override
                    public void onFinish(final Videos result) {
                        if (result.getResults().size() > 0) {
                            youTubePlayerView.setVisibility(View.VISIBLE);
                            YouTubePlayerView youTubePlayerView = view.findViewById(R.id.youtube_player_view_serie);
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

            }
        }, id);


        return view;

    }



    private void armadoDeRecyclerCast(final RecyclerView recyclerView, String id) {
        SerieController serieControllerCredits = new SerieController();
        serieControllerCredits.getCreditsSerie(new ResultListener<Credits>() {
            @Override
            public void onFinish(Credits resultCredits) {
                List<Cast> castList = resultCredits.getCast();
                SerieCastAdapter serieCastAdapter = new SerieCastAdapter(castList, DetalleSerieFragment.this);
                recyclerView.setAdapter(serieCastAdapter);
                recyclerView.setHasFixedSize(true);
                recyclerView.setItemViewCacheSize(20);

            }
        }, id);
    }

    private void setIdiomaDetalles(String lenguaje) {
        switch (lenguaje) {
            case "(en)":
                lenguaje = "Ingles";
                break;
            case "(es)":
                lenguaje = "Espñol";
                break;
            case "(ja)":
                lenguaje = "Japones";
                break;
        }
        textViewDetalleSerieIdioma.setText(lenguaje);
    }

    private void setPlataformas(SerieDetalles result) {
        List<Network> networkList = result.getNetworks();
        for (Network network : networkList) {
            plataformas += network.getName() + " ";
        }
        textViewDetalleSeriePlataforma.setText(plataformas);
    }

    private void setCreadores(SerieDetalles result) {
        List<CreatedBy> creadoresList = result.getCreatedBy();
        for (CreatedBy createdBy : creadoresList) {
            creadores += createdBy.getName() + "/ ";
        }
        textViewDetalleSerieCreadoPor.setText(creadores);
    }

    private void setGeneros(SerieDetalles result) {
        List<Genres> listaGenres = result.getGenres();
        List<String> listaGeneros = new ArrayList<>();
        for (Genres genres : listaGenres) {
            listaGeneros.add(genres.getName());
        }
        for (String genero : listaGeneros) {
            generos += genero + " | ";
        }
        textViewDetalleSerieGeneros.setText(generos);
    }

    /*public void serieSpinner(List<Season> temporadasResult) {
        List<String> temporadasList = new ArrayList<>();
        temporadasList.add("+TEMPORADAS");
        for (int i=1; i<temporadasResult.size(); i++){
            temporadasList.add("Temporada "+ i);
        }

        spinner.setAdapter(new ArrayAdapter<String>(getContext(), R.layout.spinner_dropdown, temporadasList));

        }*/

//TODO click en Spinner
            /*spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<> parentView, View selectedItemView, int position, long id) {
                    ((TextView)parentView.getChildAt(0)).setTextColor(Color.RED);
                }
            });*/


    public void botonReparto() {
        botonDetallesReparto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                visibility(recyclerSerieReparto);
            }

        });
    }

    public void botonDetalles() {
        botonDetalleSeriesDetalles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                visibility(linearDetalleSerieDetalles);
            }

        });
    }

    public void botonSinopsis() {
        botonDetalleSeriesSinopsis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                visibility(textViewDetalleSerieSinopsis);
            }

        });
    }

    public void visibility(View view) {
        if (view.getVisibility() == View.GONE)
            view.setVisibility(View.VISIBLE);
        else view.setVisibility(View.GONE);
    }

    @Override
    public void informarSeleccion(Cast cast, String id) {
        notificador.enviarNotificacion(cast, id);
    }

    public interface notificadorCast {
        public void enviarNotificacion(Cast cast, String id);
    }
}
