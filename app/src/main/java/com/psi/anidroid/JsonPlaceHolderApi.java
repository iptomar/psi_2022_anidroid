package com.psi.anidroid;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    @GET("posts") //https://jsonplaceholder.typicode.com/posts, é só o get do "posts" não do URL todo
    Call<List<Post>> getPosts();
}
