package com.gem.weatherproject.screens.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gem.weatherproject.DTO.WeatherDTO;
import com.gem.weatherproject.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by apple on 5/3/18.
 */

public class ListWeatherAdapter extends RecyclerView.Adapter<ListWeatherAdapter.WeatherViewHolder> {
  private Context mContext;
  private List<WeatherDTO> mData = new ArrayList<>();
  private OnItemWeatherClickListener mListener;

  public ListWeatherAdapter(Context context, List<WeatherDTO> data, OnItemWeatherClickListener listener) {
    this.mContext = context;
    this.mData = data;
    this.mListener = listener;
  }

  @NonNull
  @Override
  public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(mContext).inflate(R.layout.item_wheather_list, parent, false);
    return new WeatherViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull WeatherViewHolder holder, int position) {
    final WeatherDTO weather = mData.get(position);
    holder.mLocationTv.setText("Lat: " + weather.getCoordinate().getLatitude() + ", Long: " + weather.getCoordinate().getLongtitude());
    holder.mWeatherTv.setText(weather.getForecast().get(0).getForecast());
    holder.mTemperatureTv.setText(String.valueOf(weather.getMain().getTemperature()));
    holder.mCountryTv.setText(weather.getName());
    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if (mListener != null) {
          mListener.onItemWeatherClicked(weather);
        }
      }
    });
  }

  @Override
  public int getItemCount() {
    return mData.size();
  }

  public void notifyDataSetChanged(List<WeatherDTO> data) {
    if(null == data && data.isEmpty()) return;

    if(null != mData) {
      mData.clear();
    }
    mData.addAll(data);
    this.notifyDataSetChanged();
  }

  public class WeatherViewHolder extends RecyclerView.ViewHolder{
    @BindView(R.id.item_wheather_location_tv)
    TextView mLocationTv;

    @BindView(R.id.item_wheather_wheather_tv)
    TextView mWeatherTv;

    @BindView(R.id.item_wheather_temperature_tv)
    TextView mTemperatureTv;

    @BindView(R.id.item_wheather_country_tv)
    TextView mCountryTv;

    public WeatherViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}

interface OnItemWeatherClickListener {
  void onItemWeatherClicked(WeatherDTO model);
}
