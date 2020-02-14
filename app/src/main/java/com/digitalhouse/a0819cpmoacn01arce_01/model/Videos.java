package com.digitalhouse.a0819cpmoacn01arce_01.model;

import com.digitalhouse.a0819cpmoacn01arce_01.model.peliculas.Result;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Videos implements Serializable {

    @SerializedName("results")
    @Expose
    private List<Result> results;

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public Videos(List<Result> results) {
        this.results = results;
    }

    public Videos() {
    }
}

