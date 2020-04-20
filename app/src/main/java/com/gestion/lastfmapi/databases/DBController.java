package com.gestion.lastfmapi.databases;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.gestion.lastfmapi.models.Artist;
import com.gestion.lastfmapi.utils.Common;

import java.util.List;

public class DBController {
    private DataBaseHelper dataBaseHelper;
    Context context;

    public DBController(Context context) {
        this.context = context;
        dataBaseHelper = new DataBaseHelper(this.context);
    }

    public boolean saveArtist(Artist artist, String country){

        ContentValues values = new ContentValues();
        values.put(DataBaseTables.ARTIST_NAME, artist.getName());
        values.put(DataBaseTables.ARTIST_LISTENERS, artist.getListeners());
        values.put(DataBaseTables.ARTIST_MBID, artist.getMbid());
        values.put(DataBaseTables.ARTIST_URL, artist.getUrl());
        values.put(DataBaseTables.ARTIST_STREAMABLE, artist.getStreamable());
        values.put(DataBaseTables.ARTIST_IMAGE, artist.getImageUrl(Common.DEFAULT_SIZE_IMG));
        values.put(DataBaseTables.ARTIST_COUNTRY, country);

        Log.d("DBController" , artist.getStreamable());
        return dataBaseHelper.INSERT(DataBaseTables.TABLE_ARTIST, values);
    }

    public List<Artist> getArtist(String country){
        return dataBaseHelper.getArtist(country);
    }

    public void deleteArtist(String country) {
        dataBaseHelper.deleteArtist(country);
    }

}
