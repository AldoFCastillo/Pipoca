package com.digitalhouse.a0819cpmoacn01arce_01.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.digitalhouse.a0819cpmoacn01arce_01.R;
import com.digitalhouse.a0819cpmoacn01arce_01.model.peliculas.Pelicula;
import com.digitalhouse.a0819cpmoacn01arce_01.view.fragment.FavoritosFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PeliculaAdapter extends RecyclerView.Adapter {

    private CarteleraAdapter.peliculaAdapterListener listener;
    private List<Pelicula> listaDePeliculas;
    private FavoritosFragment favoritosFragment = new FavoritosFragment();


    public PeliculaAdapter(List<Pelicula> listaDePeliculas, CarteleraAdapter.peliculaAdapterListener listener) {
        this.listaDePeliculas = listaDePeliculas;
        this.listener = listener;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.celda_peliculas, parent, false);
        PeliculaViewHolder peliculaViewHolder = new PeliculaViewHolder(view);
        return peliculaViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Pelicula peliculadeLaLista = this.listaDePeliculas.get(position);
        PeliculaViewHolder peliculaViewHolder = (PeliculaViewHolder) holder;
        peliculaViewHolder.bindPelicula(peliculadeLaLista);
    }

    @Override
    public int getItemCount() {
        return listaDePeliculas.size();
    }

    class PeliculaViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imageViewTrailerCelda)
        ImageView imageViewTrailerCelda;
        @BindView(R.id.textViewNombreCelda)
        TextView textViewNombreCelda;
        @BindView(R.id.textViewGeneroCelda)
        TextView textViewGeneroCelda;
        @BindView(R.id.textViewPuntajeCelda)
        TextView textViewPuntajeCelda;
        /*@BindView(R.id.detallePeliculasFloatingButton)
        FloatingActionButton detallePeliculasFloatingButton;*/
        @BindView(R.id.frameLayoutCeldaPeliculas)
        FrameLayout frameLayoutCeldaPeliculas;
        @BindView(R.id.imageViewDislikeCeldaPelicula)
        ImageView imageViewDislike;
        @BindView(R.id.lottieLikeCeldaPelicula)
        LottieAnimationView lottieLike;
        private Pelicula pelicula;

        public PeliculaViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int adapterPosition = getAdapterPosition();
                    Pelicula pelicula = listaDePeliculas.get(adapterPosition);
                    listener.informarSeleccionPelicula(adapterPosition);
                }
            });
            /*detallePeliculasFloatingButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final FirebaseAuth mAuth;
                    mAuth = FirebaseAuth.getInstance();
                    FirebaseUser currentUser = mAuth.getCurrentUser();
                    if (currentUser != null) {
                        if (!buscarFavRepetido(pelicula)) {
                            FavoritosFragment.peliculaFavList.add(pelicula);
                            Snackbar.make(frameLayoutCeldaPeliculas, "Agregado a favoritos!", Snackbar.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(v.getContext(), "Debe estar Logueado", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        private Boolean buscarFavRepetido(Pelicula unaPelicula) {
            Boolean repe = false;
            for (Pelicula pelicula : FavoritosFragment.peliculaFavList) {
                if (pelicula.getId().equals(unaPelicula.getId())) {
                    Toast.makeText(itemView.getContext(), "Ya se encuentra en Favoritos", Toast.LENGTH_SHORT).show();
                    repe = true;
                }
            }
            return repe;*/
        }

        public void bindPelicula(Pelicula peliculaDeLaCelda) {
            this.pelicula = peliculaDeLaCelda;
            Glide.with(itemView).load(this.pelicula.getBackdropPath()).into(imageViewTrailerCelda);
            textViewNombreCelda.setText(this.pelicula.getTitle());
            textViewGeneroCelda.setText(this.pelicula.getHomepage());
            favoritosFragment.setFavButton(pelicula, imageViewDislike, lottieLike);
            favoritosFragment.setFavVisibility(pelicula, imageViewDislike, lottieLike);
           //todo para revisar la parte de abajo porque cambie el tipo de atributo

            // textViewPuntajeCelda.setText(this.pelicula.getVoteAverage());
        }
    }

    public interface peliculaAdapterListener {
        void informarSeleccion(Pelicula pelicula, String id);
    }
}
