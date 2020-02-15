package com.digitalhouse.a0819cpmoacn01arce_01.view.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.digitalhouse.a0819cpmoacn01arce_01.R;
import com.digitalhouse.a0819cpmoacn01arce_01.model.peliculas.Pelicula;
import com.digitalhouse.a0819cpmoacn01arce_01.view.fragment.FavoritosFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class  CarteleraAdapter extends RecyclerView.Adapter {

    private peliculaAdapterListener listener;
    private List<Pelicula> listaDePeliculas;
    private FavoritosFragment favoritosFragment = new FavoritosFragment();


    public CarteleraAdapter(List<Pelicula> listaDePeliculas, peliculaAdapterListener listener) {
        this.listaDePeliculas = listaDePeliculas;
        this.listener = listener;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflador = LayoutInflater.from(parent.getContext());
        View view = inflador.inflate(R.layout.celda_cartelera, parent, false);
       // PeliculaViewHolder peliculaViewHolder = new PeliculaViewHolder(view);
      //  return peliculaViewHolder;
        return new PeliculaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Pelicula peliculaDeLaLista = this.listaDePeliculas.get(position);
        PeliculaViewHolder peliculaViewHolder = (PeliculaViewHolder) holder;
        peliculaViewHolder.bindPelicula(peliculaDeLaLista);


    }

    @Override
    public int getItemCount() {
        return this.listaDePeliculas.size();
    }


    public interface peliculaAdapterListener {
        void informarSeleccionPelicula(Integer posicion);
    }

    class PeliculaViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageViewPosterCeldaCartelera)
        ImageView imageViewPosterCeldaCartelera;
        @BindView(R.id.textViewTitleCeldaCartelera)
        TextView textViewTitleCeldaCartelera;
        /*@BindView(R.id.detallePeliculasFloatingButton)
        FloatingActionButton detallePeliculasFloatingButton;*/
        @BindView(R.id.coordinatorCeldaCartelera)
        CoordinatorLayout coordinatorCeldaCartelera;
    /*    @BindView(R.id.imageViewDislikeCeldaPelicula)
        ImageView imageViewDislike;
        @BindView(R.id.lottieLikeCeldaPelicula)
        LottieAnimationView lottieLike;*/
        private Pelicula pelicula;
        private FirebaseAuth mAuth;
        List<Serializable> serializableList = new ArrayList<>();

        public PeliculaViewHolder(@NonNull final View itemView) {
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
                        writeDocument(itemView, listaDePeliculas.get(getAdapterPosition()));
                    } else {
                        Toast.makeText(itemView.getContext(), "Debe estar Logueado", Toast.LENGTH_SHORT).show();
                    }


                }
            });*/


        }

       /* private void writeDocument(@NonNull final View itemView, final Pelicula unaPelicula) {

            final FirebaseFirestore db = FirebaseFirestore.getInstance();
            final CollectionReference peliculas = db.collection("peliculas");

            Query query = peliculas.whereEqualTo("id", true);
            query.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    for (DocumentSnapshot document : queryDocumentSnapshots.getDocuments()) {
                        if (document.getId().equals(unaPelicula.getId())) {
                            Toast.makeText(itemView.getContext(), "ya fue agregado a favoritos", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                    db.collection("peliculas")
                            .add(pelicula)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Snackbar.make(coordinatorCeldaCartelera, "Agregado a favoritos!", Snackbar.LENGTH_SHORT).show();
                                }
                            });
                }
            });


        }

          private Boolean escribirSinRepetir(final Pelicula unaPelicula) {
            Boolean repe = false;
            final FirebaseFirestore db = FirebaseFirestore.getInstance();
            final CollectionReference peliculas = db.collection("peliculas");

            Query query = peliculas.whereEqualTo("id", true);
          //  ApiFuture<QuerySnapshot> querySnapshot = query.get();
            query.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    for (DocumentSnapshot document : queryDocumentSnapshots.getDocuments()) {
                        if (document.getId().equals(unaPelicula.getId())) {
                            Toast.makeText(itemView.getContext(), "ya fue agregado a favoritos", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }

                    writeDocument(db, itemView);

                }
            });
            return repe;
        }*/


        public void bindPelicula(Pelicula peliculaDeLaCelda) {
            this.pelicula = peliculaDeLaCelda;
            textViewTitleCeldaCartelera.setText(this.pelicula.getTitle());
            Glide.with(itemView)
                    .load(this.pelicula.getPosterPath())
                    .into(imageViewPosterCeldaCartelera);
            /*favoritosFragment.setFavButton(pelicula, imageViewDislike, lottieLike);
            favoritosFragment.setFavVisibility(pelicula, imageViewDislike, lottieLike);*/
        }


    }
}
