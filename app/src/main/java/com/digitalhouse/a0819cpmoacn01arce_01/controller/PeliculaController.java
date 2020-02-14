package com.digitalhouse.a0819cpmoacn01arce_01.controller;

import com.digitalhouse.a0819cpmoacn01arce_01.model.People;
import com.digitalhouse.a0819cpmoacn01arce_01.model.ResultTodo;
import com.digitalhouse.a0819cpmoacn01arce_01.model.Videos;
import com.digitalhouse.a0819cpmoacn01arce_01.model.peliculas.ResultPelicula;
import com.digitalhouse.a0819cpmoacn01arce_01.utils.ResultListener;
import com.digitalhouse.a0819cpmoacn01arce_01.dao.PeliculaDAO;
import com.digitalhouse.a0819cpmoacn01arce_01.model.peliculas.PeliculaCrew;
import com.digitalhouse.a0819cpmoacn01arce_01.model.peliculas.Pelicula;
import com.digitalhouse.a0819cpmoacn01arce_01.model.peliculas.PeliculaReparto;

import java.util.ArrayList;
import java.util.List;

public class PeliculaController {


    private PeliculaDAO peliculaDao;
    private ResultPelicula contenedorDePelicula;
    List<Pelicula> peliculasCarteleraList = new ArrayList<>();
    private Integer page = 1;
    private static final Integer PAGE_SIZE = 20;
    private Boolean hayMasResults = true;


    public PeliculaController() {
        this.peliculaDao = new PeliculaDAO();
    }

    public void getPeliculasCartelera(final ResultListener<ResultPelicula> escuchadorDeLaVista) {
        peliculaDao.getPeliculasCartelera(new ResultListener<ResultPelicula>() {
            @Override
            public void onFinish(ResultPelicula result) {
                contenedorDePelicula = result;
                peliculasCarteleraList = result.getPeliculasResult();
                if (peliculasCarteleraList.size()<PAGE_SIZE){
                    hayMasResults = false;
                }
                page = page + 1;
                escuchadorDeLaVista.onFinish(result);
            }
        }, page);
    }

    public void getPeliculasPopulares(final ResultListener<ResultPelicula> escuchadorDeLaVista) {
        peliculaDao.getPeliculasPopulares(new ResultListener<ResultPelicula>() {
            @Override
            public void onFinish(ResultPelicula result) {
                escuchadorDeLaVista.onFinish(result);
            }
        });
    }

    public void getPeliculasDetalleCartelera(
            final ResultListener<Pelicula> escuchadorDeLaVista, String id) {
        peliculaDao.getPeliculasDetalleCartelera(id, new ResultListener<Pelicula>() {
            @Override
            public void onFinish(Pelicula result) {
                escuchadorDeLaVista.onFinish(result);
            }
        });
    }

    public void getPeliculaReparto(
            final ResultListener<List<PeliculaReparto>> escuchadorDeLaVista, String id) {
        peliculaDao.getPeliculaReparto(id, new ResultListener<List<PeliculaReparto>>() {
            @Override
            public void onFinish(List<PeliculaReparto> result) {
                escuchadorDeLaVista.onFinish(result);
            }
        });
    }

    public void getPeliculaCrew(
            final ResultListener<List<PeliculaCrew>> escuchadorDeLaVista, String id) {
        peliculaDao.getPeliculaCrew(id, new ResultListener<List<PeliculaCrew>>() {
            @Override
            public void onFinish(List<PeliculaCrew> result) {
                escuchadorDeLaVista.onFinish(result);
            }
        });
    }

    public void getActoresPeliculas(final ResultListener<People> escuchadorDeLaVista, String id) {
        peliculaDao.getActoresPeliculas(id, new ResultListener<People>() {
            @Override
            public void onFinish(People result) {
                escuchadorDeLaVista.onFinish(result);
            }
        });
    }

    public void getVideos(final ResultListener<Videos> escuchadorDeLaVista, String id) {
        peliculaDao.getVideos(id, new ResultListener<Videos>() {
            @Override
            public void onFinish(Videos result) {
                escuchadorDeLaVista.onFinish(result);
            }
        });
    }

    public void getBusqueda(final ResultListener<ResultTodo> escuchadorDeLaVista, String key) {
        peliculaDao.getBusqueda(new ResultListener<ResultTodo>() {
            @Override
            public void onFinish(ResultTodo result) {
                escuchadorDeLaVista.onFinish(result);
            }
        }, key);
    }


    public void addPeliculaFav(final ResultListener<Boolean> listener, Pelicula pelicula){
        peliculaDao.addPeliculaFav(new ResultListener<Boolean>() {
            @Override
            public void onFinish(Boolean result) {
                listener.onFinish(result);
            }
        }, pelicula);
    }

    public void removePeliculaFav(final ResultListener<Boolean> listener, Pelicula pelicula){
        peliculaDao.removePeliculaFav(new ResultListener<Boolean>() {
            @Override
            public void onFinish(Boolean result) {
                listener.onFinish(result);
            }
        }, pelicula);
    }

    public void getPeliculaFav(final ResultListener<List<Pelicula>> listener){
        peliculaDao.getPeliculasFavs(new ResultListener<List<Pelicula>>() {
            @Override
            public void onFinish(List<Pelicula> result) {
                listener.onFinish(result);
            }
        });
    }



    public Boolean getHayMasResults(){
        return hayMasResults;
    }

}

