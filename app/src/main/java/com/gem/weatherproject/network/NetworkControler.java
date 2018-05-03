package com.gem.weatherproject.network;

import com.gem.weatherproject.base.Constant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by apple on 5/3/18.
 */

public class NetworkControler {
  private static volatile RequestAPI apiBuilder;

  private NetworkControler() {
  }

  public static Gson getGson() {
    return new GsonBuilder()
        .setLenient()
        .create();
  }

  public static RequestAPI getAPIBuilder() {
    if (apiBuilder == null) {
      Gson gson = new GsonBuilder()
          .setLenient()
          .create();
      Retrofit retrofit = new Retrofit.Builder()
          .baseUrl(Constant.END_POINT)
          .addConverterFactory(GsonConverterFactory.create(gson))
          .client(getOkHttpClient(60, 60))
          .build();
      apiBuilder = retrofit.create(RequestAPI.class);
    }
    return apiBuilder;
  }

  static OkHttpClient getOkHttpClient(int readTimeOut, int connectTimeOut) {
    OkHttpClient.Builder builder = new OkHttpClient.Builder();
    builder.readTimeout(readTimeOut, TimeUnit.SECONDS)
        .connectTimeout(connectTimeOut, TimeUnit.SECONDS)
        .addInterceptor(getLoggingInterceptor());
    return builder.build();

  }

  private static Interceptor getLoggingInterceptor() {
    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    return interceptor;
  }
}
