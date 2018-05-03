package com.gem.weatherproject.screens.main;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gem.weatherproject.DTO.ListWeatherDTO;
import com.gem.weatherproject.DTO.WeatherDTO;
import com.gem.weatherproject.R;
import com.gem.weatherproject.base.BaseCallBack;
import com.gem.weatherproject.base.Constant;
import com.gem.weatherproject.network.NetworkControler;
import com.gem.weatherproject.realm.RealmControler;
import com.gem.weatherproject.screens.detail.DetailActivity;
import com.gem.weatherproject.ultis.DialogUtils;
import com.gem.weatherproject.ultis.NetworkUtils;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class WheatherListFragment extends Fragment implements OnRefreshClickListener, OnItemWeatherClickListener {
  private ListWeatherAdapter mAdapter;
  private List<WeatherDTO> mData = new ArrayList<>();
  private static final int REQUEST_CODE_ASK_PERMISSIONS = 100;

  @BindView(R.id.wheather_list_rcv)
  RecyclerView mListRcv;

  @BindView(R.id.wheather_list_btn)
  View mYourLocationBtn;

  public WheatherListFragment() {
    // Required empty public constructor
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_wheather_list, container, false);
    ButterKnife.bind(this, view);
    return view;
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    if (requestCode == REQUEST_CODE_ASK_PERMISSIONS) {
      if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
        getCurrentLocation();
      } else {
        Toast.makeText(getContext(), getString(R.string.permissin_denied), Toast.LENGTH_SHORT).show();
      }
    }
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mListRcv.setLayoutManager(new LinearLayoutManager(getContext()));
    mAdapter = new ListWeatherAdapter(getContext(), mData, this);
    mListRcv.setAdapter(mAdapter);
    mYourLocationBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
          getCurrentLocation();
        } else {
          int hasAccessLocationPermission = ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION);
          int hasAccessCoarseLocationPermission = ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION);
          if (hasAccessLocationPermission != PackageManager.PERMISSION_GRANTED
              || hasAccessCoarseLocationPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_CODE_ASK_PERMISSIONS);
          } else {
            getCurrentLocation();
          }
        }
      }
    });
    getListWeather();
  }

  private void getCurrentLocation() {
    FusedLocationProviderClient mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());
    mFusedLocationClient.getLastLocation()
        .addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
          @Override
          public void onSuccess(Location location) {
            if (location != null) {
              Intent intent = new Intent(getActivity(), DetailActivity.class);
              intent.putExtra(DetailActivity.KEY_WEATHER_LAT, location.getLatitude());
              intent.putExtra(DetailActivity.KEY_WEATHER_LON, location.getLongitude());
              getActivity().startActivity(intent);
            }
          }
        });
  }

  private void getListWeather() {
    if (NetworkUtils.isConnect(getContext())) {
      DialogUtils.showProgressDialog(getActivity());
      Call<ListWeatherDTO> call = NetworkControler.getAPIBuilder().getDataWeather(Constant.LIST_ID, Constant.API_KEY);
      call.enqueue(new BaseCallBack<ListWeatherDTO>() {
        @Override
        public void onSuccess(Call<ListWeatherDTO> call, Response<ListWeatherDTO> response) {
          DialogUtils.dismissProgressDialog();
          mData = response.body().getList();
          mAdapter.notifyDataSetChanged(mData);
          RealmControler.with(getActivity()).saveData(mData);
        }

        @Override
        public void onFailure(Call<ListWeatherDTO> call) {
          DialogUtils.dismissProgressDialog();
          Toast.makeText(getContext(), R.string.error_system_upgrading, Toast.LENGTH_LONG).show();
        }
      });
    } else {
      mData = RealmControler.with(getActivity()).getAllData();
      mAdapter.notifyDataSetChanged(mData);
    }
  }

  @Override
  public void onRefreshClick() {
    getListWeather();
  }

  public OnRefreshClickListener getCallBack() {
    return this;
  }

  @Override
  public void onItemWeatherClicked(WeatherDTO model) {
    Intent intent = new Intent(getActivity(), DetailActivity.class);
    intent.putExtra(DetailActivity.KEY_WEATHER_ID, model.getId());
    getActivity().startActivity(intent);
  }
}
