package com.psi.anidroid;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RequestInterface {
    @GET("sample_object.json")
    Call<SampleResponse> getSampleResponse();
}
