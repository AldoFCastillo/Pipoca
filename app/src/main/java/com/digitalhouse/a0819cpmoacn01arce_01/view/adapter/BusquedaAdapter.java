package com.digitalhouse.a0819cpmoacn01arce_01.view.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.digitalhouse.a0819cpmoacn01arce_01.R;
import com.digitalhouse.a0819cpmoacn01arce_01.model.Tmdb;
import com.digitalhouse.a0819cpmoacn01arce_01.model.peliculas.Pelicula;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BusquedaAdapter extends RecyclerView.Adapter {

    private List<Tmdb> tmdbList;
    private TmdbAdapterListener listener;

    public BusquedaAdapter(List<Tmdb> tmdbList, TmdbAdapterListener listener) {
        this.tmdbList = tmdbList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.celda_busqueda, parent, false);
        BusquedaViewHolder busquedaViewHolder = new BusquedaViewHolder(view);
        return busquedaViewHolder;


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Tmdb tmdbDeLaLista = this.tmdbList.get(position);
        BusquedaViewHolder busquedaViewHolder = (BusquedaViewHolder) holder;
        busquedaViewHolder.bindBusqueda(tmdbDeLaLista);
    }

    @Override
    public int getItemCount() {
        return this.tmdbList.size();
    }

    public void actualizarLista(List<Tmdb> tmdbList) {
        this.tmdbList = tmdbList;
        notifyDataSetChanged();
    }

    class BusquedaViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textViewCeldaBusquedaNombre)
        TextView busquedaId;
        @BindView(R.id.imageViewBusqueda)
        ImageView imageViewBusqueda;
        private Tmdb tmdb;

        public BusquedaViewHolder(@NonNull final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Integer adapterPosition = getAdapterPosition();
                    Tmdb tmdb = tmdbList.get(adapterPosition);
                    listener.informarSeleccionBusqueda(adapterPosition, tmdb);
                }
            });

        }

        public void bindBusqueda(Tmdb tmdbDeLaCelda) {
            this.tmdb = tmdbDeLaCelda;
            if (tmdb.getName() == null) {
                if (tmdb.getOriginalName() == null) {
                    this.busquedaId.setText(this.tmdb.getOriginalTitle());
                } else {
                    this.busquedaId.setText(this.tmdb.getOriginalName());
                }
            } else {
                this.busquedaId.setText(this.tmdb.getName());
            }

            if (!tmdb.getPosterPath().contains("jpg")) {
                Glide.with(itemView).load(this.tmdb.getProfilePath()).into(imageViewBusqueda);
            } else {
                Glide.with(itemView).load(this.tmdb.getPosterPath()).into(imageViewBusqueda);
            }
        }
    }

    public interface TmdbAdapterListener {
        void informarSeleccionBusqueda(Integer posicion, Tmdb tmdb);
    }
}

