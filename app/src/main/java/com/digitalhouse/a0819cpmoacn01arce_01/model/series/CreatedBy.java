package com.digitalhouse.a0819cpmoacn01arce_01.model.series;

import com.google.gson.annotations.SerializedName;

public class CreatedBy {

    @SerializedName("id")
    private Integer id;
    @SerializedName("credit_id")
    private String creditId;
    @SerializedName("name")
    private String name;
    @SerializedName("gender")
    private Integer gender;
    @SerializedName("profile_path")
    private Object profilePath;

    public CreatedBy() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreditId() {
        return creditId;
    }

    public void setCreditId(String creditId) {
        this.creditId = creditId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Object getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(Object profilePath) {
        this.profilePath = profilePath;
    }
}
