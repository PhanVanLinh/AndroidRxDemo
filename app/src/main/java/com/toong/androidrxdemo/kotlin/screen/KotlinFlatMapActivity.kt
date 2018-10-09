package com.toong.androidrxdemo.kotlin.screen

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.toong.androidrxdemo.R
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function

class KotlinFlatMapActivity : AppCompatActivity() {

    private val TAG = KotlinFlatMapActivity::class.simpleName

    val listData: Observable<String>
        get() = Observable.just("a")

    val listData2: Observable<String>
        get() = Observable.just("b")

    val listData3: Observable<String>
        get() = Observable.just("c")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_flat_map)

        flatMapTest().subscribe(object : Observer<String> {
            override fun onSubscribe(d: Disposable) {
                Log.i(TAG, "onSubscribe")
            }

            override fun onNext(strings: String) {
                Log.i(TAG, "onNext $strings")
            }

            override fun onError(e: Throwable) {
                Log.i(TAG, "onError$e")
            }

            override fun onComplete() {
                Log.i(TAG, "onComplete")
            }
        })
    }

    private fun flatMapTest(): Observable<String> {
        return listData.flatMap { listData2 }
    }
}
