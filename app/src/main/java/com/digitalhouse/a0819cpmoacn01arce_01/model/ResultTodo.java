package com.digitalhouse.a0819cpmoacn01arce_01.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ResultTodo implements Serializable {

    @SerializedName("results")
    private List<Tmdb> todoList;

    public ResultTodo(List<Tmdb> todoList) {
        this.todoList = todoList;
    }

    public List<Tmdb> getTodoList() {
        return todoList;
    }

    public void setTodoList(List<Tmdb> todoList) {
        this.todoList = todoList;
    }
}