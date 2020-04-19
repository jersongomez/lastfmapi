package com.gestion.lastfmapi.views.topartistslisting;

public interface TopArtistsPresenter {
    void onDestroy();

    void getUserTopArtists(String country, int limit, String apiKey, int page);
}
