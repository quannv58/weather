package com.gem.weatherproject.ultis;

import android.app.Activity;
import android.os.Handler;

/**
 * Created by apple on 5/3/18.
 */

public class DialogUtils {
  private static volatile ProgressDialog progress;

  private DialogUtils() {
  }

  public static void showProgressDialog(final Activity activity) {
    if (activity == null)
      return;

    if (progress != null){
      try {
        progress.cancel();
      } catch (Exception ex){
      }
    }

    progress = new ProgressDialog(activity);
    progress.setTitle("");
    progress.setCancelable(true);
    progress.setOnCancelListener(null);
    progress.setCanceledOnTouchOutside(false);
    progress.show();
  }

  public static void dismissProgressDialog() {
    try {
      if (progress != null) {
        new Handler().postDelayed(new Runnable() {
          @Override
          public void run() {
            progress.cancel();
          }
        }, 800);
      }
    } catch (Exception e) {
    }
  }
}
