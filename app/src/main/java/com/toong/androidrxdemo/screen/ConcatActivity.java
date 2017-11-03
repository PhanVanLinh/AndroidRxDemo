package com.toong.androidrxdemo.screen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.toong.androidrxdemo.R;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ConcatActivity extends AppCompatActivity {
    private String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concat);

        concatTest().subscribe(new Observer<String>() {
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
     * ConcatActivity: b
     * ConcatActivity: c
     * ConcatActivity: onComplete
     */
    private Observable<String> concatTest() {
        return Observable.concat(getListData(), getListData2(), getListData3());
    }

    public Observable<String> getListData() {
        return Observable.just("a");
    }

    public Observable<String> getListData2() {
        return Observable.just("b");
    }

    public Observable<String> getListData3() {
        return Observable.just("c");
    }
}
