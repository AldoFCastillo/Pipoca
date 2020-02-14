package com.digitalhouse.a0819cpmoacn01arce_01.view.fragment;


import android.content.Context;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.digitalhouse.a0819cpmoacn01arce_01.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    @BindView(R.id.imageButtonPeliculasHome)
    ImageButton imageButtonPeliculasHome;
    @BindView(R.id.imageButtonSeriesHome)
    ImageButton imageButtonSeriesHome;
    @BindView(R.id.imageButtonExtrasHome)
    ImageButton imageButtonExtrasHome;
    @BindView(R.id.imageButtonNoticiasHome)
    ImageButton imageButtonNoticiasHome;
    @BindView(R.id.imageButtonCarteleraHome)
    ImageButton imageButtonCarteleraHome;
    @BindView(R.id.imageButtonLoginHome)
    ImageButton imageButtonLoginHome;
    LoginButton loginButton;

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private NotificadorPeliculas notificadorPeliculas;
    private NotificadorSeries notificadorSeries;
    private NotificadorExtras notificadorExtras;
    private NotificadorNoticias notificadorNoticias;
    private NotificadorCartelera notificadorCartelera;
    private NotificadorLogin notificadorLogin;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.notificadorPeliculas = (NotificadorPeliculas) context;
        this.notificadorSeries = (NotificadorSeries) context;
        this.notificadorExtras = (NotificadorExtras) context;
        this.notificadorNoticias = (NotificadorNoticias) context;
        this.notificadorCartelera = (NotificadorCartelera) context;
        this.notificadorLogin = (NotificadorLogin) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_home, container, false);


        ButterKnife.bind(this, view);

        imageButtonPeliculasHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notificadorPeliculas.hicieronClickPeliculas();
            }
        });

        imageButtonSeriesHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notificadorSeries.hicieronClickSeries();
            }
        });

        imageButtonExtrasHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notificadorExtras.hicieronClickExtras();
            }
        });

        imageButtonNoticiasHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notificadorNoticias.hicieronClickNoticias();
            }
        });

        imageButtonCarteleraHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notificadorCartelera.hicieronClickCartelera();
            }
        });

        imageButtonLoginHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notificadorLogin.hicieronClickLogin();

            }
        });

        return view;
    }

    public interface NotificadorPeliculas {
        public void hicieronClickPeliculas();

    }

    public interface NotificadorSeries {
        public void hicieronClickSeries();
    }

    public interface NotificadorExtras {
        public void hicieronClickExtras();
    }

    public interface NotificadorNoticias {
        public void hicieronClickNoticias();
    }

    public interface NotificadorCartelera {
        public void hicieronClickCartelera();
    }

    public interface NotificadorLogin {
        public void hicieronClickLogin();
    }
}
