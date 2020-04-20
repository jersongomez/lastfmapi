package com.gestion.lastfmapi.views.topartistslisting;


import com.gestion.lastfmapi.models.Artist;

import java.util.List;

public interface TopArtistsView {
    void showProgress();
    void hideProgress();
    void updateData(List<Artist> topArtists, String country);
    void showError();
    void showEmpty();
    void hidEmpty();
    void dbData(List<Artist> topArtists);
}
