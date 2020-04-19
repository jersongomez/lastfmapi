package com.gestion.lastfmapi.views.topartistslisting;

import androidx.annotation.NonNull;

import com.gestion.lastfmapi.models.Artist;
import com.gestion.lastfmapi.models.TopArtistsResponse;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class TopArtistsPresenterImpl implements TopArtistsPresenter {
    Disposable mDisposable;
    TopArtistsView mView;
    TopArtistsInteractor mInteractor;

    public TopArtistsPresenterImpl(TopArtistsView view, TopArtistsInteractor interactor) {
        this.mView = view;
        this.mInteractor = interactor;
    }

    @Override

    public void onDestroy() {
        disposeRequest();
    }

    @Override
    public void getUserTopArtists(String country, int limit, String apiKey, int page) {
        disposeRequest();
        mView.showProgress();
        mView.hidEmpty();
        mDisposable = mInteractor.getTopArtists(country, limit, apiKey, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<TopArtistsResponse, List<Artist>>() {
                    @Override
                    public List<Artist> apply(@NonNull TopArtistsResponse topArtistsResponse) throws Exception {
                        if (topArtistsResponse != null && topArtistsResponse.getTopartists() != null) {
                            return topArtistsResponse.getTopartists().getArtists();
                        }
                        return new ArrayList<Artist>();
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Artist>>() {
                    @Override
                    public void accept(@NonNull List<Artist> artists) throws Exception {
                        mView.hideProgress();
                        if (artists.size() == 0) {
                            mView.showEmpty();
                        }
                        mView.updateData(artists);

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        mView.showError();
                    }
                });

    }

    private void disposeRequest() {
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }
}
