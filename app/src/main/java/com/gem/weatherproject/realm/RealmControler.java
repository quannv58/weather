package com.gem.weatherproject.realm;

import android.app.Activity;
import android.app.Application;

import com.gem.weatherproject.DTO.WeatherDTO;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by apple on 5/3/18.
 */

public class RealmControler {
  private static volatile RealmControler mInstance;
  private final Realm mRealm;

  public RealmControler(Application application) {
    mRealm = Realm.getDefaultInstance();
  }

  public static RealmControler with(Activity activity) {
    if (mInstance == null) {
      mInstance = new RealmControler(activity.getApplication());
    }
    return mInstance;
  }

  public Realm getRealm() {
    return mRealm;
  }

  public void saveData(final List<WeatherDTO> data) {
    mRealm.executeTransaction(new Realm.Transaction() {
      @Override
      public void execute(Realm realm) {
        for (WeatherDTO weatherDTO : data) {
          mRealm.copyToRealmOrUpdate(weatherDTO);
        }
      }
    });
  }

  public void saveCurrentLocation(final WeatherDTO data) {
    mRealm.executeTransaction(new Realm.Transaction() {
      @Override
      public void execute(Realm realm) {
        RealmResults<WeatherDTO> rows = realm.where(WeatherDTO.class).equalTo("isCurrentLocation",true).findAll();
        rows.deleteAllFromRealm();
        mRealm.copyToRealmOrUpdate(data);
      }
    });
  }

  public RealmResults<WeatherDTO> getAllData() {
    return mRealm.where(WeatherDTO.class)
        .findAll();
  }

  public WeatherDTO getDataById(String id) {
    RealmResults<WeatherDTO> list= mRealm.where(WeatherDTO.class)
        .equalTo("mId", id)
        .findAll();
    if (!list.isEmpty()) {
      return mRealm.copyFromRealm(list.first());
    } else {
      return null;
    }
  }

  public WeatherDTO getCurrentLocation() {
    RealmResults<WeatherDTO> list= mRealm.where(WeatherDTO.class)
        .equalTo("isCurrentLocation", true)
        .findAll();
    if (!list.isEmpty()) {
      return mRealm.copyFromRealm(list.first());
    } else {
      return null;
    }
  }
}
