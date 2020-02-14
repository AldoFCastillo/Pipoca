package com.digitalhouse.a0819cpmoacn01arce_01.model.peliculas;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;


public class ResultPelicula implements Serializable {


    @SerializedName("results")
    private List<Pelicula> peliculasResult;

    public ResultPelicula(List<Pelicula> peliculasResult) {
        this.peliculasResult = peliculasResult;
    }

    public ResultPelicula() {
    }

    public List<Pelicula> getPeliculasResult() {
        return peliculasResult;
    }

    public void setPeliculasResult(List<Pelicula> peliculasResult) {
        this.peliculasResult = peliculasResult;
    }

}