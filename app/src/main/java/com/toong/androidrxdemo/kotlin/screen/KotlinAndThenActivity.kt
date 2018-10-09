package com.toong.androidrxdemo.kotlin.screen

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.toong.androidrxdemo.R
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable

class KotlinAndThenActivity : AppCompatActivity() {

    private val TAG = KotlinFlatMapActivity::class.simpleName

    val listData: Completable
        get() = Completable.complete()

    val listData2: Single<String>
        get() = Single.just("b")

    val listData3: Single<String>
        get() = Single.just("c")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_and_then)

        andThenTest().subscribe(object : SingleObserver<String> {
            override fun onSuccess(t: String) {
                Log.i(TAG, "onSuccess $t")
            }

            override fun onSubscribe(d: Disposable) {
                Log.i(TAG, "onSubscribe")
            }

            override fun onError(e: Throwable) {
                Log.i(TAG, "onError$e")
            }
        })
    }

    private fun andThenTest(): Single<String> {
        return listData.andThen(
            listData2
        )
    }
}
