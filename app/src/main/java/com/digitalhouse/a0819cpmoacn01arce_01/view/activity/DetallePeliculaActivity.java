package com.digitalhouse.a0819cpmoacn01arce_01.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.digitalhouse.a0819cpmoacn01arce_01.R;
import com.digitalhouse.a0819cpmoacn01arce_01.controller.PeliculaController;
import com.digitalhouse.a0819cpmoacn01arce_01.dao.PeliculaDAO;
import com.digitalhouse.a0819cpmoacn01arce_01.model.peliculas.Pelicula;
import com.digitalhouse.a0819cpmoacn01arce_01.model.peliculas.PeliculaReparto;
import com.digitalhouse.a0819cpmoacn01arce_01.model.peliculas.ResultPelicula;
import com.digitalhouse.a0819cpmoacn01arce_01.view.adapter.ViewPagerDetallesAdapter;
import com.digitalhouse.a0819cpmoacn01arce_01.view.fragment.DetallePeliculaFragment;
import com.digitalhouse.a0819cpmoacn01arce_01.view.fragment.DetallePeopleFragment;

import java.util.ArrayList;
import java.util.List;

public class DetallePeliculaActivity extends AppCompatActivity implements DetallePeliculaFragment.notificadorDetallePelicula {


    private ViewPager pager;
    private ViewPagerDetallesAdapter pagerAdapter;
    private List<Fragment> fragmentList = new ArrayList<>();
    private PeliculaController peliculaController;
    private PeliculaDAO peliculaDao;
    public static final String KEY_CONTENEDOR_PELICULAS = "Cont";
    public static final String KEY_POSICION = "Pos";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pelicula);
        setupToolbar();
        pager = findViewById(R.id.ViewPagerDetallePelicula);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        Integer posicionEnLaLista = bundle.getInt(KEY_POSICION);


        ResultPelicula resultPelicula = (ResultPelicula) bundle.getSerializable(KEY_CONTENEDOR_PELICULAS);
        List<Pelicula> peliculasResult = resultPelicula.getPeliculasResult();
        cargarListaDePeliculas(peliculasResult);

        pager.setCurrentItem(posicionEnLaLista);


    }


    @Override
    public void enviarNotificacionDetallePelicula(PeliculaReparto peliculaReparto, String id) {
        Intent intent = new Intent(this, PeopleDetalleActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(DetallePeopleFragment.KEY_ID, id);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void cargarListaDePeliculas(List<Pelicula> peliculaList) {
        for (Pelicula pelicula : peliculaList) {
            DetallePeliculaFragment detallePeliculaFragment = DetallePeliculaFragment.getInstance(pelicula);
            fragmentList.add(detallePeliculaFragment);
        }
        pagerAdapter = new ViewPagerDetallesAdapter(getSupportFragmentManager(), fragmentList);
        pager.setAdapter(pagerAdapter);
    }
    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(DetallePeliculaActivity.this, SecondActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("position", 0);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}

