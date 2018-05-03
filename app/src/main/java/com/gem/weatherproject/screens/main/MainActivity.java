package com.gem.weatherproject.screens.main;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.gem.weatherproject.R;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    initView();
  }

  private void initView() {
    RefreshFragment refreshFragment = new RefreshFragment();
    FragmentTransaction refreshTrans = getFragmentManager().beginTransaction();
    refreshTrans.replace(R.id.main_refresh_layout, refreshFragment);
    refreshTrans.addToBackStack(null);
    refreshTrans.commit();

    WheatherListFragment listFragment = new WheatherListFragment();
    FragmentTransaction listTrans = getFragmentManager().beginTransaction();
    listTrans.replace(R.id.main_list_layout, listFragment);
    listTrans.addToBackStack(null);
    listTrans.commit();

    refreshFragment.setCallBack(listFragment.getCallBack());
  }
}
