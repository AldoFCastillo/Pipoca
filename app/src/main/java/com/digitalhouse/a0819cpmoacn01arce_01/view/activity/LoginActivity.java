package com.digitalhouse.a0819cpmoacn01arce_01.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.digitalhouse.a0819cpmoacn01arce_01.R;
import com.digitalhouse.a0819cpmoacn01arce_01.utils.ActivityUtils;
import com.digitalhouse.a0819cpmoacn01arce_01.view.fragment.DetallePeopleFragment;
import com.digitalhouse.a0819cpmoacn01arce_01.view.fragment.LoginFragment;

public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setupViewFragment();

    }

    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count == 0) {
            super.onBackPressed();
            //additional code
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }

    private void setupViewFragment() {
        LoginFragment loginFragment = new LoginFragment();
        ActivityUtils.replaceFragmentInActivity(
                getSupportFragmentManager(), loginFragment, R.id.contenedorDeLogin);
        //TODO remplaza a:

        /*public void LoginFragment(){
            LoginFragment loginFragment = new LoginFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.contenedorDeLogin, loginFragment).commit();*/
    }
}
