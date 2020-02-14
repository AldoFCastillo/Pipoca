package com.digitalhouse.a0819cpmoacn01arce_01.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.digitalhouse.a0819cpmoacn01arce_01.R;
import com.digitalhouse.a0819cpmoacn01arce_01.model.series.Serie;
import com.digitalhouse.a0819cpmoacn01arce_01.view.fragment.FavoritosFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SerieAdapter extends RecyclerView.Adapter {
    private List<Serie> serieList;
    private serieAdapterListener listener;
    private FavoritosFragment favoritosFragment = new FavoritosFragment();

    public SerieAdapter(List<Serie> listaDeSeries, SerieAdapter.serieAdapterListener listener) {
        this.serieList = listaDeSeries;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflador = LayoutInflater.from(parent.getContext());
        View view = inflador.inflate(R.layout.celda_serie, parent, false);
        SerieViewHolder serieViewHolder = new SerieViewHolder(view);
        return serieViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Serie serieDeLaLista = this.serieList.get(position);
        SerieAdapter.SerieViewHolder serieViewHolder = (SerieViewHolder) holder;
        serieViewHolder.bindSerie(serieDeLaLista);

    }

    @Override
    public int getItemCount() {
        return serieList.size();
    }

    public interface serieAdapterListener {
        public void     informarSeleccionSerie(Integer posicion);
    }

    public class SerieViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageViewTrailerCeldaSeries)
        ImageView imageViewTrailerCeldaSeries;
        @BindView(R.id.textViewNombreCeldaSeries)
        TextView textViewNombreCeldaSeries;
        @BindView(R.id.textViewGeneroCeldaSeries)
        TextView textViewGeneroCeldaSeries;
        @BindView(R.id.textViewPuntajeCeldaSeries)
        TextView textViewPuntajeCeldaSeries;
        /*@BindView(R.id.floatingButtonSerie)
        FloatingActionButton floatingButtonSerie;*/
        @BindView(R.id.frameLayoutCeldaSerie)
        FrameLayout frameLayoutCeldaSerie;
        @BindView(R.id.lottieLike)
        LottieAnimationView lottieLike;
        @BindView(R.id.imageViewDislike)
        ImageView imageViewDislike;
        private Serie serie;


        public SerieViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int adapterPosition = getAdapterPosition();
                    Serie serie = serieList.get(adapterPosition);
                    listener.informarSeleccionSerie(adapterPosition);
                }
            });




        }

       /* private void setFavButton() {

            *//*floatingButtonSerie.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final FirebaseAuth mAuth;
                    mAuth = FirebaseAuth.getInstance();
                    FirebaseUser currentUser = mAuth.getCurrentUser();
                    if (currentUser != null) {
                        if (!buscarFavRepetido(serie)) {
                            FavoritosFragment.serieFavList.add(serie);
                            Snackbar.make(frameLayoutCeldaSerie, "Agregado a favoritos!", Snackbar.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(v.getContext(), "Debe estar Logueado", Toast.LENGTH_SHORT).show();
                    }
                }
            });*//*


            imageViewDislike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final FirebaseAuth mAuth;
                    mAuth = FirebaseAuth.getInstance();
                    FirebaseUser currentUser = mAuth.getCurrentUser();
                    if (currentUser == null) {
                        if (!buscarFavRepetido(serie)) {
                            FavoritosFragment.serieFavList.add(serie);
                            lottieLike.playAnimation();
                            changeFavVisibility();
                            Snackbar.make(frameLayoutCeldaSerie, "Agregado a favoritos!", Snackbar.LENGTH_SHORT).show();
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
                    if (currentUser == null) {
                        FavoritosFragment.serieFavList.remove(serie);
                        lottieLike.playAnimation();
                        changeFavVisibility();
                        Snackbar.make(frameLayoutCeldaSerie, "Eliminado de favoritos!", Snackbar.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(view.getContext(), "Debe estar Logueado", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }

        private void changeFavVisibility() {

            if (imageViewDislike.getVisibility() == View.GONE) {
                imageViewDislike.setVisibility(View.VISIBLE);
                lottieLike.setVisibility(View.GONE);
            } else {
                imageViewDislike.setVisibility(View.GONE);
                lottieLike.setVisibility(View.VISIBLE);
            }
        }

        private void setFavVisibility(Serie serie){
            for (Serie favSerie : FavoritosFragment.serieFavList)
                 { if (favSerie.getId().equals(serie.getId())){
                     imageViewDislike.setVisibility(View.GONE);
                     lottieLike.setVisibility(View.VISIBLE);
                 }

            }
        }

        private Boolean buscarFavRepetido(Serie serie) {
            Boolean repe = false;
            for (Serie unaSerie : FavoritosFragment.serieFavList) {
                if (serie.getId().equals(unaSerie.getId())) {
                    Toast.makeText(itemView.getContext(), "Ya se encuentra en Favoritos", Toast.LENGTH_SHORT).show();
                    repe = true;
                }
            }
            return repe;
        }*/

        public void bindSerie(Serie serieDeLaCelda) {
            this.serie = serieDeLaCelda;
            this.textViewNombreCeldaSeries.setText(this.serie.getName());
            String fechaInicio = serie.getFirstAirDate();
            String anioInicio = "(" + fechaInicio.substring(0, 4) + ")";
            this.textViewGeneroCeldaSeries.setText(anioInicio);
            this.textViewPuntajeCeldaSeries.setText(this.serie.getVoteAverage());
            Glide.with(itemView).load(serieDeLaCelda.getBackdropPath()).into(imageViewTrailerCeldaSeries);
            setFavs();

        }

        public void setFavs(){
            favoritosFragment.setFavButton(serie, imageViewDislike, lottieLike);
            favoritosFragment.setFavVisibility(serie, imageViewDislike, lottieLike);
        }
    }
}
