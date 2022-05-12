package com.psi.anidroid;

import com.google.gson.annotations.SerializedName;

public class Post {

    private int userId;

    private Integer id;

    private String title;

    @SerializedName("body")
    private String text;

    public Post(int userId, String title, String text) { //não é preciso ter o id, porque a API é que dá o valor do id
        this.userId = userId;
        this.title = title;
        this.text = text;
    }

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
