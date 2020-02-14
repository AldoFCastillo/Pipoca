package com.digitalhouse.a0819cpmoacn01arce_01.view.adapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.digitalhouse.a0819cpmoacn01arce_01.R;
import com.digitalhouse.a0819cpmoacn01arce_01.model.series.Cast;
import com.digitalhouse.a0819cpmoacn01arce_01.view.fragment.DetallePeopleFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SerieCastAdapter extends RecyclerView.Adapter {

    private List<Cast> castList;
    private serieCastAdapterListener listener;

    public SerieCastAdapter(List<Cast> castList, serieCastAdapterListener listener) {
        this.castList = castList;
        this.listener = listener;
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflador = LayoutInflater.from(parent.getContext());
        View view = inflador.inflate(R.layout.celda_serie_cast, parent, false);
        SerieCastViewHolder serieCastViewHolder = new SerieCastViewHolder(view);
        return serieCastViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Cast castDeLaLista = this.castList.get(position);
        SerieCastAdapter.SerieCastViewHolder serieCastViewHolder = (SerieCastViewHolder) holder;
        serieCastViewHolder.bindCreditsSerie(castDeLaLista);


    }

    @Override
    public int getItemCount() {
        return castList.size();
    }

    public interface serieCastAdapterListener {
        public void informarSeleccion(Cast cast, String id);
    }

    class SerieCastViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imageViewCeldaSerieCast)
        ImageView imageViewCeldaSerieCast;
        @BindView(R.id.textViewCeldaSerieCastNombre)
        TextView textViewCeldaSerieCastNombre;





        public SerieCastViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int adapterPosition = getAdapterPosition();
                    Cast cast = castList.get(adapterPosition);
                    listener.informarSeleccion(cast, cast.getId());

                }
            });

        }

        public void bindCreditsSerie(Cast castDeLaCelda) {
            textViewCeldaSerieCastNombre.setText(castDeLaCelda.getName());
            Glide.with(itemView).load(castDeLaCelda.getProfilePath()).into(imageViewCeldaSerieCast);
        }
    }
}
