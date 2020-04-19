package com.gestion.lastfmapi.adapters;

import android.content.Context;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.gestion.lastfmapi.R;
import com.gestion.lastfmapi.views.topartistslisting.TopArtistsFragment;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Hashtable;

public class PrincipalPagerAdapter extends FragmentPagerAdapter {
    private static final int NUM_TABS = 1;
    private static final int TOP_ARTISTS_INDEX = 0;
    private String topArtistsTitle;

    protected Hashtable<Integer, WeakReference<Fragment>> fragments;

    public PrincipalPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        topArtistsTitle = context.getString(R.string.tittle_top_artists);
        fragments = new Hashtable<>();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case TOP_ARTISTS_INDEX: {
                Fragment fr = TopArtistsFragment.newInstance();
                fragments.put(position, new WeakReference<>(fr));
                return fr;
            }
        }
        return new TopArtistsFragment();

    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case TOP_ARTISTS_INDEX: {
                return topArtistsTitle;
            }
        }
        return super.getPageTitle(position);
    }

    @Override
    public int getCount() {
        return NUM_TABS;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
        fragments.remove(position);
    }

    public ArrayList<Fragment> getFragments() {
        ArrayList<Fragment> list = new ArrayList<>();
        for (int i = 0; i < fragments.size(); i++) {
            list.add(fragments.get(i).get());
        }
        return list;
    }
}
