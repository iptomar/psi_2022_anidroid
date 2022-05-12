package com.psi.anidroid;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
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

    @POST("posts")
    Call<Post> createPost(@Body Post post);

    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPost(
            @Field("userId") int userId, //chamar o nome das variáveis na API
            @Field("title") String title,
            @Field("body") String text
    );

    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPost(@FieldMap Map<String, String> fields);

    @PUT("posts/{id}") //substitui totalmente o corpo
    Call<Post> putPost(@Path("id") int id, @Body Post post);

    @PATCH("posts/{id}") //substitui apenas os campos que enviamos, se mandarmos valores nulos, o JSON substitui esse valor por o valor que está na API
    Call<Post> patchPost(@Path("id") int id, @Body Post post);

    @DELETE("posts/{id}")
    Call<Void> deletePost(@Path("id") int id);
}
