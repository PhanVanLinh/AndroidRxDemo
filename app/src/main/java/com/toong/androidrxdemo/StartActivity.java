package com.toong.androidrxdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.toong.androidrxdemo.screen.ConcatActivity;
import com.toong.androidrxdemo.screen.DelayActivity;
import com.toong.androidrxdemo.screen.FlatMapActivity;

import com.toong.androidrxdemo.screen.ConcatWithActivity;
import com.toong.androidrxdemo.screen.FlatMapWithZipActivity;
import com.toong.androidrxdemo.screen.MergeActivity;
import com.toong.androidrxdemo.screen.RetryActivity;
import com.toong.androidrxdemo.screen.SimpleActivity;
import com.toong.androidrxdemo.screen.ZipActivity;

public class StartActivity extends BaseActivity{
    Button btnSimple;
    Button btnMerge;
    Button btnMap;
    Button btnFlatMap;
    Button btnConcatWith;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        btnSimple = (Button) findViewById(R.id.button_simple);
        btnMerge = (Button) findViewById(R.id.button_merge);
        btnMap = (Button) findViewById(R.id.button_map);
        btnFlatMap = (Button) findViewById(R.id.button_flat_map);
        btnFlatMap = (Button) findViewById(R.id.button_flat_map);
        btnConcatWith = (Button) findViewById(R.id.button_concat_with);

        btnSimple.setOnClickListener(this);
        btnMerge.setOnClickListener(this);
        btnMap.setOnClickListener(this);
        btnFlatMap.setOnClickListener(this);

        findViewById(R.id.button_concat).setOnClickListener(this);
        findViewById(R.id.button_zip).setOnClickListener(this);
        findViewById(R.id.button_flat_map_with_zip).setOnClickListener(this);
        findViewById(R.id.button_retry).setOnClickListener(this);
        btnConcatWith.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_simple:
                startActivity(new Intent(mActivityContext, SimpleActivity.class));
                break;
            case R.id.button_merge:
                startActivity(new Intent(mActivityContext, MergeActivity.class));
                break;
            case R.id.button_map:
                startActivity(new Intent(mActivityContext, DelayActivity.class));
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
            case R.id.button_concat_with:
                startActivity(new Intent(mActivityContext, ConcatWithActivity.class));
                break;
            case R.id.button_flat_map_with_zip:
                startActivity(new Intent(mActivityContext, FlatMapWithZipActivity.class));
                break;
            case R.id.button_retry:
                startActivity(new Intent(mActivityContext, RetryActivity.class));
                break;
        }
    }
}
