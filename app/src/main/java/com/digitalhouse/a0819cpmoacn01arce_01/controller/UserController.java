package com.digitalhouse.a0819cpmoacn01arce_01.controller;

import com.digitalhouse.a0819cpmoacn01arce_01.dao.UserDao;
import com.digitalhouse.a0819cpmoacn01arce_01.model.User;
import com.digitalhouse.a0819cpmoacn01arce_01.utils.ResultListener;

public class UserController {

    private UserDao userDao ;

    public UserController() {
        this.userDao = new UserDao();
    }

    public void addProfile(final ResultListener<Boolean> listener, String uId, User user){
        userDao.addProfile(new ResultListener<Boolean>() {
            @Override
            public void onFinish(Boolean result) {
                listener.onFinish(result);
            }
        }, uId, user);
    }


}
