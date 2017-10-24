package com.example.android.recyclerview.model;

/**
 * Created by ETORO on 02/05/2017.
 */

public class ListItem {

    private String subTitle;
    private boolean favourite = false;
    private String title;
    private int imageResId;

    // Getters and Setters

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }


    public boolean isFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }


    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }
}
