package com.digitalhouse.a0819cpmoacn01arce_01.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.digitalhouse.a0819cpmoacn01arce_01.R;
import com.digitalhouse.a0819cpmoacn01arce_01.model.peliculas.ResultPelicula;
import com.digitalhouse.a0819cpmoacn01arce_01.model.series.ResultSeriesPopulares;
import com.digitalhouse.a0819cpmoacn01arce_01.model.series.Serie;
import com.digitalhouse.a0819cpmoacn01arce_01.utils.ActivityUtils;
import com.digitalhouse.a0819cpmoacn01arce_01.view.fragment.CarteleraFragment;
import com.digitalhouse.a0819cpmoacn01arce_01.view.fragment.FavoritosFragment;
import com.digitalhouse.a0819cpmoacn01arce_01.view.fragment.LoginFragment;
import com.digitalhouse.a0819cpmoacn01arce_01.view.fragment.PeliculasFragment;
import com.digitalhouse.a0819cpmoacn01arce_01.view.fragment.SerieFragment;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoritosActivity extends AppCompatActivity implements FavoritosFragment.notificadorPelicula, FavoritosFragment.notificadorSerie {

    @BindView(R.id.navigationViewFavoritos)
    NavigationView navigationViewFavoritos;
    /*@BindView(R.id.toolbar)
    Toolbar toolbarFavoritos;*/
    @BindView(R.id.drawerLayoutActivityFavoritos)
    DrawerLayout drawerLayoutActivityFavoritos;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);
        mAuth = FirebaseAuth.getInstance();
        ButterKnife.bind(this);
        drawerLayoutActivityFavoritos.setScrimColor(Color.TRANSPARENT);
        drawerLayoutActivityFavoritos.closeDrawers();

        setNavigationView();

        setupViewFragment();
    }



    public void setNavigationView() {
        setHeader();
        navigationViewFavoritos.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                metodoNavigationView(item);
                return true;
            }
        });
    }

    public void metodoNavigationView(MenuItem item) {
        Intent intent = new Intent(FavoritosActivity.this, SecondActivity.class);
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
                Intent intentFav = new Intent(FavoritosActivity.this, FavoritosActivity.class);
                startActivity(intentFav);
                break;
            case R.id.navigationViewMiListaItem:
                Toast.makeText(FavoritosActivity.this, "Vamos a Mi Lista", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigationViewNotificacionesItem:
                Toast.makeText(FavoritosActivity.this, "Vamos a Mis Notificaciones", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigationViewCerrarSesionItem:
                FirebaseUser currentUser = mAuth.getCurrentUser();
                GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
                if (currentUser != null) {
                    FirebaseAuth.getInstance().signOut();
                    Toast.makeText(this, "Desconexion exitosa", Toast.LENGTH_SHORT).show();
                    drawerLayoutActivityFavoritos.closeDrawers();
                    mAuth = FirebaseAuth.getInstance();
                    setHeader();
                } else{
                    Toast.makeText(this, "No has iniciado sesion", Toast.LENGTH_SHORT).show();
                }
                if (account != null) {
                    //          signOut();
                    //        revokeAccess();
                }
                break;
        }
    }

    /*public void metodoNavigationView(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigationViewPeliculasItem:
                Intent pelicula = new Intent(this, PeliculasActivity.class);
                startActivity(pelicula);
                break;
            case R.id.navigationViewSeriesItem:
                Intent serie = new Intent(this, SerieActivity.class);
                startActivity(serie);
                break;
            case R.id.navigationViewCarteleraItem:
                Intent cartelera = new Intent(this, CarteleraActivity.class);
                startActivity(cartelera);
                break;
            case R.id.navigationViewExtrasItem:
                Intent extras = new Intent(this, ExtrasActivity.class);
                startActivity(extras);
                break;
            case R.id.navigationViewNoticiasItem:
                Intent noticias = new Intent(this, NoticiasActivity.class);
                startActivity(noticias);
                break;
            case R.id.navigationViewFavoritosItem:
                Toast.makeText(this, "Usted ya se encuentra en Favoritos", Toast.LENGTH_LONG).show();
                break;
            case R.id.navigationViewMiListaItem:
                Toast.makeText(this, "Vamos a Mi Lista", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigationViewNotificacionesItem:
                Toast.makeText(this, "Vamos a Mis Notificaciones", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigationViewCerrarSesionItem:
                Toast.makeText(this, "Vamos a Mis Estadisticas", Toast.LENGTH_SHORT).show();
                break;
        }
    }*/

    @Override
    public void onBackPressed() {
        if (drawerLayoutActivityFavoritos.isDrawerOpen(GravityCompat.START)) {
            drawerLayoutActivityFavoritos.closeDrawer(GravityCompat.START);
        } else {
            Intent favoritos = new Intent(this, HomeActivity.class);
            startActivity(favoritos);
        }
    }

    private void setHeader() {
        View header = navigationViewFavoritos.getHeaderView(0);
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
        FavoritosFragment favoritosFragment = new FavoritosFragment();
        ActivityUtils.replaceFragmentInActivity(
                getSupportFragmentManager(), favoritosFragment, R.id.contenedorDeFavoritos);

        //TODO remplaza a:

        /*private void favoritosFragment() {
            FavoritosFragment favoritosFragment = new FavoritosFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.contenedorDeFavoritos, favoritosFragment).commit();*/
    }







    @Override
    public void enviarNotificacionSeries(ResultSeriesPopulares resultSeriesPopulares, Integer posicion) {
        Intent intent = new Intent(this, DetalleSerieActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(DetalleSerieActivity.KEY_CONTENEDOR_SERIES, resultSeriesPopulares);
        bundle.putInt(DetalleSerieActivity.KEY_POSICION, posicion);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void enviarNotificacionPeliculas(ResultPelicula resultPelicula, Integer posicion) {
        Intent intent = new Intent(this, DetallePeliculaActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(DetallePeliculaActivity.KEY_CONTENEDOR_PELICULAS, resultPelicula);
        bundle.putInt(DetallePeliculaActivity.KEY_POSICION, posicion);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
