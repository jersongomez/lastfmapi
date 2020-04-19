package com.gestion.lastfmapi.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.gestion.lastfmapi.R;
import com.gestion.lastfmapi.adapters.PrincipalPagerAdapter;
import com.gestion.lastfmapi.models.Artist;
import com.gestion.lastfmapi.views.topartistslisting.TopArtistsFragment;
import com.google.android.material.tabs.TabLayout;
import com.mancj.materialsearchbar.MaterialSearchBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Principal extends AppCompatActivity implements TopArtistsFragment.OnFragmentInteractionListener {

    @BindView(R.id.tl_main)
    TabLayout mTabLayout;
    @BindView(R.id.vp_main)
    ViewPager mViewPager;
    PrincipalPagerAdapter mAdapter;
    @BindView(R.id.searchBar)
    MaterialSearchBar searchEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        ButterKnife.bind(this);
        looseSearchEditTextFocus();
        initializeFragments();

        searchEditText.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (isValidSearch(searchEditText.getText().toString())) {
                    searchValue(searchEditText.getText().toString());
                } else {
                    showEnterValidValueToast();
                }
            }
        });
    }

    @Override
    public void onArtistClicked(Artist artist) {
        openUrl(artist.getUrl());
    }

    void openUrl(String url) {
        if (!TextUtils.isEmpty(url)) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }
    }

    private void initializeFragments() {
        mAdapter = new PrincipalPagerAdapter(getSupportFragmentManager(), this);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOffscreenPageLimit(3);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void showEnterValidValueToast() {
        Toast.makeText(this, R.string.write_value, Toast.LENGTH_SHORT).show();
    }

    private boolean isValidSearch(String search) {
        if (TextUtils.isEmpty(search))
            return false;
        return true;
    }

    private void searchValue(String string) {
        for (Fragment fr : mAdapter.getFragments()
        ) {
            if (fr instanceof BaseFragment) {
                ((BaseFragment) fr).searchCountry(string);
            }
        }
    }

    private void looseSearchEditTextFocus() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        searchEditText.clearFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

        }
    }
}
