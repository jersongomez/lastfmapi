package com.gestion.lastfmapi.api;

import com.gestion.lastfmapi.models.TopArtistsResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TopArtistsService {
    @GET("?method=geo.gettopartists&format=json")
    Single<TopArtistsResponse> getTopArtists(@Query("country") String country, @Query("limit") int limit, @Query("api_key") String apiKey, @Query("page") int page);
}
