package com.toong.androidrxdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.toong.androidrxdemo.screen.CombineLatestActivity;
import com.toong.androidrxdemo.screen.ConcatActivity;
import com.toong.androidrxdemo.screen.ConcatWithActivity;
import com.toong.androidrxdemo.screen.DelayActivity;
import com.toong.androidrxdemo.screen.FlatMapActivity;
import com.toong.androidrxdemo.screen.FlatMapThenZip;
import com.toong.androidrxdemo.screen.FlatMapWithZipActivity;
import com.toong.androidrxdemo.screen.MergeActivity;
import com.toong.androidrxdemo.screen.RepeatActivity;
import com.toong.androidrxdemo.screen.RetryActivity;
import com.toong.androidrxdemo.screen.SimpleActivity;
import com.toong.androidrxdemo.screen.ZipActivity;

public class StartActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        findViewById(R.id.button_test).setOnClickListener(this);
        findViewById(R.id.button_simple).setOnClickListener(this);
        findViewById(R.id.button_merge).setOnClickListener(this);
        findViewById(R.id.button_map).setOnClickListener(this);
        findViewById(R.id.button_flat_map).setOnClickListener(this);
        findViewById(R.id.button_concat).setOnClickListener(this);
        findViewById(R.id.button_zip).setOnClickListener(this);
        findViewById(R.id.button_flat_map_with_zip).setOnClickListener(this);
        findViewById(R.id.button_retry).setOnClickListener(this);
        findViewById(R.id.button_repeat).setOnClickListener(this);
        findViewById(R.id.button_combine_latest).setOnClickListener(this);
        findViewById(R.id.button_concat_with).setOnClickListener(this);
        findViewById(R.id.button_flat_map_then_zip).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_test:
                startActivity(new Intent(mActivityContext, TestActivity.class));
                break;
            case R.id.button_simple:
                startActivity(new Intent(mActivityContext, SimpleActivity.class));
                break;
            case R.id.button_merge:
                startActivity(new Intent(mActivityContext, MergeActivity.class));
                break;
            case R.id.button_map:

                break;
            case R.id.button_flat_map:
                startActivity(new Intent(mActivityContext, FlatMapActivity.class));
                break;
            case R.id.button_concat:
                startActivity(new Intent(mActivityContext, ConcatActivity.class));
                break;
            case R.id.button_zip:
                startActivity(new Intent(mActivityContext, ZipActivity.class));
                break;
            case R.id.button_flat_map_with_zip:
                startActivity(new Intent(mActivityContext, FlatMapWithZipActivity.class));
                break;
            case R.id.button_concat_with:
                startActivity(new Intent(mActivityContext, ConcatWithActivity.class));
                break;
            case R.id.button_retry:
                startActivity(new Intent(mActivityContext, RetryActivity.class));
                break;
            case R.id.button_repeat:
                startActivity(new Intent(mActivityContext, RepeatActivity.class));
                break;
            case R.id.button_delay:
                startActivity(new Intent(mActivityContext, DelayActivity.class));
                break;
            case R.id.button_combine_latest:
                startActivity(new Intent(mActivityContext, CombineLatestActivity.class));
                break;
            case R.id.button_flat_map_then_zip:
                startActivity(new Intent(mActivityContext, FlatMapThenZip.class));
                break;
        }
    }
}
