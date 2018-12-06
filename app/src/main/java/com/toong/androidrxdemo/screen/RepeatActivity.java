package com.toong.androidrxdemo.screen;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.toong.androidrxdemo.R;

import org.reactivestreams.Publisher;

import java.util.Observable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.functions.BooleanSupplier;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class RepeatActivity extends AppCompatActivity {

    String TAG = "RepeatActivity";

    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repeat);

        repeatWhenTest();
        //repeatUtilTest();
    }

    @SuppressLint("CheckResult")
    private void repeatWhenTest() {
        getData().subscribeOn(Schedulers.io()).repeatWhen(new Function<Flowable<Object>, Publisher<?>>() {
            @Override
            public Publisher<?> apply(Flowable<Object> objectFlowable) throws Exception {
                return objectFlowable.delay(1, TimeUnit.SECONDS);
            }
        }).takeUntil(new Predicate<String>() {
            @Override
            public boolean test(String s) throws Exception {
                return s.equals("Hello 5");
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.i(TAG, "value " + s);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        });
    }

    int repeatUtilCount = 0;
    @SuppressLint("CheckResult")
    private void repeatUtilTest() {
        getData().subscribeOn(Schedulers.io()).repeatUntil(new BooleanSupplier() {
            @Override
            public boolean getAsBoolean() throws Exception {
                repeatUtilCount++;
                Log.i(TAG, "count = "+repeatUtilCount);
                return repeatUtilCount > 5;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.i(TAG, "value " + s);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.i(TAG, "error " + throwable);
            }
        });
    }

    public Single<String> getData() {
        return Single.create(new SingleOnSubscribe<String>() {
            @Override
            public void subscribe(SingleEmitter<String> emitter) throws Exception {
                emitter.onSuccess("Hello "+ (++count));
            }
        });
    }
}
