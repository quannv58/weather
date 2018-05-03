package com.gem.weatherproject;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.gem.weatherproject.realm.MyRealm;

import io.fabric.sdk.android.Fabric;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by apple on 5/3/18.
 */

public class MyApplication extends Application {
  @Override
  public void onCreate() {
    super.onCreate();
    Fabric.with(this, new Crashlytics());
    Realm.init(this);
    RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
        .name(Realm.DEFAULT_REALM_NAME)
        .schemaVersion(0)
               /* .modules(new MyModule())*/
        .deleteRealmIfMigrationNeeded()
        .migration(new MyRealm())
        .build();
    Realm.setDefaultConfiguration(realmConfiguration);
  }
}
