package com.gestion.lastfmapi.databases;

@SuppressWarnings("WeakerAccess")
public class DataBaseTables {

    public static final String TABLE_ARTIST = "artists";
    public static final String ARTIST_NAME = "artist_name";
    public static final String ARTIST_LISTENERS = "artist_listeners";
    public static final String ARTIST_MBID = "artist_mbid";
    public static final String ARTIST_URL = "artist_url";
    public static final String ARTIST_STREAMABLE = "artist_streamable_fm";
    public static final String ARTIST_IMAGE = "artist_image";
    public static final String ARTIST_COUNTRY = "artist_country";

    public static final String ARTISTS = "CREATE TABLE " + TABLE_ARTIST + " ( "
            + ARTIST_NAME + " TEXT, "
            + ARTIST_LISTENERS + " TEXT, "
            + ARTIST_MBID + " TEXT, "
            + ARTIST_URL + " TEXT, "
            + ARTIST_STREAMABLE + " TEXT, "
            + ARTIST_IMAGE + " TEXT, "
            + ARTIST_COUNTRY + " TEXT "
            + ")";

}
