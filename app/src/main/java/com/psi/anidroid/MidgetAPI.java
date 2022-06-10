package com.psi.anidroid;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MidgetAPI {
    @GET("api/API/1")
    Call<Anime> requestAnime();

    @GET("API/main/api")
    Call<List<Anime>> requestAllAnimes();
}
