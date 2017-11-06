package com.toong.androidrxdemo.screen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.toong.androidrxdemo.R;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;

public class ConcatActivity extends AppCompatActivity {
    private String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concat);

        concatDelayErrorTest2().subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG, "onSubscribe");
            }

            @Override
            public void onNext(String s) {
                Log.i(TAG, "" + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError" + e);
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete");
            }
        });
    }

    /**
     * ConcatActivity: onSubscribe
     * ConcatActivity: a
     * ConcatActivity: c
     * ConcatActivity: onErrorjava.lang.Exception: example exception
     */
    private Observable<String> concatDelayErrorTest() {
        return Observable.concatDelayError(new ArrayList<ObservableSource<? extends String>>() {{
            add(getData());
            add(getData4());
            add(getData3());
        }});
    }

    /**
     * ConcatActivity: onSubscribe
     * ConcatActivity: a
     * ConcatActivity: c
     * ConcatActivity: onErrorjava.lang.Exception: example exception
     */
    private Observable<String> concatDelayErrorTest2() {
        return Observable.concatDelayError(Observable.just(getData(), getData4(), getData3()));
    }

    /**
     * ConcatActivity: onSubscribe
     * ConcatActivity: a
     * ConcatActivity: b
     * ConcatActivity: c
     * ConcatActivity: onComplete
     */
    private Observable<String> concatTest() {
        return Observable.concat(getData(), getData2(), getData3());
    }

    public Observable<String> getData() {
        return Observable.just("a");
    }

    public Observable<String> getData2() {
        return Observable.just("b");
    }

    public Observable<String> getData3() {
        return Observable.just("c");
    }

    public Observable<String> getData4() {
        return Observable.error(new Exception("example exception"));
    }
}
