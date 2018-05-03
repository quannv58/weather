package com.gem.weatherproject.screens.main;


import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.gem.weatherproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class RefreshFragment extends Fragment {
  @BindView(R.id.refresh_img)
  ImageView mRefreshImg;

  private OnRefreshClickListener mListener;

  public RefreshFragment() {
    // Required empty public constructor
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_refresh, container, false);
    ButterKnife.bind(this, view);
    return view;
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    mRefreshImg.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if (mListener != null) {
          mListener.onRefreshClick();
        }
      }
    });
  }

  public void setCallBack(OnRefreshClickListener listener) {
    this.mListener = listener;
  }
}

interface OnRefreshClickListener {
  void onRefreshClick();
}
