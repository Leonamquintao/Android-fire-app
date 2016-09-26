package com.fireapp;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by leonam on 25/09/16.
 */
public class FireApp extends Application {

    public void onCreate() {
        super.onCreate();

        Firebase.setAndroidContext(this);
    }

}
