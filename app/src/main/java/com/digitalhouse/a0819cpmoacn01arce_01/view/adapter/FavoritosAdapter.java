package com.digitalhouse.a0819cpmoacn01arce_01.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.digitalhouse.a0819cpmoacn01arce_01.R;
import com.digitalhouse.a0819cpmoacn01arce_01.model.peliculas.Pelicula;
import com.digitalhouse.a0819cpmoacn01arce_01.model.series.Serie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoritosAdapter extends RecyclerView.Adapter {

    private List<Serializable> serializableList;
    favsAdapterListener listener;
    final static int CELDA_PELICULA = 0;
    final static int CELDA_SERIE = 1;
    public static  List<Pelicula> peliculaFavList= new ArrayList<>();
    public static List<Serie> serieFavList= new ArrayList<>();

    public FavoritosAdapter(List<Serializable> favoritos, FavoritosAdapter.favsAdapterListener favsAdapterListener) {
        this.serializableList = favoritos;
        this.listener = favsAdapterListener;
    }

    @NonNull
    @Override
    public int getItemViewType(int position) {
        if (this.serializableList.get(position) instanceof Serie) {
            return CELDA_SERIE;
        } else {
            return CELDA_PELICULA;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflador = LayoutInflater.from(parent.getContext());
        View view;
        if (viewType == CELDA_SERIE) {
            view = inflador.inflate(R.layout.celda_serie, parent, false);
            SerieFavViewHolder serieFavViewHolder = new SerieFavViewHolder(view);
            return serieFavViewHolder;
        } else {
            view = inflador.inflate(R.layout.celda_peliculas, parent, false);
            PeliculaFavViewHolder peliculaFavViewHolder = new PeliculaFavViewHolder(view);
            return peliculaFavViewHolder;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (this.serializableList.get(position) instanceof Serie) {
            Serie serieDeLaLista = (Serie) this.serializableList.get(position);
            SerieFavViewHolder serieFavViewHolder = (SerieFavViewHolder) holder;
            serieFavViewHolder.bindSerie(serieDeLaLista);
        } else {

            Pelicula peliculadeLaLista = (Pelicula) this.serializableList.get(position);
            PeliculaFavViewHolder peliculaFavViewHolder = (PeliculaFavViewHolder) holder;
            peliculaFavViewHolder.bindPelicula(peliculadeLaLista);
        }
    }

    @Override
    public int getItemCount() {
        return serializableList.size();
    }

    //TODO corregir Glide, no carga imagen

    public class SerieFavViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imageViewTrailerCeldaSeries)
        ImageView imageViewTrailerCeldaSeries;
        @BindView(R.id.textViewNombreCeldaSeries)
        TextView textViewNombreCeldaSeries;
        @BindView(R.id.textViewGeneroCeldaSeries)
        TextView textViewGeneroCeldaSeries;
        @BindView(R.id.textViewPuntajeCeldaSeries)
        TextView textViewPuntajeCeldaSeries;
        private Serie serie;

        public SerieFavViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

        }

        public void bindSerie(Serie serieDeLaCelda) {
            this.serie = serieDeLaCelda;
            this.textViewNombreCeldaSeries.setText(this.serie.getName());
            String fechaInicio = serie.getFirstAirDate();
            String anioInicio = "(" + fechaInicio.substring(0, 4) + ")";
            this.textViewGeneroCeldaSeries.setText(anioInicio);
            this.textViewPuntajeCeldaSeries.setText(this.serie.getVoteAverage());
            Glide.with(itemView).load(this.serie.getBackdropPath()).into(imageViewTrailerCeldaSeries);
        }
    }


    class PeliculaFavViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imageViewTrailerCelda)
        ImageView imageViewTrailerCelda;
        @BindView(R.id.textViewNombreCelda)
        TextView textViewNombreCelda;
        @BindView(R.id.textViewGeneroCelda)
        TextView textViewGeneroCelda;
        @BindView(R.id.textViewPuntajeCelda)
        TextView textViewPuntajeCelda;
        private Pelicula pelicula;

        public PeliculaFavViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        public void bindPelicula(Pelicula peliculaDeLaCelda) {
            this.pelicula = peliculaDeLaCelda;
            Glide.with(itemView).load(this.pelicula.getBackdropPath()).into(imageViewTrailerCelda);
            textViewNombreCelda.setText(this.pelicula.getTitle());
            textViewGeneroCelda.setText(this.pelicula.getHomepage());
            //todo para revisar la parte de abajo porque cambie el atributo
            //textViewPuntajeCelda.setText(this.pelicula.getVoteAverage());
        }
    }

    public interface favsAdapterListener {
        public void informarSeleccion(Integer posicion);
    }


}
