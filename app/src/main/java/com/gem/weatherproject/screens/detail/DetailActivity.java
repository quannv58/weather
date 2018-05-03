package com.gem.weatherproject.screens.detail;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.gem.weatherproject.DTO.ListWeatherDTO;
import com.gem.weatherproject.DTO.WeatherDTO;
import com.gem.weatherproject.R;
import com.gem.weatherproject.base.BaseCallBack;
import com.gem.weatherproject.base.Constant;
import com.gem.weatherproject.network.NetworkControler;
import com.gem.weatherproject.realm.RealmControler;
import com.gem.weatherproject.ultis.DialogUtils;
import com.gem.weatherproject.ultis.NetworkUtils;
import com.gem.weatherproject.ultis.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
  @BindView(R.id.detail_back_btn)
  ImageView mBackImg;

  @BindView(R.id.detail_refresh_btn)
  ImageView mRefreshImg;

  @BindView(R.id.detail_weather_img)
  ImageView mWeatherImg;

  @BindView(R.id.detail_country_tv)
  TextView mCountryTv;

  @BindView(R.id.detail_location_tv)
  TextView mLocationTv;

  @BindView(R.id.detail_wheather_tv)
  TextView mWeatherTv;

  @BindView(R.id.detail_temperature_tv)
  TextView mTemperatureTv;

  @BindView(R.id.detail_humidity_tv)
  TextView mHumidityTv;

  private String mWeatherId;
  private double mLocationLat;
  private double mLocationLon;

  public static final String KEY_WEATHER_ID = "key_weather_object";
  public static final String KEY_WEATHER_LAT = "key_weather_lat";
  public static final String KEY_WEATHER_LON = "key_weather_lon";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail);
    ButterKnife.bind(this);
    mWeatherId = getIntent().getStringExtra(KEY_WEATHER_ID);
    mLocationLat = getIntent().getDoubleExtra(KEY_WEATHER_LAT, 0);
    mLocationLon = getIntent().getDoubleExtra(KEY_WEATHER_LON, 0);
    if (StringUtils.isEmpty(mWeatherId)) {
      getWeatherByLocation();
    } else {
      getModelDetail();
    }
  }

  private void getWeatherByLocation() {
    if (NetworkUtils.isConnect(this)) {
      DialogUtils.showProgressDialog(this);
      Call<WeatherDTO> call = NetworkControler.getAPIBuilder().getDataByLocation(mLocationLat, mLocationLon, Constant.API_KEY);
      call.enqueue(new BaseCallBack<WeatherDTO>() {
        @Override
        public void onSuccess(Call<WeatherDTO> call, Response<WeatherDTO> response) {
          DialogUtils.dismissProgressDialog();
          WeatherDTO model = response.body();
          model.setCurrentLocation(true);
          RealmControler.with(DetailActivity.this).saveCurrentLocation(model);
          initView(model);
        }

        @Override
        public void onFailure(Call<WeatherDTO> call) {
          DialogUtils.dismissProgressDialog();
          Toast.makeText(DetailActivity.this, R.string.error_system_upgrading, Toast.LENGTH_LONG).show();
        }
      });
    } else {
      initView(RealmControler.with(DetailActivity.this).getCurrentLocation());
    }
  }

  private void getModelDetail() {
    if (NetworkUtils.isConnect(this)) {
      DialogUtils.showProgressDialog(this);
      Call<ListWeatherDTO> call = NetworkControler.getAPIBuilder().getDataWeather(mWeatherId, Constant.API_KEY);
      call.enqueue(new BaseCallBack<ListWeatherDTO>() {
        @Override
        public void onSuccess(Call<ListWeatherDTO> call, Response<ListWeatherDTO> response) {
          DialogUtils.dismissProgressDialog();
          initView(response.body().getList().get(0));
        }

        @Override
        public void onFailure(Call<ListWeatherDTO> call) {
          DialogUtils.dismissProgressDialog();
          Toast.makeText(DetailActivity.this, R.string.error_system_upgrading, Toast.LENGTH_LONG).show();
        }
      });
    } else {
      initView(RealmControler.with(this).getDataById(mWeatherId));
    }
  }

  private void initView(WeatherDTO weatherModel) {
    mBackImg.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        finish();
      }
    });

    mRefreshImg.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if (StringUtils.isEmpty(mWeatherId)) {
          getWeatherByLocation();
        } else {
          getModelDetail();
        }
      }
    });

    if (weatherModel == null) {
      return;
    }

    if (weatherModel.getForecast() != null
        && !weatherModel.getForecast().isEmpty()
        && !StringUtils.isEmpty(weatherModel.getForecast().get(0).getIcon())) {
      Glide.with(this)
          .load(Constant.IMG_LINK + weatherModel.getForecast().get(0).getIcon() + ".png")
          .into(mWeatherImg);
    } else {
      mWeatherImg.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.default_img));
    }

    mCountryTv.setText(weatherModel.getName());
    mLocationTv.setText("Lat: " + weatherModel.getCoordinate().getLatitude() + ", Long: " + weatherModel.getCoordinate().getLongtitude());
    mWeatherTv.setText(weatherModel.getForecast().get(0).getForecast());
    mTemperatureTv.setText(String.valueOf(weatherModel.getMain().getTemperature()));
    mHumidityTv.setText(String.valueOf(weatherModel.getMain().getHumidity()));
  }
}
