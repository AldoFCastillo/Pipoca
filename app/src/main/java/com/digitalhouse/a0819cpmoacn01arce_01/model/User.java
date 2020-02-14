package com.digitalhouse.a0819cpmoacn01arce_01.model;

import com.digitalhouse.a0819cpmoacn01arce_01.model.peliculas.Pelicula;
import com.digitalhouse.a0819cpmoacn01arce_01.model.series.Serie;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String nombre;
    private String apellido;
    private String edad;
    private String mail;
    private String password;
    private String userName;
    private List<Serie> seriesFavs = new ArrayList<>();
    private List<Pelicula> peliculasFavs = new ArrayList<>();


    public User() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Serie> getSeriesFavs() {
        return seriesFavs;
    }

    public void setSeriesFavs(List<Serie> seriesFavs) {
        this.seriesFavs = seriesFavs;
    }

    public List<Pelicula> getPeliculasFavs() {
        return peliculasFavs;
    }

    public void setPeliculasFavs(List<Pelicula> peliculasFavs) {
        this.peliculasFavs = peliculasFavs;
    }
}
