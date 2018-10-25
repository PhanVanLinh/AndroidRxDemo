package com.toong.androidrxdemo.kotlin.screen

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.toong.androidrxdemo.R
import io.reactivex.Maybe


class KotlinMaybeActivity : AppCompatActivity() {
    private val TAG = KotlinMaybeActivity::class.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_maybe)

        //Some Emission
        val singleSource = Maybe.just("single item")
        singleSource.subscribe({
            Log.i(TAG, "Item received: from singleSource $it")
        }, {
            it.printStackTrace()
        }, {
            Log.i(TAG, "Done from SingleSource")
        })

        val emptySource = Maybe.empty<Int>()
        emptySource.subscribe({
            Log.i(TAG, "Item received: from emptySource $it")
        }, {
            it.printStackTrace()
        }, {
            Log.i(TAG, "Done from EmptySource")
        })
    }

    fun getChannel(): Maybe<Int> {
        return Maybe.empty<Int>()
    }
}
