package com.gem.weatherproject.screens.splash;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.gem.weatherproject.BuildConfig;
import com.gem.weatherproject.R;
import com.gem.weatherproject.screens.main.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {

  @BindView(R.id.splash_background_img)
  ImageView mBackgroundImg;

  @BindView(R.id.splash_environment_tv)
  TextView mEnvironmentTv;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);
    ButterKnife.bind(this);
    initView();
    new Handler().postDelayed(new Runnable() {
      @Override
      public void run() {
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
      }
    }, 2000);
  }

  private void initView() {
    switch (BuildConfig.FLAVOR) {
      case "production":
        mBackgroundImg.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.production_bg));
        mEnvironmentTv.setText(getString(R.string.production_version));
        break;

      case "dev":
        mBackgroundImg.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.dev_bg));
        mEnvironmentTv.setText(getString(R.string.develop_version));
        break;

      case "tst":
      default:
        mBackgroundImg.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.test_bg));
        mEnvironmentTv.setText(getString(R.string.test_version));
        break;
    }
  }
}
