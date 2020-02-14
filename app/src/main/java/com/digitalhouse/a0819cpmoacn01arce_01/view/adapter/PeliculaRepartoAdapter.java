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
import com.digitalhouse.a0819cpmoacn01arce_01.model.People;
import com.digitalhouse.a0819cpmoacn01arce_01.model.peliculas.Pelicula;
import com.digitalhouse.a0819cpmoacn01arce_01.model.peliculas.PeliculaReparto;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PeliculaRepartoAdapter extends RecyclerView.Adapter {

    private List<PeliculaReparto> listaDePeliculaReparto;
    private PeopleAdapterListener listener;

    public PeliculaRepartoAdapter(List<PeliculaReparto> listaDePeliculaReparto, PeopleAdapterListener listener) {
        this.listaDePeliculaReparto = listaDePeliculaReparto;
        this.listener = listener;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.celda_reparto, parent, false);
        PeliculaRepartoViewHolder peliculaRepartoViewHolder = new PeliculaRepartoViewHolder(view);
        return peliculaRepartoViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        PeliculaReparto peliculaRepartoDeLaLista = this.listaDePeliculaReparto.get(position);
        PeliculaRepartoViewHolder peliculaRepartoViewHolder = (PeliculaRepartoViewHolder) holder;
        peliculaRepartoViewHolder.bindPeliculaReparto(peliculaRepartoDeLaLista);
    }

    @Override
    public int getItemCount() {
        return this.listaDePeliculaReparto.size();
    }


    public interface PeopleAdapterListener {
        void informarSeleccion(PeliculaReparto peliculaReparto, String id);
    }

    class PeliculaRepartoViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageViewCeldaRepartoImagenReparto)
        ImageView imageViewCeldaRepartoImagen;
        @BindView(R.id.textViewCeldaRepartoNombreActor)
        TextView textViewCeldaRepartoNombreActor;
        @BindView(R.id.textViewCeldaRepartoNombrePersonaje)
        TextView textViewCeldaRepartoNombrePersonaje;
        private PeliculaReparto peliculaReparto;


        public PeliculaRepartoViewHolder(@NonNull View itemView) {

            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int adapterPosition = getAdapterPosition();
                    PeliculaReparto peliculaReparto = listaDePeliculaReparto.get(adapterPosition);
                    listener.informarSeleccion(peliculaReparto, peliculaReparto.getId());

                }
            });
        }

        public void bindPeliculaReparto(PeliculaReparto peliculaRepartoDeLaCelda) {
            this.peliculaReparto = peliculaRepartoDeLaCelda;
            Glide.with(itemView).load(peliculaRepartoDeLaCelda.getProfilePath()).into(imageViewCeldaRepartoImagen);
            this.textViewCeldaRepartoNombreActor.setText(this.peliculaReparto.getName());
            this.textViewCeldaRepartoNombrePersonaje.setText(this.peliculaReparto.getCharacter());
        }
    }

}
