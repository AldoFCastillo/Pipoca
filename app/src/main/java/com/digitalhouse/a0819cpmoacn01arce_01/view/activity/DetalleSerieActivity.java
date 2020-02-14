package com.digitalhouse.a0819cpmoacn01arce_01.view.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.digitalhouse.a0819cpmoacn01arce_01.R;
import com.digitalhouse.a0819cpmoacn01arce_01.model.series.Cast;
import com.digitalhouse.a0819cpmoacn01arce_01.model.series.ResultSeriesPopulares;
import com.digitalhouse.a0819cpmoacn01arce_01.model.series.Serie;
import com.digitalhouse.a0819cpmoacn01arce_01.view.adapter.ViewPagerDetallesAdapter;
import com.digitalhouse.a0819cpmoacn01arce_01.view.fragment.DetallePeopleFragment;
import com.digitalhouse.a0819cpmoacn01arce_01.view.fragment.DetalleSerieFragment;

import java.util.ArrayList;
import java.util.List;

public class DetalleSerieActivity extends AppCompatActivity implements DetalleSerieFragment.notificadorCast{

    public static final String KEY_POSICION = "Pos";
    public static final String KEY_CONTENEDOR_SERIES = "Cont";
    private ViewPager viewPager;
    private ViewPagerDetallesAdapter viewPagerDetallesAdapter;
    List<Fragment> fragmentList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_serie);

        viewPager = findViewById(R.id.ViewPagerDetalleSerie);
//1ro
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        Integer posicionEnLaLista = bundle.getInt(KEY_POSICION);
        ResultSeriesPopulares resultSeriesPopulares = (ResultSeriesPopulares) bundle.getSerializable(KEY_CONTENEDOR_SERIES);

        List<Serie> serieList = resultSeriesPopulares.getSeriesPopularesList();

        cargarListaDeSeries(serieList);

        viewPager.setCurrentItem(posicionEnLaLista);


    }

    private void cargarListaDeSeries(List<Serie> serieList) {
        for (Serie serie : serieList){
            DetalleSerieFragment detalleSerieFragment = DetalleSerieFragment.getInstance(serie);
            fragmentList.add(detalleSerieFragment);
        }
        viewPagerDetallesAdapter = new ViewPagerDetallesAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(viewPagerDetallesAdapter);
    }

    @Override
    public void enviarNotificacion(Cast cast, String id) {
        Intent intent = new Intent(this, PeopleDetalleActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(DetallePeopleFragment.KEY_ID, id);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(DetalleSerieActivity.this, SecondActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("position", 4);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
