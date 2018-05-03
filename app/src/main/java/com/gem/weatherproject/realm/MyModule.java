package com.gem.weatherproject.realm;

import com.gem.weatherproject.DTO.CoordinateDTO;
import com.gem.weatherproject.DTO.ForecastDTO;
import com.gem.weatherproject.DTO.MainDTO;
import com.gem.weatherproject.DTO.WeatherDTO;

import io.realm.annotations.RealmModule;

/**
 * Created by apple on 5/3/18.
 */

@RealmModule(classes = {CoordinateDTO.class, ForecastDTO.class, MainDTO.class, WeatherDTO.class})
public class MyModule {
}
