package com.digitalhouse.a0819cpmoacn01arce_01.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.digitalhouse.a0819cpmoacn01arce_01.R;
import com.digitalhouse.a0819cpmoacn01arce_01.utils.ActivityUtils;
import com.digitalhouse.a0819cpmoacn01arce_01.view.fragment.DetallePeopleFragment;

public class PeopleDetalleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_detalle);
        setupViewFragment();
    }

    private void setupViewFragment() {
        DetallePeopleFragment detallePeopleFragment = new DetallePeopleFragment();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        detallePeopleFragment.setArguments(bundle);
        ActivityUtils.replaceFragmentInActivity(
                getSupportFragmentManager(), detallePeopleFragment, R.id.contenedorDeFragmentDetallePeople);
        //TODO remplaza a:

        /*  DetallePeopleFragment detallePeopleFragment = new DetallePeopleFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        detallePeopleFragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contenedorDeFragmentDetallePeople, detallePeopleFragment).commit();*/
    }
}
