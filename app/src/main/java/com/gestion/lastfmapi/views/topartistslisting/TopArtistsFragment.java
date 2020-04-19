package com.gestion.lastfmapi.views.topartistslisting;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gestion.lastfmapi.R;
import com.gestion.lastfmapi.adapters.TopArtistsAdapter;
import com.gestion.lastfmapi.models.Artist;
import com.gestion.lastfmapi.utils.Common;
import com.gestion.lastfmapi.views.BaseFragment;
import com.gestion.lastfmapi.views.topartistslisting.di.DaggerTopArtistsComponent;
import com.gestion.lastfmapi.views.topartistslisting.di.TopArtistsModule;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TopArtistsFragment extends BaseFragment implements TopArtistsView {
    @BindView(R.id.rc_artists)
    RecyclerView artistsRecyclerView;
    @BindView(R.id.pg_main)
    ProgressBar mProgressBar;
    private OnFragmentInteractionListener mListener;
    @BindView(R.id.ly_empty)
    View emptyLayout;
    @Inject
    TopArtistsPresenter mPresenter;
    TopArtistsAdapter mAdapter;

    public TopArtistsFragment() {
    }

    @Override
    protected void searchCountry(String country) {
        if (mAdapter != null) {
            mAdapter.clearDataset();
        }
        mPresenter.getUserTopArtists(country, Common.LIMIT_ITEMS, Common.KEY_API, Common.DEFAULT_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_artists, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerTopArtistsComponent.builder().topArtistsModule(new TopArtistsModule(this)).build().inject(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter.getUserTopArtists(Common.DEFAULT_SEARCH, Common.LIMIT_ITEMS, Common.KEY_API, Common.DEFAULT_PAGE);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void updateData(List<Artist> topArtists) {

        if(Common.getInstance().isInternet(getContext())){
            if (mAdapter == null) {
                mAdapter = new TopArtistsAdapter(topArtists, getContext(), onArtistClickListener);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                artistsRecyclerView.setLayoutManager(linearLayoutManager);
                artistsRecyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            } else {
                mAdapter.setDataset(topArtists);
            }
        }
    }

    View.OnClickListener onArtistClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int position = artistsRecyclerView.getChildLayoutPosition(view);
            Artist artist = mAdapter.getItemByPosition(position);
            if (mListener != null) {
                mListener.onArtistClicked(artist);
            }
        }
    };

    @Override
    public void showError() {
        Toast.makeText(getContext(), R.string.error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showEmpty() {
        emptyLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hidEmpty() {
        emptyLayout.setVisibility(View.GONE);

    }

    public static TopArtistsFragment newInstance() {
        return new TopArtistsFragment();
    }

    public interface OnFragmentInteractionListener {
        void onArtistClicked(Artist artist);
    }
}
