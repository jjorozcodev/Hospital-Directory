package com.uca.jj.apps.hospitaldirectory;

import com.facebook.drawee.backends.pipeline.Fresco;

import io.realm.Realm;

public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        Fresco.initialize(this);
    }
}
