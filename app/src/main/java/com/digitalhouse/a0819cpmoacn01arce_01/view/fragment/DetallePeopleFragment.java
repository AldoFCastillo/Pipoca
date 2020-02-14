package com.digitalhouse.a0819cpmoacn01arce_01.view.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.digitalhouse.a0819cpmoacn01arce_01.R;
import com.digitalhouse.a0819cpmoacn01arce_01.controller.PeliculaController;
import com.digitalhouse.a0819cpmoacn01arce_01.model.People;
import com.digitalhouse.a0819cpmoacn01arce_01.utils.ResultListener;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetallePeopleFragment extends Fragment {

    public static final String KEY_ID = "id";

    @BindView(R.id.imageViewFotoPeople)
    ImageView imageViewFotoPeople;
    @BindView(R.id.textViewNombrePeople)
    TextView textViewNombrePeople;
    @BindView(R.id.textViewTrabajoPeople)
    TextView textViewTrabajoPeople;
    @BindView(R.id.textViewFechaDeNacimientoPeople)
    TextView textViewFechaDeNacimientoPeople;
    @BindView(R.id.textViewFechaDeMuertePeople)
    TextView textViewFechaDeMuertePeople;
    @BindView(R.id.textViewLugarDeNacimientoPeople)
    TextView textViewLugarDeNacimientoPeople;
    @BindView(R.id.textViewBiografiaPeople)
    TextView textViewBiografiaPeople;

    public DetallePeopleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalle_people, container, false);
        ButterKnife.bind(this, view);
        Bundle bundle = getArguments();
        String id = bundle.getString(KEY_ID);
        PeliculaController peliculaController = new PeliculaController();
        peliculaController.getActoresPeliculas(new ResultListener<People>() {
            @Override
            public void onFinish(People result) {
                Glide.with(DetallePeopleFragment.this).load(result.getProfilePath()).into(imageViewFotoPeople);
                textViewNombrePeople.setText(result.getName());
                textViewTrabajoPeople.setText(result.getKnownForDepartment());
                textViewFechaDeNacimientoPeople.setText(result.getBirthday());
                textViewFechaDeMuertePeople.setText(result.getDeathday());
                textViewLugarDeNacimientoPeople.setText(result.getPlaceOfBirth());
                textViewBiografiaPeople.setText(result.getBiography());
            }
        }, id);
        return view;
    }


}
