package com.gestion.lastfmapi.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Artist {

    @SerializedName("name")
    private String name;
    @SerializedName("listeners")
    private String listeners;
    @SerializedName("mbid")
    private String mbid;
    @SerializedName("url")
    private String url;
    @SerializedName("streamable")
    private String streamable;
    @SerializedName("image")
    private List<ImageItem> images;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getListeners() {
        return listeners;
    }

    public void setListeners(String playCount) {
        this.listeners = playCount;
    }

    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStreamable() {
        return streamable;
    }

    public void setStreamable(String streamable) {
        this.streamable = streamable;
    }

    public List<ImageItem> getImages() {
        return images;
    }

    public void setImages(List<ImageItem> images) {
        this.images = images;
    }

    public String getImageUrl(String size) {
        if (getImages() != null && getImages().size() > 0) {
            for (ImageItem img :
                    getImages()) {
                if (img.getSize().equalsIgnoreCase(size)) {
                    return img.getUrl();
                }
            }
        }
        return null;
    }
}
