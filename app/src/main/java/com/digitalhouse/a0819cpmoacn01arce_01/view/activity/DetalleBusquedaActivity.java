package com.digitalhouse.a0819cpmoacn01arce_01.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.digitalhouse.a0819cpmoacn01arce_01.R;
import com.digitalhouse.a0819cpmoacn01arce_01.view.fragment.DetalleBusquedaFragment;

public class DetalleBusquedaActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_busqueda);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();




        DetalleBusquedaFragment detalleBusquedaFragment = new DetalleBusquedaFragment();
        detalleBusquedaFragment.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.busquedaFragmentContainer, detalleBusquedaFragment).commit();




    }
}
