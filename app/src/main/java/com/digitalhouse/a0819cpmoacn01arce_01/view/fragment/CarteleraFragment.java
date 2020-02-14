package com.digitalhouse.a0819cpmoacn01arce_01.view.fragment;


import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.digitalhouse.a0819cpmoacn01arce_01.R;
import com.digitalhouse.a0819cpmoacn01arce_01.controller.ExtrasController;
import com.digitalhouse.a0819cpmoacn01arce_01.model.Extra;
import com.digitalhouse.a0819cpmoacn01arce_01.model.peliculas.Pelicula;
import com.digitalhouse.a0819cpmoacn01arce_01.model.peliculas.ResultPelicula;
import com.digitalhouse.a0819cpmoacn01arce_01.utils.ResultListener;
import com.digitalhouse.a0819cpmoacn01arce_01.controller.PeliculaController;
import com.digitalhouse.a0819cpmoacn01arce_01.utils.ShakeDetector;
import com.digitalhouse.a0819cpmoacn01arce_01.view.adapter.CarteleraAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class CarteleraFragment extends Fragment implements CarteleraAdapter.peliculaAdapterListener {

    List<Pelicula> peliculasCarteleraList = new ArrayList<>();
    private notificadorCartelera notificadorCartelera;
    private ResultPelicula contenedorDePelicula;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;

    public CarteleraFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.notificadorCartelera = (notificadorCartelera) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cartelera, container, false);

        final RecyclerView recyclerView = view.findViewById(R.id.recyclerViewCarteleraFragment);
        final GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 3, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);


        PeliculaController peliculaController = new PeliculaController();
        if (peliculaController.getHayMasResults()) {
            peliculaController.getPeliculasCartelera(new ResultListener<ResultPelicula>() {
                @Override
                public void onFinish(ResultPelicula result) {
                    contenedorDePelicula = result;
                    peliculasCarteleraList = result.getPeliculasResult();
                    CarteleraAdapter carteleraAdapter = new CarteleraAdapter(peliculasCarteleraList, CarteleraFragment.this);
                    recyclerView.setAdapter(carteleraAdapter);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setItemViewCacheSize(20);
                    recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                        @Override
                        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                            super.onScrolled(recyclerView, dx, dy);
                            Integer posicionActual = layoutManager.findLastVisibleItemPosition();
                            Integer ultimaCelda = layoutManager.getItemCount();
                            if (posicionActual.equals(ultimaCelda - 3)) {
                                new PeliculaController().getPeliculasCartelera(new ResultListener<ResultPelicula>() {
                                    @Override
                                    public void onFinish(ResultPelicula result) {
                                        contenedorDePelicula = result;
                                        peliculasCarteleraList.addAll(contenedorDePelicula.getPeliculasResult());
                                    }
                                });
                            }
                        }
                    });


                }
            });
        }


        return view;
    }

    @Override
    public void informarSeleccionPelicula(Integer posicion) {
        notificadorCartelera.enviarNotificacionCartelera(contenedorDePelicula, posicion);
    }

    public interface notificadorCartelera {
        public void enviarNotificacionCartelera(ResultPelicula contenedorDePelicula, Integer posicion);
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    public void pepe() {
        mSensorManager = (SensorManager) Objects.requireNonNull(getActivity()).getSystemService(Context.SENSOR_SERVICE);
        assert mSensorManager != null;
        mAccelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector();
        mShakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {

            @Override
            public void onShake(int count) {


            }
        });
    }


}

