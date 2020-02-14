package com.digitalhouse.a0819cpmoacn01arce_01.controller;

import com.digitalhouse.a0819cpmoacn01arce_01.model.Videos;
import com.digitalhouse.a0819cpmoacn01arce_01.model.series.Serie;
import com.digitalhouse.a0819cpmoacn01arce_01.utils.ResultListener;
import com.digitalhouse.a0819cpmoacn01arce_01.dao.SerieDAO;
import com.digitalhouse.a0819cpmoacn01arce_01.model.series.Credits;
import com.digitalhouse.a0819cpmoacn01arce_01.model.series.ResultSeriesPopulares;
import com.digitalhouse.a0819cpmoacn01arce_01.model.series.SerieDetalles;

import java.util.List;

public class SerieController {

    private SerieDAO serieDAO;

    public SerieController() {
        this.serieDAO = new SerieDAO();
    }

    public void getSeriesPopulares(final ResultListener<ResultSeriesPopulares> listenerDeLaView) {
        serieDAO.getSeries(new ResultListener<ResultSeriesPopulares>() {
            @Override
            public void onFinish(ResultSeriesPopulares result) {
                listenerDeLaView.onFinish(result);
            }
        });

    }

    public void getDetallesSerie(final ResultListener<SerieDetalles> listenerDeLaView, String id) {
        serieDAO.getSerieDetalles(new ResultListener<SerieDetalles>() {
            @Override
            public void onFinish(SerieDetalles result) {
                listenerDeLaView.onFinish(result);
            }
        }, id );

    }

    public void getCreditsSerie(final ResultListener<Credits> listenerDeLaView, String id) {
        serieDAO.getSerieCredits(new ResultListener<Credits>() {
            @Override
            public void onFinish(Credits result) {
                listenerDeLaView.onFinish(result);
            }
        }, id );

    }

    public void getVideosSeries(final ResultListener<Videos> escuchadorDeLaVista, String id){
        serieDAO.getVideosSeries(id, new ResultListener<Videos>() {
            @Override
            public void onFinish(Videos result) {
                escuchadorDeLaVista.onFinish(result);
            }
        });
    }




    public void addSerieFav(final ResultListener<Boolean> listener, Serie serie){
        serieDAO.addSerieFav(new ResultListener<Boolean>() {
            @Override
            public void onFinish(Boolean result) {
                listener.onFinish(result);
            }
        }, serie);
    }

    public void removeSerieFav(final ResultListener<Boolean> listener, Serie serie){
        serieDAO.removeSerieFav(new ResultListener<Boolean>() {
            @Override
            public void onFinish(Boolean result) {
                listener.onFinish(result);
            }
        }, serie);
    }

    public void getSeriesFav(final ResultListener<List<Serie>> listener){
        serieDAO.getSeriesFavs(new ResultListener<List<Serie>>() {
            @Override
            public void onFinish(List<Serie> result) {
                listener.onFinish(result);
            }
        });
    }

}
