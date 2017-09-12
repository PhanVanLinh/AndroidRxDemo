package com.toong.androidrxdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.toong.androidrxdemo.screen.SimpleActivity;

public class StartActivity extends BaseActivity{
    Button btnSimple;
    Button btnMerge;
    Button btnMap;
    Button btnFlatMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        btnSimple = (Button) findViewById(R.id.button_simple);
        btnMerge = (Button) findViewById(R.id.button_merge);
        btnMap = (Button) findViewById(R.id.button_map);
        btnFlatMap = (Button) findViewById(R.id.button_flat_map);

        btnSimple.setOnClickListener(this);
        btnMerge.setOnClickListener(this);
        btnMap.setOnClickListener(this);
        btnFlatMap.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_simple:
                startActivity(new Intent(mActivityContext, SimpleActivity.class));
                break;
            case R.id.button_merge:

                break;
            case R.id.button_map:

                break;
            case R.id.button_flat_map:

                break;
        }
    }
}
