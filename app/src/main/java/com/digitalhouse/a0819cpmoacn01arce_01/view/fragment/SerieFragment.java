package com.digitalhouse.a0819cpmoacn01arce_01.view.fragment;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.digitalhouse.a0819cpmoacn01arce_01.R;
import com.digitalhouse.a0819cpmoacn01arce_01.controller.SerieController;
import com.digitalhouse.a0819cpmoacn01arce_01.model.series.ResultSeriesPopulares;
import com.digitalhouse.a0819cpmoacn01arce_01.model.series.Serie;
import com.digitalhouse.a0819cpmoacn01arce_01.utils.ResultListener;
import com.digitalhouse.a0819cpmoacn01arce_01.view.adapter.SerieAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class SerieFragment extends Fragment implements SerieAdapter.serieAdapterListener {


    List<Serie> seriesPopularesList = new ArrayList<>();
    private notificador notificador;
    private ResultSeriesPopulares resultSeriesPopulares;
    @BindView(R.id.recyclerViewSeriesFragment)
    RecyclerView recyclerView;



    public SerieFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.notificador = (notificador) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_serie, container, false);
        ButterKnife.bind(this, view);


       // recyclerView = view.findViewById(R.id.recyclerViewSeriesFragment);
        setRecycler();

        return view;
    }

    public void setRecycler() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        SerieController serieController = new SerieController();
        serieController.getSeriesPopulares(new ResultListener<ResultSeriesPopulares>() {
            @Override
            public void onFinish(ResultSeriesPopulares result) {

                seriesPopularesList = result.getSeriesPopularesList();
                resultSeriesPopulares = result;
                SerieAdapter serieAdapter = new SerieAdapter(seriesPopularesList, SerieFragment.this);
                recyclerView.setAdapter(serieAdapter);
                recyclerView.setHasFixedSize(true);
                recyclerView.setItemViewCacheSize(20);

            }
        });
    }

    @Override
    public void informarSeleccionSerie(Integer posicion) {
        notificador.enviarNotificacionSerie(resultSeriesPopulares, posicion);

    }

    public interface notificador {
        public void enviarNotificacionSerie(ResultSeriesPopulares result, Integer posicion);
    }

}
