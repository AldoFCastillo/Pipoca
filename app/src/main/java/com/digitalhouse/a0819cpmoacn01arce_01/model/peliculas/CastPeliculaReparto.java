package com.digitalhouse.a0819cpmoacn01arce_01.model.peliculas;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class CastPeliculaReparto {



    @SerializedName("cast")
    private List<PeliculaReparto> peliculaRepartoList;

    @SerializedName("crew")
    private List<PeliculaCrew> peliculaCrewList;

    public List<PeliculaReparto> getPeliculaRepartoList() {
        return peliculaRepartoList;
    }

    public void setPeliculaRepartoList(List<PeliculaReparto> peliculaRepartoList) {
        this.peliculaRepartoList = peliculaRepartoList;
    }

    public List<PeliculaCrew> getPeliculaCrewList() {
        return peliculaCrewList;
    }

    public void setPeliculaCrewList(List<PeliculaCrew> peliculaCrewList) {
        this.peliculaCrewList = peliculaCrewList;
    }

    public CastPeliculaReparto(List<PeliculaReparto> peliculaRepartoList, List<PeliculaCrew> peliculaCrewList) {
        this.peliculaRepartoList = peliculaRepartoList;
        this.peliculaCrewList = peliculaCrewList;
    }

    public CastPeliculaReparto() {
    }
}
