package com.toong.androidrxdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by PhanVanLinh on 12/09/2017.
 * phanvanlinh.94vn@gmail.com
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener{
    protected final String TAG = getClass().getSimpleName();
    protected Context mActivityContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityContext = this;
    }
}
