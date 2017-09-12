package com.toong.androidrxdemo.screen;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.toong.androidrxdemo.BaseActivity;
import com.toong.androidrxdemo.R;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class SimpleActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG, "onSubscribe: ");
            }

            @Override
            public void onNext(String value) {
                Log.e(TAG, "onNext: " + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: ");
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete: All Done!");
            }
        };

        getListData("1").subscribe(observer);

        findViewById(R.id.button).setOnClickListener(this);
    }

    public Observable<String> getListData(final String b) {
        return Observable.just("a", "b");
    }

    @Override
    public void onClick(View view) {
        getListData("asd").doOnNext(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.i(TAG, "onNext 1 " + s);
            }
        }).doOnComplete(new Action() {
            @Override
            public void run() throws Exception {
                Log.i(TAG, "onComplete 1");
            }
        }).flatMap(new Function<String, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(@NonNull String s) throws Exception {
                return getListData("asdasd").doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.i(TAG, "onNext 2 " + s);
                    }
                }).doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.i(TAG, "onComplete 2");
                    }
                });
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull String s) {

            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete 3");
            }
        });
    }
}
