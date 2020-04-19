package com.gestion.lastfmapi.views.topartistslisting;

import com.gestion.lastfmapi.api.TopArtistsService;
import com.gestion.lastfmapi.models.TopArtistsResponse;

import io.reactivex.Single;
import retrofit2.Retrofit;

public class TopArtistsInteractorImpl implements TopArtistsInteractor {
    Retrofit mRetrofit;

    public TopArtistsInteractorImpl(Retrofit retrofit) {
        mRetrofit = retrofit;
    }

    @Override
    public Single<TopArtistsResponse> getTopArtists(String country, int limit, String apiKey, int page) {
        return mRetrofit.create(TopArtistsService.class).getTopArtists(country, limit, apiKey, page);
    }
}
