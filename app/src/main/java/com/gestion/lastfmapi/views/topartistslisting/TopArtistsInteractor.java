package com.gestion.lastfmapi.views.topartistslisting;

import com.gestion.lastfmapi.models.TopArtistsResponse;

import io.reactivex.Single;

public interface TopArtistsInteractor {
    Single<TopArtistsResponse> getTopArtists(String country, int limit, String apiKey, int page);
}
