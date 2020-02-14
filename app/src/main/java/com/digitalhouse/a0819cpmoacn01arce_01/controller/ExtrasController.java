package com.digitalhouse.a0819cpmoacn01arce_01.controller;

import com.digitalhouse.a0819cpmoacn01arce_01.dao.ExtrasDao;
import com.digitalhouse.a0819cpmoacn01arce_01.model.Extra;
import com.digitalhouse.a0819cpmoacn01arce_01.utils.ResultListener;

public class ExtrasController {

    private ExtrasDao extrasDao;

    public ExtrasController() {
        this.extrasDao = new ExtrasDao();
    }


    public void getExtrasController(final ResultListener<Extra> escuchadorDeLaVista) {

        extrasDao.getExtrasDao(new ResultListener<Extra>() {
            @Override
            public void onFinish(Extra result) {
                if (!result.getAdult()) {
                    escuchadorDeLaVista.onFinish(result);
                } else {
                    getExtrasController(escuchadorDeLaVista);
                }

            }
        });
    }
}

