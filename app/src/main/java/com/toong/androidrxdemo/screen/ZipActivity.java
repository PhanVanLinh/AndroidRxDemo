package com.toong.androidrxdemo.screen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.toong.androidrxdemo.R;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function3;
import java.util.ArrayList;
import java.util.List;

public class ZipActivity extends AppCompatActivity {
    private String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zip);

        zipTest().subscribe(new Observer<List<String>>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG, "onSubscribe");
            }

            @Override
            public void onNext(List<String> strings) {
                Log.i(TAG, "" + strings.toString());
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
    ZipActivity: onSubscribe
    ZipActivity: [a, b, c]
    ZipActivity: onComplete
    */
    private Observable<List<String>> zipTest() {
        return Observable.zip(getData1(), getData2(), getData3(),
                new Function3<String, String, String, List<String>>() {
                    @Override
                    public List<String> apply(String s, String s2, String s3) throws Exception {
                        Log.i(TAG, "receive data");
                        List<String> list = new ArrayList<>();
                        list.add(s);
                        list.add(s2);
                        list.add(s3);
                        return list;
                    }
                });
    }

    /**
     ZipActivity: onSubscribe
     ZipActivity: onErrorjava.lang.Exception: example exception
     */
    private Observable<List<String>> zipTestWithError() {
        return Observable.zip(getData1(), getData2(), getData4(),
                new Function3<String, String, String, List<String>>() {
                    @Override
                    public List<String> apply(String s, String s2, String s3) throws Exception {
                        Log.i(TAG, "receive data");
                        List<String> list = new ArrayList<>();
                        list.add(s);
                        list.add(s2);
                        list.add(s3);
                        return list;
                    }
                });
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
