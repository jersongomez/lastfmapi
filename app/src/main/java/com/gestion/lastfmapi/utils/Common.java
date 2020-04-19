package com.gestion.lastfmapi.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Common {

    public static final String KEY_API = "829751643419a7128b7ada50de590067";
    public static String BASE_URL = "https://ws.audioscrobbler.com/2.0/";
    public static final int LIMIT_ITEMS = 10;
    public static final String DEFAULT_SEARCH = "spain";
    public static final int DEFAULT_PAGE = 1;

    private static final Common commonInstance = new Common();

    public static Common getInstance() {
        return commonInstance;
    }

    private Common() {
    }

    public static boolean isInternet(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        return isConnected;
    }

}
