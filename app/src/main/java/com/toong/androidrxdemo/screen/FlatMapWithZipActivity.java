package com.toong.androidrxdemo.screen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.toong.androidrxdemo.R;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function3;
import java.util.ArrayList;
import java.util.List;

public class FlatMapWithZipActivity extends AppCompatActivity {
    private String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flatmap_with_zip);

        flatMapWithZipTest().doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {
                Log.i(TAG, " Log.i(TAG, \"onSubscribe\");");
            }
        }).subscribe(new Observer<List<String>>() {
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
     * FlatMapWithZipActivity: receive a
     * FlatMapWithZipActivity: onSubscribe
     * FlatMapWithZipActivity: [a, b, c]
     * FlatMapWithZipActivity: onComplete
     */
    private Observable<List<String>> flatMapWithZipTest() {
        return getData1().flatMap(new Function<String, ObservableSource<List<String>>>() {
            @Override
            public ObservableSource<List<String>> apply(String s) throws Exception {
                Log.i(TAG, "receive " + s);
                return getData1().flatMap(new Function<String, ObservableSource<List<String>>>() {
                    @Override
                    public ObservableSource<List<String>> apply(String s) throws Exception {
                        Log.i(TAG, "receive " + s);
                        return zipTest();
                    }
                });
            }
        });
    }

    private Observable<List<String>> zipTest() {
        return Observable.zip(getData1(), getData2(), getData3(),
                new Function3<String, String, String, List<String>>() {
                    @Override
                    public List<String> apply(String s, String s2, String s3) throws Exception {
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
}
