package com.example.admed.crudrealm.application;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Luis Eduardo on 20/09/2017.
 */

public class CRUDRealm extends Application {


    private static CRUDRealm instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().name("crudrealm.realm").build();
        Realm.setDefaultConfiguration(config);
    }

    public static CRUDRealm getInstance() {
        return instance;
    }

}
