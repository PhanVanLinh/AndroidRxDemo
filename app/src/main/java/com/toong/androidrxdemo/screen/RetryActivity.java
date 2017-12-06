package com.toong.androidrxdemo.screen;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.toong.androidrxdemo.BaseActivity;
import com.toong.androidrxdemo.R;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.disposables.Disposable;

public class RetryActivity extends BaseActivity {
    private int getDataTimeTime = 0;
    private int maxRetryTime = 3; // total time retry

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retry);

        getData().retry(maxRetryTime).subscribeWith(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG, "onSubscribe");
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
    }

    /**
     * RetryActivity: onNext 0
     * RetryActivity: onNext 1
     * RetryActivity: onNext 2
     * RetryActivity: onNext 3
     * RetryActivity: onNext 0
     *
     * RetryActivity: onNext 1
     * RetryActivity: onNext 2
     * RetryActivity: onNext 3
     * RetryActivity: onNext 4
     * RetryActivity: onNext 5
     * RetryActivity: onNext 6
     * RetryActivity: onNext 7
     * RetryActivity: onNext 8
     * RetryActivity: onNext 9
     *
     *
     * when use retry, it may return the duplicate data
     * if the retry time > maxRetryTime => Observable will emit error
     * if retry time still < maxRetryTime and all Observable emit success => no all error call
     */
    public Observable<String> getData() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(final ObservableEmitter<String> e) throws Exception {
                getDataTimeTime++;
                for (int i = 0; i < 10; i++) {
                    if (i == 4 && getDataTimeTime < 2) { // for fake
                        e.onError(new Throwable("test exception"));
                        return;
                    } else {
                        e.onNext("" + i);
                    }
                }
            }
        });
    }

    public Single<String> getData2() {
        return Single.create(new SingleOnSubscribe<String>() {
            @Override
            public void subscribe(SingleEmitter<String> e) throws Exception {
                e.onSuccess("sd");
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
