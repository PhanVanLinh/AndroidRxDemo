package com.toong.androidrxdemo.screen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import com.toong.androidrxdemo.R;
import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import java.util.concurrent.TimeUnit;

public class DelayActivity extends AppCompatActivity {
    private static String TAG = DelayActivity.class.getSimpleName();
    Disposable mDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delay);

        findViewById(R.id.button_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
            }
        });
    }

    private void start() {
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
            doATask();
            return;
        }
        Completable a = Completable.timer(5, TimeUnit.SECONDS, AndroidSchedulers.mainThread());
        mDisposable = a.subscribe(new Action() {
            @Override
            public void run() throws Exception {
                doATask();
            }
        });
    }

    private void doATask() {
        Log.i(TAG, "doATask");
    }
}
