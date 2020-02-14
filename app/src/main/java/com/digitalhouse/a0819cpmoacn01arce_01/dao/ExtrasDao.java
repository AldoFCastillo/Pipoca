package com.digitalhouse.a0819cpmoacn01arce_01.dao;

import com.digitalhouse.a0819cpmoacn01arce_01.model.Extra;
import com.digitalhouse.a0819cpmoacn01arce_01.utils.ResultListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExtrasDao extends TmdbDao {

    public static final String API_KEY = "e00143389939e722c4dd551059122bbf";
    private int max = 99999;
    private int min = 1;
    private Boolean loadingRequest = false;

    public void getExtrasDao(final ResultListener<Extra> resultListener) {
        int x = (int) (Math.random() * ((max - min) + 1)) + min;
        int id = x;
        if (!loadingRequest) {
            Call<Extra> call = tmdbService.getExtras(id, API_KEY);
            loadingRequest = true;
            call.enqueue(new Callback<Extra>() {
                @Override
                public void onResponse(Call<Extra> call, Response<Extra> response) {
                    Extra resultExtras = response.body();
                    if (response.body() != null) {
                        resultListener.onFinish(resultExtras);
                        loadingRequest = false;
                    } else {
                        getExtrasDao(resultListener);
                    }
                }

                @Override
                public void onFailure(Call<Extra> call, Throwable t) {
                    String message = t.getMessage();
                    System.out.println("ha ocurrido un error" + message);
                    t.printStackTrace();
                }
            });

        }
    }
}

