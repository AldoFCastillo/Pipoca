package com.digitalhouse.a0819cpmoacn01arce_01.model.series;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Credits {

    @SerializedName("cast")
    private List<Cast> cast = null;
    /*@SerializedName("crew")
    private List<Crew> crew = null;*/
    @SerializedName("id")
    private Integer id;

    public Credits() {
    }

    public List<Cast> getCast() {
        return cast;
    }

    public void setCast(List<Cast> cast) {
        this.cast = cast;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
