package com.toong.androidrxdemo;

import android.os.Bundle;
import android.os.Handler;
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

public class MainActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG, "onSubscribe: ");
            }

            @Override
            public void onNext(Integer value) {
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

//        getList().subscribe(observer);
//        Observable.merge(getList("1"), getList("2")).flatMap(new Function<String, ObservableSource<?>>() {
//            @Override
//            public ObservableSource<?> apply(@NonNull String s) throws Exception {
//                return null;
//            }
//        });

        getList("s").subscribe();

//        getList("s").subscribe(new Observer<String>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(String s) {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });

        //Observable.merge(a("1"), a("2")).subscribe();

//        a("1").map(new Function<String, Integer>() {
//            @Override
//            public Integer apply(@NonNull String s) throws Exception {
//                return Integer.valueOf(s);
//            }
//        }).subscribe(observer);
    }

    public Observable<Integer> a(String a){
        Log.i(TAG, "call a"+a);
        return getList(a).flatMap(new Function<String, ObservableSource<Integer>>() {
            @Override
            public ObservableSource<Integer> apply(@NonNull String s) throws Exception {
                Log.i(TAG, "call -->");
                return convertFromStringToInteger(s);
            }
        });
    }

    public Observable<String> getList(final String b){
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
                        }catch (OutOfMemoryError ex){
                            e.onError(ex);
                        }
                    }
                }, 500);
            }
        });
    }

    public Observable<Integer> convertFromStringToInteger(final String b){
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
