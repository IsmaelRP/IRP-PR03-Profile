package es.iessaladillo.ismaelraqi.pr03.data.local.model;

import androidx.annotation.DrawableRes;

/**
 * Avatar model class
 */
// DO NOT TOUCH
public class Avatar {

    private long id;
    @DrawableRes
    private final int imageResId;
    private final String name;

    public Avatar(int imageResId, String name) {
        this.imageResId = imageResId;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getName() {
        return name;
    }

}