package com.gem.weatherproject.realm;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;

/**
 * Created by apple on 5/3/18.
 */

public class MyRealm implements RealmMigration {
  @Override
  public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
    //RealmSchema schema = realm.getSchema();
  }
}
