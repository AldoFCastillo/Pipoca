package com.digitalhouse.a0819cpmoacn01arce_01.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Tmdb implements Serializable {

    @SerializedName("id")
    private String id;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("original_title")
    private String originalTitle;
    @SerializedName("original_name")
    private String originalName;
    @SerializedName("name")
    private String name;
    @SerializedName("profile_path")
    private String profilePath;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPosterPath() {
        return direccion + posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilePath() {
        return direccion + profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }

    public Tmdb(String id, String posterPath, String originalTitle, String originalName, String name, String profilePath) {
        this.id = id;
        this.posterPath = posterPath;
        this.originalTitle = originalTitle;
        this.originalName = originalName;
        this.name = name;
        this.profilePath = profilePath;
    }

    public Tmdb() {
    }

    private String direccion = "https://image.tmdb.org/t/p/original";
}