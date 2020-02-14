package com.digitalhouse.a0819cpmoacn01arce_01.view.fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.digitalhouse.a0819cpmoacn01arce_01.R;
import com.digitalhouse.a0819cpmoacn01arce_01.model.peliculas.Pelicula;
import com.digitalhouse.a0819cpmoacn01arce_01.model.peliculas.ResultPelicula;
import com.digitalhouse.a0819cpmoacn01arce_01.utils.ResultListener;
import com.digitalhouse.a0819cpmoacn01arce_01.controller.PeliculaController;
import com.digitalhouse.a0819cpmoacn01arce_01.view.adapter.CarteleraAdapter;
import com.digitalhouse.a0819cpmoacn01arce_01.view.adapter.PeliculaAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class PeliculasFragment extends Fragment implements CarteleraAdapter.peliculaAdapterListener {

    List<Pelicula> peliculasPopularesList = new ArrayList<>();
    private NotificadorPeliculas notificadorPeliculas;
    private ResultPelicula contenedorDePelicula;

    public PeliculasFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.notificadorPeliculas = (NotificadorPeliculas) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_peliculas, container, false);
        final RecyclerView recyclerView = view.findViewById(R.id.recyclerViewPeliculasFragment);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        new PeliculaController().getPeliculasPopulares(new ResultListener<ResultPelicula>() {
            @Override
            public void onFinish(ResultPelicula result) {
                contenedorDePelicula = result;
                peliculasPopularesList = result.getPeliculasResult();
                PeliculaAdapter peliculaAdapter = new PeliculaAdapter(peliculasPopularesList, PeliculasFragment.this);
                recyclerView.setAdapter(peliculaAdapter);
                recyclerView.setHasFixedSize(true);
                recyclerView.setItemViewCacheSize(20);
            }


        });


        return view;
    }


    @Override
    public void informarSeleccionPelicula(Integer posicion) {
        notificadorPeliculas.enviarNotificacionPeliculas(contenedorDePelicula, posicion);
    }

    public interface NotificadorPeliculas {
        public void enviarNotificacionPeliculas(ResultPelicula contenedorDePelicula, Integer posicion);
    }


}
