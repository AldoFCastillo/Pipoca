package com.digitalhouse.a0819cpmoacn01arce_01.model.series;

import com.google.gson.annotations.SerializedName;


public class Genres {

    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String name;

    public Genres() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

