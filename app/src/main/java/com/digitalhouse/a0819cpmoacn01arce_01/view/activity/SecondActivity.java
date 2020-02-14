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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
import com.digitalhouse.a0819cpmoacn01arce_01.utils.ResultListener;
import com.digitalhouse.a0819cpmoacn01arce_01.view.adapter.ViewPagerAdapter;
import com.digitalhouse.a0819cpmoacn01arce_01.view.fragment.BusquedaFragment;
import com.digitalhouse.a0819cpmoacn01arce_01.view.fragment.CarteleraFragment;
import com.digitalhouse.a0819cpmoacn01arce_01.view.fragment.DetalleBusquedaFragment;
import com.digitalhouse.a0819cpmoacn01arce_01.view.fragment.ExtrasFragment;
import com.digitalhouse.a0819cpmoacn01arce_01.view.fragment.NoticiasFragment;
import com.digitalhouse.a0819cpmoacn01arce_01.view.fragment.PeliculasFragment;
import com.digitalhouse.a0819cpmoacn01arce_01.view.fragment.SerieFragment;
import com.digitalhouse.a0819cpmoacn01arce_01.view.fragment.SinResultadosFragment;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SecondActivity extends AppCompatActivity implements BusquedaFragment.notificadorBusqueda, PeliculasFragment.NotificadorPeliculas, SerieFragment.notificador, CarteleraFragment.notificadorCartelera, NoticiasFragment.notificadorNoticias {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawerLayoutActivitySecond)
    DrawerLayout drawerLayout;
    @BindView(R.id.navigationViewSecondActivity)
    NavigationView navigationView;
    private FirebaseAuth mAuth;
    @BindView(R.id.tabLayoutSecondActivity)
    TabLayout tabLayout;
    private ResultTodo resultTodo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);
        setToolbar();
       /* setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
*/

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Integer position = bundle.getInt("position");
        final ViewPager pager = findViewById(R.id.contenedorDeFragmentSecondActivity);
        tabLayout.addTab(tabLayout.newTab().setText("Peliculas"));
        tabLayout.addTab(tabLayout.newTab().setText("Cines Cercanos"));
        tabLayout.addTab(tabLayout.newTab().setText("Cartelera"));
        tabLayout.addTab(tabLayout.newTab().setText("Extras"));
        tabLayout.addTab(tabLayout.newTab().setText("Series"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        PagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        tabLayout.getTabCount();
        pager.setAdapter(pagerAdapter);
        pager.setCurrentItem(position);
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

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

    public void metodoNavigationView(MenuItem item) {
        Intent intent = new Intent(SecondActivity.this, SecondActivity.class);
        Bundle bundle = new Bundle();
        switch (item.getItemId()) {
            case R.id.navigationViewPeliculasItem:
                bundle.putInt("position", 0);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.navigationViewSeriesItem:
                bundle.putInt("position", 4);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.navigationViewCarteleraItem:
                bundle.putInt("position", 2);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.navigationViewExtrasItem:
                bundle.putInt("position", 3);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.navigationViewNoticiasItem:
                bundle.putInt("position", 1);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.navigationViewFavoritosItem:
                Intent intentFav = new Intent(SecondActivity.this, FavoritosActivity.class);
                startActivity(intentFav);
                break;
            case R.id.navigationViewMiListaItem:
                Toast.makeText(SecondActivity.this, "Vamos a Mi Lista", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigationViewNotificacionesItem:
                Toast.makeText(SecondActivity.this, "Vamos a Mis Notificaciones", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigationViewCerrarSesionItem:
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
                break;
        }
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
                tabLayout.setVisibility(View.GONE);
                busqueda(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                tabLayout.setVisibility(View.GONE);
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
                    fragmentTransaction.add(R.id.containerfragSecondActivity, sinResultadosFragment).commit();
                }

            }
        }, key);
    }

    private void actualizarPantalla(Fragment unFragmento, Bundle bundle) {
        unFragmento.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.containerfragSecondActivity, unFragmento);
        fragmentTransaction.commit();
    }

    @Override
    public void enviarNotificacionCartelera(ResultPelicula contenedorDePelicula, Integer posicion) {
        Intent intent = new Intent(this, DetallePeliculaActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(DetallePeliculaActivity.KEY_CONTENEDOR_PELICULAS, contenedorDePelicula);
        bundle.putInt(DetallePeliculaActivity.KEY_POSICION, posicion);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void enviarNotificacionNoticias() {
    }


    @Override
    public void enviarNotificacionPeliculas(ResultPelicula contenedorDePelicula, Integer posicion) {
        Intent intent = new Intent(this, DetallePeliculaActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(DetallePeliculaActivity.KEY_CONTENEDOR_PELICULAS, contenedorDePelicula);
        bundle.putInt(DetallePeliculaActivity.KEY_POSICION, posicion);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void enviarNotificacionSerie(ResultSeriesPopulares result, Integer posicion) {
        Intent intent = new Intent(this, DetalleSerieActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(DetalleSerieActivity.KEY_CONTENEDOR_SERIES, result);
        bundle.putInt(DetalleSerieActivity.KEY_POSICION, posicion);
        intent.putExtras(bundle);
        startActivity(intent);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
