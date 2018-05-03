package com.gem.weatherproject.DTO;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by apple on 5/3/18.
 */

public class WeatherDTO extends RealmObject implements Serializable {
  @PrimaryKey
  @SerializedName("id")
  private String mId;

  @SerializedName("name")
  private String mName;

  @SerializedName("coord")
  private CoordinateDTO mCoordinate;

  @SerializedName("weather")
  private RealmList<ForecastDTO> mForecast;

  @SerializedName("main")
  private MainDTO mMain;

  private boolean isCurrentLocation = false;

  public String getId() {
    return mId;
  }

  public String getName() {
    return mName;
  }

  public CoordinateDTO getCoordinate() {
    return mCoordinate;
  }

  public RealmList<ForecastDTO> getForecast() {
    return mForecast;
  }

  public MainDTO getMain() {
    return mMain;
  }

  public boolean isCurrentLocation() {
    return isCurrentLocation;
  }

  public void setCurrentLocation(boolean currentLocation) {
    isCurrentLocation = currentLocation;
  }
}
