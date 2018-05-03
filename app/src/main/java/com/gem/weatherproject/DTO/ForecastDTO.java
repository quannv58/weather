package com.gem.weatherproject.DTO;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by apple on 5/3/18.
 */

public class ForecastDTO extends RealmObject implements Serializable {
  @SerializedName("id")
  private String mId;

  @SerializedName("main")
  private String mForecast;

  @SerializedName("description")
  private String mDescription;

  @SerializedName("icon")
  private String mIcon;

  public String getId() {
    return mId;
  }

  public String getForecast() {
    return mForecast;
  }

  public String getDescription() {
    return mDescription;
  }

  public String getIcon() {
    return mIcon;
  }
}
