package com.gem.weatherproject.base;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by apple on 5/3/18.
 */

public abstract class BaseCallBack<T> implements Callback<T> {
  public abstract void onSuccess(Call<T> call, Response<T> response);

  public abstract void onFailure(Call<T> call);
  
  @Override
  public void onResponse(Call<T> call, Response<T> response) {
    if (response != null && response.isSuccessful()) {
      onSuccess(call, response);
    } else {
      onFailure(call);
    }
  }

  @Override
  public void onFailure(Call<T> call, Throwable t) {
    this.onFailure(call);
  }
}
