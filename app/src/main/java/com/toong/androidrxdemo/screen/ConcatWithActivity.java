package com.toong.androidrxdemo.screen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.toong.androidrxdemo.R;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class ConcatWithActivity extends AppCompatActivity {
    private  String TAG = getClass().getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concat_with);


        get().subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull String integer) {

            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete 3");
            }
        });
    }

    public Observable<String> getListData(final String b) {
        Log.i(TAG, "getListData " +b);
        return Observable.just("a");
    }

    public Observable<Integer> getListIntegerData() {
        Log.i(TAG, "getListIntegerData");
        return Observable.just(1);
    }

    private Observable<String> get(){
        return Observable.concat(getListData("a").doOnNext(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.i(TAG, "onNext 1 " + s);
            }
        }).doOnComplete(new Action() {
            @Override
            public void run() throws Exception {
                Log.i(TAG, "doOnComplete 1");
            }
        }), getListData("b").doOnNext(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.i(TAG, "onNext 2 " + s);
            }
        })).doOnComplete(new Action() {
            @Override
            public void run() throws Exception {
                Log.i(TAG, "doOnComplete 2");
            }
        });


//        return getListData("asd").doOnNext(new Consumer<String>() {
//            @Override
//            public void accept(String s) throws Exception {
//                Log.i(TAG, "onNext 1 " + s);
//            }
//        }).doOnComplete(new Action() {
//            @Override
//            public void run() throws Exception {
//                Log.i(TAG, "onComplete 1");
//            }
//        }).concatMapEager(new Function<String, ObservableSource<Integer>>() {
//            @Override
//            public ObservableSource<Integer> apply(final @NonNull String s) throws Exception {
//                return getListIntegerData().doOnNext(new Consumer<Integer>() {
//                    @Override
//                    public void accept(Integer integer) throws Exception {
//                        Log.i(TAG, "onNext 2 "+s);
//                    }
//                }).doOnComplete(new Action() {
//                    @Override
//                    public void run() throws Exception {
//                        Log.i(TAG, "onComplete 2");
//                    }
//                });
//            }
//        });

//        return getListData("asd").doOnNext(new Consumer<String>() {
//            @Override
//            public void accept(String s) throws Exception {
//                Log.i(TAG, "onNext 1 " + s);
//            }
//        }).doOnComplete(new Action() {
//            @Override
//            public void run() throws Exception {
//                Log.i(TAG, "onComplete 1");
//            }
//        }).flatMapCompletable(new Function<String, CompletableSource>() {
//            @Override
//            public CompletableSource apply(@NonNull String s) throws Exception {
//                return null;
//            }
//        })
    }
}
