package com.digitalhouse.a0819cpmoacn01arce_01.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.digitalhouse.a0819cpmoacn01arce_01.R;
import com.digitalhouse.a0819cpmoacn01arce_01.controller.PeliculaController;
import com.digitalhouse.a0819cpmoacn01arce_01.model.ResultTodo;
import com.digitalhouse.a0819cpmoacn01arce_01.model.Tmdb;
import com.digitalhouse.a0819cpmoacn01arce_01.model.peliculas.ResultPelicula;
import com.digitalhouse.a0819cpmoacn01arce_01.model.series.ResultSeriesPopulares;
import com.digitalhouse.a0819cpmoacn01arce_01.model.series.Serie;
import com.digitalhouse.a0819cpmoacn01arce_01.utils.ActivityUtils;
import com.digitalhouse.a0819cpmoacn01arce_01.utils.ResultListener;
import com.digitalhouse.a0819cpmoacn01arce_01.view.adapter.ViewPagerAdapter;
import com.digitalhouse.a0819cpmoacn01arce_01.view.fragment.BusquedaFragment;
import com.digitalhouse.a0819cpmoacn01arce_01.view.fragment.CarteleraFragment;
import com.digitalhouse.a0819cpmoacn01arce_01.view.fragment.DetalleBusquedaFragment;
import com.digitalhouse.a0819cpmoacn01arce_01.view.fragment.ExtrasFragment;
import com.digitalhouse.a0819cpmoacn01arce_01.view.fragment.HomeFragment;
import com.digitalhouse.a0819cpmoacn01arce_01.view.fragment.LoginFragment;
import com.digitalhouse.a0819cpmoacn01arce_01.view.fragment.NoticiasFragment;
import com.digitalhouse.a0819cpmoacn01arce_01.view.fragment.PeliculasFragment;
import com.digitalhouse.a0819cpmoacn01arce_01.view.fragment.SerieFragment;
import com.digitalhouse.a0819cpmoacn01arce_01.view.fragment.SinResultadosFragment;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements BusquedaFragment.notificadorBusqueda, HomeFragment.NotificadorCartelera, HomeFragment.NotificadorPeliculas, HomeFragment.NotificadorSeries, HomeFragment.NotificadorExtras, HomeFragment.NotificadorNoticias, HomeFragment.NotificadorLogin {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawerLayoutActivityHome)
    DrawerLayout drawerLayout;
    @BindView(R.id.navigationViewHome)
    NavigationView navigationView;
    private ResultTodo resultTodo;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mAuth = FirebaseAuth.getInstance();

        ButterKnife.bind(this);
        setToolbar();


        HomeFragment homeFragment = new HomeFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.containerfrag, homeFragment).commit();


    }


    private void setViewPager() {
        ViewPager pager = findViewById(R.id.contenedorDeFragmentHome);
        PagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);
        pager.setCurrentItem(0);
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Pipoca");
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.open_drawer,
                R.string.close_drawer);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        drawerLayout.setScrimColor(Color.TRANSPARENT);
        actionBarDrawerToggle.syncState();
        drawerLayout.closeDrawers();
        setNavigationView();
        setupViewFragment();
    }

    private void hicieronClickFavoritos() {
        Intent intent = new Intent(HomeActivity.this, FavoritosActivity.class);
        startActivity(intent);
    }

    private void hicieronClickCerrarSesion() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (currentUser != null) {
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(this, "Desconexion exitosa", Toast.LENGTH_SHORT).show();
            drawerLayout.closeDrawers();
            mAuth = FirebaseAuth.getInstance();
            setHeader();
        } else {
            Toast.makeText(this, "No has iniciado sesion", Toast.LENGTH_SHORT).show();
        }
        if (account != null) {
            //          signOut();
            //        revokeAccess();
        }
    }

    public void metodoNavigationView(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigationViewPeliculasItem:
                hicieronClickPeliculas();
                break;
            case R.id.navigationViewSeriesItem:
                hicieronClickSeries();
                break;
            case R.id.navigationViewCarteleraItem:
                hicieronClickCartelera();
                break;
            case R.id.navigationViewExtrasItem:
                hicieronClickExtras();
                break;
            case R.id.navigationViewNoticiasItem:
                hicieronClickNoticias();
                break;
            case R.id.navigationViewFavoritosItem:
                hicieronClickFavoritos();
                break;
            case R.id.navigationViewMiListaItem:
                Toast.makeText(HomeActivity.this, "Vamos a Mi Lista", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigationViewNotificacionesItem:
                Toast.makeText(HomeActivity.this, "Vamos a Mis Notificaciones", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigationViewCerrarSesionItem:
                hicieronClickCerrarSesion();
                break;
        }
    }

    public void setNavigationView() {
        setHeader();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                metodoNavigationView(item);
                return true;
            }
        });
    }

    private void setHeader() {
        View header = navigationView.getHeaderView(0);
        final ImageView imagen = header.findViewById(R.id.fotoHeader);
        final TextView email = header.findViewById(R.id.emailHeader);
        final TextView nombre = header.findViewById(R.id.nombreHeader);
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            nombre.setText(currentUser.getDisplayName().toString());
            if (nombre.length() > 0) {
                Glide.with(this).load(mAuth.getCurrentUser().getPhotoUrl()).into(imagen);
                email.setText(mAuth.getCurrentUser().getEmail());
            }
        } else {
            imagen.setVisibility(View.GONE);
            email.setVisibility(View.GONE);
            nombre.setVisibility(View.GONE);
        }
    }

    private void setupViewFragment() {
        HomeFragment homeFragment = new HomeFragment();
        ActivityUtils.replaceFragmentInActivity(
                getSupportFragmentManager(), homeFragment, R.id.contenedorDeFragmentHome);
        //TODO remplaza a:

        /*private void setFragmentOnActivity() {
            HomeFragment homeFragment = new HomeFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.contenedorDeFragmentHome, homeFragment);
            fragmentTransaction.commit();
        }*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.itemToolBarBuscar).getActionView();
        MenuItem searchMenuItem = menu.findItem(R.id.itemToolBarBuscar);

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                busqueda(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                busqueda(newText);
                return false;
            }
        });


        return true;
    }

    public void busqueda(String key) {
        PeliculaController peliculaController = new PeliculaController();
        peliculaController.getBusqueda(new ResultListener<ResultTodo>() {
            @Override
            public void onFinish(ResultTodo result) {
                resultTodo = result;
                if (resultTodo.getTodoList().size() > 0) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(BusquedaFragment.KEY_LISTA, result);
                    BusquedaFragment busquedaFragment = new BusquedaFragment();
                    actualizarPantalla(busquedaFragment, bundle);
                } else {
                    SinResultadosFragment sinResultadosFragment = new SinResultadosFragment();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.add(R.id.containerfrag, sinResultadosFragment).commit();
                }

            }
        }, key);
    }

    private void actualizarPantalla(Fragment unFragmento, Bundle bundle) {
        unFragmento.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.containerfrag, unFragmento).addToBackStack(null);
        fragmentTransaction.commit();
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

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);


    }

    @Override
    public void hicieronClickCartelera() {
        Intent intent = new Intent(HomeActivity.this, SecondActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("position", 2);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void hicieronClickPeliculas() {
        Intent intent = new Intent(HomeActivity.this, SecondActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("position", 0);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    @Override
    public void hicieronClickSeries() {
        Intent intent = new Intent(HomeActivity.this, SecondActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("position", 4);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    @Override
    public void hicieronClickExtras() {
        Intent intent = new Intent(HomeActivity.this, SecondActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("position", 3);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void hicieronClickNoticias() {
        Intent intent = new Intent(HomeActivity.this, SecondActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("position", 1);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    @Override
    public void hicieronClickLogin() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (currentUser != null) {
            Toast.makeText(this, "ya estas logueado", Toast.LENGTH_SHORT).show();
        } else {
            LoginFragment loginFragment = new LoginFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.containerfrag, loginFragment).addToBackStack(null).commit();
        }
    }

    @Override
    public void enviarNotificacionBusqueda(Tmdb tmdb, Integer posicion) {
        Intent intent = new Intent(this, DetalleBusquedaActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(DetalleBusquedaFragment.KEY_CONTENEDOR_TMDB, tmdb);
        bundle.putInt(DetalleBusquedaFragment.KEY_POSICION, posicion);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}