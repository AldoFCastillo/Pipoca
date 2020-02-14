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
import com.digitalhouse.a0819cpmoacn01arce_01.model.ResultTodo;
import com.digitalhouse.a0819cpmoacn01arce_01.model.Tmdb;
import com.digitalhouse.a0819cpmoacn01arce_01.view.adapter.BusquedaAdapter;

import java.util.ArrayList;
import java.util.List;

public class BusquedaFragment extends Fragment implements BusquedaAdapter.TmdbAdapterListener {

    private ResultTodo resultTodo;
    private List<Tmdb> tmdbList = new ArrayList<>();
    public static final String KEY_LISTA = "Lista";
    private notificadorBusqueda notificadorBusqueda;

    public BusquedaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.notificadorBusqueda = (notificadorBusqueda) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_busqueda, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewBusquedaFragment);
        Bundle bundle = getArguments();
        resultTodo = (ResultTodo) bundle.getSerializable(KEY_LISTA);

        tmdbList = resultTodo.getTodoList();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        BusquedaAdapter busquedaAdapter = new BusquedaAdapter(tmdbList, BusquedaFragment.this);
        recyclerView.setAdapter(busquedaAdapter);

        return view;
    }

    @Override
    public void informarSeleccionBusqueda(Integer posicion, Tmdb tmdb) {
        notificadorBusqueda.enviarNotificacionBusqueda(tmdb, posicion);
    }

    public interface notificadorBusqueda {
        public void enviarNotificacionBusqueda(Tmdb tmdb, Integer posicion);

    }


}

