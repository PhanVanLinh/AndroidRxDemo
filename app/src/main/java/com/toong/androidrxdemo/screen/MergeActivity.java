package com.toong.androidrxdemo.screen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import com.toong.androidrxdemo.R;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MergeActivity extends AppCompatActivity {
    private String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merge);
        getSupportActionBar().setTitle("Merge");

        final Observer observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG, "onSubscribe");
            }

            @Override
            public void onNext(String string) {
                Log.i(TAG, "onNext " + string);
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError" + e);
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete");
            }
        };



        findViewById(R.id.btnMerge).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        findViewById(R.id.btnMergeWithError).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mergeTestWithError();
            }
        });

        findViewById(R.id.btnMergeDelayError).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mergeTestWithDelayError().subscribe(observer);
            }
        });
    }



    /**
     * MergeActivity: onSubscribe
     * MergeActivity: onNext a
     * MergeActivity: onNext b
     * MergeActivity: onNext c
     * MergeActivity: onComplete
     */
    private Observable<String> mergeTest() {
        return Observable.merge(getData1(), getData2(), getData3());
    }

    /**
     * MergeActivity: onSubscribe
     * MergeActivity: onNext a
     * MergeActivity: onError java.lang.Exception: example exception
     *
     * When error happened it will stop another stream
     */
    private Observable<String> mergeTestWithError() {
        return Observable.merge(getData1(), getData4(), getData2());
    }

    /**
     * MergeActivity: onSubscribe
     * MergeActivity: onNext a
     * MergeActivity: onNext b
     * MergeActivity: onError java.lang.Exception: example exception
     *
     * When error happened it don't stop another stream
     */
    private Observable<String> mergeTestWithDelayError() {
        return Observable.mergeDelayError(getData1(), getData4(), getData2());
    }

    public Observable<String> getData1() {
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
