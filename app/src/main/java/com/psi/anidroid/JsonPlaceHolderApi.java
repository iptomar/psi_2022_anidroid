package com.psi.anidroid;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface JsonPlaceHolderApi {

    @GET("posts") //https://jsonplaceholder.typicode.com/posts, é só o get do "posts" não do URL todo, RELATIVE URL'S
    Call<List<Post>> getPosts(
            @Query("userId") Integer[] userId,
            @Query("_sort") String sort,
            @Query("_order") String order
    );

    @GET("posts") //https://jsonplaceholder.typicode.com/posts, é só o get do "posts" não do URL todo
    Call<List<Post>> getPosts(@QueryMap Map<String, String> parameters);

    @GET("posts/{id}/comments")
    Call<List<Comment>> getComments(@Path("id") int postId); //o retrofit já sabe que vai ter que substituir o {id} no URL por este parametro

    @GET
    Call<List<Comment>> getComments(@Url String url); //o retrofit já sabe que vai ter que substituir o {id} no URL por este parametro
}
