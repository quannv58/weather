package com.gem.weatherproject.DTO;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by apple on 5/3/18.
 */

public class MainDTO extends RealmObject implements Serializable {
  @SerializedName("temp")
  private float mTemperature;

  @SerializedName("pressure")
  private float mPressure;

  @SerializedName("humidity")
  private float mHumidity;

  @SerializedName("temp_min")
  private float mMinTemp;

  @SerializedName("temp_max")
  private float mMaxTemp;

  public float getTemperature() {
    return mTemperature;
  }

  public float getPressure() {
    return mPressure;
  }

  public float getHumidity() {
    return mHumidity;
  }

  public float getMinTemp() {
    return mMinTemp;
  }

  public float getMaxTemp() {
    return mMaxTemp;
  }
}
