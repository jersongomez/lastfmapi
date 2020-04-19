package com.gestion.lastfmapi.views.topartistslisting.di;

import com.gestion.lastfmapi.views.topartistslisting.TopArtistsFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {TopArtistsModule.class})
public interface TopArtistsComponent {
    void inject(TopArtistsFragment target);
}
