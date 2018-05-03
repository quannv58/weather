package com.gem.weatherproject.DTO;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by apple on 5/3/18.
 */

public class CoordinateDTO extends RealmObject implements Serializable {
  @SerializedName("lon")
  private float mLongtitude;

  @SerializedName("lat")
  private float mLatitude;

  public float getLongtitude() {
    return mLongtitude;
  }

  public float getLatitude() {
    return mLatitude;
  }
}
