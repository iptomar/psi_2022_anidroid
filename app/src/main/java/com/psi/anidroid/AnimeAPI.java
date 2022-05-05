package com.psi.anidroid;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AnimeAPI {
    @GET("anime/1")
    Call<Anime> requestAnime();
}
