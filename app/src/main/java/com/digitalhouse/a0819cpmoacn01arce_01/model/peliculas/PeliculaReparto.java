package com.digitalhouse.a0819cpmoacn01arce_01.model.peliculas;

import com.google.gson.annotations.SerializedName;

public class PeliculaReparto {

    @SerializedName("cast_id")
    private Integer castId;
    @SerializedName("character")
    private String character;
    @SerializedName("credit_id")
    private String creditId;
    @SerializedName("gender")
    private Integer gender;
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("order")
    private Integer order;
    @SerializedName("profile_path")
    private Object profilePath;

    public PeliculaReparto(Integer castId, String character, String creditId, Integer gender, String id, String name, Integer order, Object profilePath) {
        this.castId = castId;
        this.character = character;
        this.creditId = creditId;
        this.gender = gender;
        this.id = id;
        this.name = name;
        this.order = order;
        this.profilePath = profilePath;
    }

    public Integer getCastId() {
        return castId;
    }

    public void setCastId(Integer castId) {
        this.castId = castId;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getCreditId() {
        return creditId;
    }

    public void setCreditId(String creditId) {
        this.creditId = creditId;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Object getProfilePath() {
        profilePath = "https://image.tmdb.org/t/p/w200" + profilePath;
        return profilePath;
    }

    public void setProfilePath(Object profilePath) {
        this.profilePath = profilePath;
    }

    public PeliculaReparto() {
    }
}
