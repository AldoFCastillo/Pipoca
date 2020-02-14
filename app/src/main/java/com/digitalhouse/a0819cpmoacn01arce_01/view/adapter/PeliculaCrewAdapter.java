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
import com.digitalhouse.a0819cpmoacn01arce_01.model.peliculas.PeliculaCrew;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PeliculaCrewAdapter extends RecyclerView.Adapter {

    private List<PeliculaCrew> peliculaCrewList;

    public PeliculaCrewAdapter(List<PeliculaCrew> peliculaCrewList) {
        this.peliculaCrewList = peliculaCrewList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.celda_crew, parent, false);
        PeliculaCrewAdapterViewHolder peliculaCrewAdapterViewHolder = new PeliculaCrewAdapterViewHolder(view);
        return peliculaCrewAdapterViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    PeliculaCrew peliculaCrewDeLaLista = this.peliculaCrewList.get(position);
    PeliculaCrewAdapterViewHolder peliculaCrewAdapterViewHolder = (PeliculaCrewAdapterViewHolder) holder;
    peliculaCrewAdapterViewHolder.bindPeliculaCrew(peliculaCrewDeLaLista);
    }

    @Override
    public int getItemCount() {
        return this.peliculaCrewList.size();
    }

    class PeliculaCrewAdapterViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageViewCeldaCrewImagen)
        ImageView imageViewCeldaCrewImagen;
        @BindView(R.id.textViewCeldaCrewPosicion)
        TextView textViewCeldaCrewPosicion;
        @BindView(R.id.textViewCeldaCrewNombre)
        TextView textViewCeldaCrewNombre;
        private PeliculaCrew peliculaCrew;

        public PeliculaCrewAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindPeliculaCrew(PeliculaCrew peliculaCrewDeLaCelda) {
            this.peliculaCrew = peliculaCrewDeLaCelda;
            Glide.with(itemView).load(peliculaCrewDeLaCelda.getProfilePath()).into(imageViewCeldaCrewImagen);
            this.textViewCeldaCrewPosicion.setText(this.peliculaCrew.getJob());
            this.textViewCeldaCrewNombre.setText(this.peliculaCrew.getName());

        }
    }
}
