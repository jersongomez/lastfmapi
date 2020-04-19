package com.gestion.lastfmapi.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.lang.ref.WeakReference;

public class ImageLoader {
    public static void imageLoad(Context context, String imageUrl, int placeHolderResourceID, ImageView imageView) {
        WeakReference<Context> weakReference = new WeakReference<>(context);
        Glide.with(weakReference.get()).asBitmap().load(imageUrl).placeholder(placeHolderResourceID).into(imageView);
    }
}
