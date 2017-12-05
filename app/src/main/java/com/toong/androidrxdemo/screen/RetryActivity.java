package com.toong.androidrxdemo.screen;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import com.toong.androidrxdemo.BaseActivity;
import com.toong.androidrxdemo.R;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RetryActivity extends BaseActivity {
    boolean isFirstTime = true;
    private int i = 1;
    CountDownTimer mCountDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retry);

        //        getData().subscribe(new Observer<String>() {
        //            @Override
        //            public void onSubscribe(Disposable d) {
        //
        //            }
        //
        //            @Override
        //            public void onNext(String s) {
        //                Log.i(TAG, "onNext " + s);
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

        getData().retry(2).subscribeWith(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                Log.i(TAG, "onNext " + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError " + e);
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete");
            }
        });

//        getData().subscribe(new Observer<String>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(String s) {
//                Log.i(TAG, "onNext " + s);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.i(TAG, "onError " + e);
//            }
//
//            @Override
//            public void onComplete() {
//                Log.i(TAG, "onComplete");
//            }
//        });
    }

    public Observable<String> getData() {
        i = 1;
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(final ObservableEmitter<String> e) throws Exception {
                mCountDownTimer = new CountDownTimer(1200, 200) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        if (i == 4) {
                            if (isFirstTime) {
                                isFirstTime = false;
                                Log.i(TAG, "will throw");
                                e.onError(new Throwable("test exception"));
                                mCountDownTimer.cancel();
                            } else {
                                e.onComplete();
                                mCountDownTimer.cancel();
                            }
                        }
                        e.onNext("" + i);
                        i++;
                    }

                    @Override
                    public void onFinish() {

                    }
                };
                mCountDownTimer.start();
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
