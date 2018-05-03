package com.gem.weatherproject.DTO;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by apple on 5/3/18.
 */

public class ListWeatherDTO implements Serializable {
  @SerializedName("cnt")
  private int mCount;

  @SerializedName("list")
  private List<WeatherDTO> mList;

  public int getCount() {
    return mCount;
  }

  public List<WeatherDTO> getList() {
    return mList;
  }
}
