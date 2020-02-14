package com.digitalhouse.a0819cpmoacn01arce_01.view.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.digitalhouse.a0819cpmoacn01arce_01.R;
import com.digitalhouse.a0819cpmoacn01arce_01.model.Tmdb;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetalleBusquedaFragment extends Fragment {

    public static final String KEY_CONTENEDOR_TMDB = "Cont";
    public static final String KEY_POSICION = "posicion";

    @BindView(R.id.textViewBusquedaFragment)
    TextView textViewBusquedaFragment;
    @BindView(R.id.imageViewBusquedaFragment)
    ImageView imageViewBusquedaFragment;

    public DetalleBusquedaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalle_busqueda, container, false);

        ButterKnife.bind(this, view);


        Bundle bundle = getArguments();
        Tmdb tmdb = (Tmdb) bundle.getSerializable(KEY_CONTENEDOR_TMDB);

        if (!tmdb.getPosterPath().contains("jpg")) {
            Glide.with(DetalleBusquedaFragment.this).load(tmdb.getProfilePath()).into(imageViewBusquedaFragment);
        } else {
            Glide.with(DetalleBusquedaFragment.this).load(tmdb.getPosterPath()).into(imageViewBusquedaFragment);
        }

        return view;
    }

}
