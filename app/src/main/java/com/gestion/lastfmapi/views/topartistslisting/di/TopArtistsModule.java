package com.gestion.lastfmapi.views.topartistslisting.di;

import com.gestion.lastfmapi.utils.Common;
import com.gestion.lastfmapi.views.topartistslisting.TopArtistsInteractor;
import com.gestion.lastfmapi.views.topartistslisting.TopArtistsInteractorImpl;
import com.gestion.lastfmapi.views.topartistslisting.TopArtistsPresenter;
import com.gestion.lastfmapi.views.topartistslisting.TopArtistsPresenterImpl;
import com.gestion.lastfmapi.views.topartistslisting.TopArtistsView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class TopArtistsModule {
    TopArtistsView mView;

    public TopArtistsModule(TopArtistsView view) {
        mView = view;
    }

    @Singleton
    @Provides
    public TopArtistsView providesTopArtistsView() {
        return this.mView;
    }

    @Singleton
    @Provides
    public Converter.Factory providesConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Singleton
    @Provides
    public CallAdapter.Factory providesCallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @Singleton
    @Provides
    public Retrofit providesRetrofit(Converter.Factory converter, CallAdapter.Factory adapter) {
        return new Retrofit.Builder()
                .baseUrl(Common.BASE_URL)
                .addCallAdapterFactory(adapter)
                .addConverterFactory(converter)
                .build();
    }

    @Singleton
    @Provides
    public TopArtistsInteractor providesTopArtistsInteractor(Retrofit retrofit) {
        return new TopArtistsInteractorImpl(retrofit);
    }

    @Singleton
    @Provides
    public TopArtistsPresenter providesTopArtistsPresenter(TopArtistsView view, TopArtistsInteractor interactor) {
        return new TopArtistsPresenterImpl(view, interactor);

    }
}

