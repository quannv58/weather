package com.gem.weatherproject.ultis;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by apple on 5/3/18.
 */

public class NetworkUtils {
  public static boolean isConnect(Context context) {
    boolean isCon = false;
    ConnectivityManager cm = null;
    if (context != null){
      cm= (ConnectivityManager) context
          .getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    if (cm != null) {
      NetworkInfo networkInfo = cm.getActiveNetworkInfo();
      if (networkInfo != null && networkInfo.isConnected()) {
        isCon = true;
      }
    }
    return isCon;
  }
}
