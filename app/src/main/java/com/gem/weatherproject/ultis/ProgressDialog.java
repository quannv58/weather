package com.gem.weatherproject.ultis;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.gem.weatherproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by apple on 5/3/18.
 */

public class ProgressDialog extends Dialog {
  @BindView(R.id.progress_bar)
  View progressBar;

  public ProgressDialog(@NonNull Context context) {
    super(context,android.R.style.Theme_Translucent_NoTitleBar);
    setContentView(R.layout.progress_dialog);
    ButterKnife.bind(this);
  }
}
