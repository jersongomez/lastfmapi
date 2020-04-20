package com.gestion.lastfmapi.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.gestion.lastfmapi.models.Artist;
import com.gestion.lastfmapi.models.ImageItem;
import com.gestion.lastfmapi.utils.Common;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String NAME = "lastfm_gestion_data";
    private static final int VERSION = 1;
    private static DataBaseHelper mInstance = null;
    private SQLiteDatabase db;

    public DataBaseHelper(Context context) {
        super(context, NAME, null, VERSION);
        db = openDatabase();
    }

    public static DataBaseHelper getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new DataBaseHelper(context.getApplicationContext());
        }
        return mInstance;
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DataBaseTables.ARTISTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DataBaseTables.ARTISTS + ";");
        onCreate(sqLiteDatabase);
    }

    public synchronized SQLiteDatabase openDatabase() {
        try{
            db = this.getWritableDatabase();
        }catch(Exception e){
        }
        return db;
    }

    public boolean INSERT(String table, ContentValues values) {
        boolean status = true;
        if (!db.isOpen()) {
            openDatabase();
        }
        long result = db.insert(table, null, values);
        if (result == -1) {
            status = false;
        }
        return status;
    }

    public List<Artist> getArtist(String name) {
        List<Artist> artistList = new ArrayList<Artist>();
        Artist artist;
        if (!db.isOpen()) {
            openDatabase();
        }
        String[] columns={
                DataBaseTables.ARTIST_NAME,
                DataBaseTables.ARTIST_LISTENERS,
                DataBaseTables.ARTIST_MBID,
                DataBaseTables.ARTIST_URL,
                DataBaseTables.ARTIST_STREAMABLE,
                DataBaseTables.ARTIST_IMAGE,
                DataBaseTables.ARTIST_COUNTRY
        };

        Cursor cursor;
        String selection = DataBaseTables.ARTIST_COUNTRY + " =? ";
        String[] args = {name};

        cursor = db.query(DataBaseTables.TABLE_ARTIST, columns,
                selection, args, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            if (cursor.getCount() > 0) {
                while (!cursor.isAfterLast()) {
                    artist = new Artist();
                    artist.setName(cursor.getString(0));
                    artist.setListeners(cursor.getString(1));
                    artist.setMbid(cursor.getString(2));
                    artist.setUrl(cursor.getString(3));
                    artist.setStreamable(cursor.getString(4));

                    List<ImageItem> imageItems = new ArrayList<ImageItem>();
                    ImageItem img = new ImageItem();
                    img.setUrl(cursor.getString(5));
                    img.setSize(Common.DEFAULT_SIZE_IMG);
                    imageItems.add(img);
                    artist.setImages(imageItems);
                    artistList.add(artist);
                    cursor.moveToNext();
                }
            }
        }

        return artistList;
    }

    public void deleteArtist(String country) {
        if (!db.isOpen()) {
            openDatabase();
        }
        db.execSQL("DELETE FROM " + DataBaseTables.ARTISTS + " WHERE " + DataBaseTables.ARTIST_COUNTRY + " = '" + country + "'");
    }

}
