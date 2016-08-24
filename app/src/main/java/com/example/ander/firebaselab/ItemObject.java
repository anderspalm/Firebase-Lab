package com.example.ander.firebaselab;

import android.content.ClipData;

/**
 * Created by ander on 8/23/2016.
 */
public class ItemObject {

    String mUser;
    String mtext;

    public ItemObject(){

    }

    public ItemObject(String user, String text) {
        this.mUser = user;
        this.mtext = text;
    }

    public String getmUser() {
        return mUser;
    }

    public void setmUser(String mUser) {
        this.mUser = mUser;
    }

    public String getMtext() {
        return mtext;
    }

    public void setMtext(String mtext) {
        this.mtext = mtext;
    }
}
