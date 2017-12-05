package com.toong.androidrxdemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.MainThread;
import android.support.annotation.UiThread;
import android.support.annotation.WorkerThread;
import android.util.Log;
import android.view.View;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG, "onSubscribe: ");
            }

            @Override
            public void onNext(String value) {
                Log.e(TAG, "onNext: " + value);
                setText();
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

        getString().subscribeOn(Schedulers.io()).subscribe(observer);

        getList("s").subscribe();

        doSomething();
    }

    @WorkerThread
    public void doSomething() {
        // this method must be called from the worker thread
    }

    @UiThread
    public void setText() {

    }

    @MainThread
    public Observable<String> getString() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("Test");
            }
        });
    }

    @WorkerThread

    public Observable<Integer> a(String a) {
        Log.i(TAG, "call a" + a);
        return getList(a).flatMap(new Function<String, ObservableSource<Integer>>() {
            @Override
            public ObservableSource<Integer> apply(@NonNull String s) throws Exception {
                Log.i(TAG, "call -->");
                return convertFromStringToInteger(s);
            }
        });
    }

    public Observable<String> getList(final String b) {
        Log.i(TAG, "call b");
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(final @NonNull ObservableEmitter<String> e) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            throw new OutOfMemoryError();
                            //                            e.onNext("" + b);
                            //                            e.onComplete();
                        } catch (OutOfMemoryError ex) {
                            e.onError(ex);
                        }
                    }
                }, 500);
            }
        });
    }

    public Observable<Integer> convertFromStringToInteger(final String b) {
        return Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(final @NonNull ObservableEmitter<Integer> e) {
                e.onNext(Integer.valueOf(b));
                e.onComplete();
            }
        });
    }

    @Override
    public void onClick(View view) {

    }
}
