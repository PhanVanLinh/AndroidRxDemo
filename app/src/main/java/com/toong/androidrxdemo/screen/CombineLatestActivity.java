package com.toong.androidrxdemo.screen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.toong.androidrxdemo.R;
import com.toong.androidrxdemo.model.Item;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import java.util.ArrayList;
import java.util.List;

public class CombineLatestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compine_latest);

        getAList().subscribe(new Observer<List<Item>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<Item> as) {
                printList(as);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    /**
     * I/TAG: s1
     * I/TAG: b
     * I/TAG: s2
     * I/TAG: b
     */
    private void printList(List<Item> aList) {
        for (Item item : aList) {
            Log.i("TAG", "" + item.getTitle());
            Log.i("TAG", "" + item.getDescription());
        }
    }

    public Observable<List<Item>> getAList() {
        return Observable.combineLatest(getData(), getData2(),
                new BiFunction<List<String>, String, List<Item>>() {
                    @Override
                    public List<Item> apply(List<String> stringList, String s) throws Exception {
                        List<Item> aList = new ArrayList<>();
                        for (String s1 : stringList) {
                            Item a = new Item();
                            a.setTitle(s1);
                            a.setDescription(s);
                            aList.add(a);
                        }
                        return aList;
                    }
                });
    }

    public Observable<List<String>> getData() {
        List<String> s = new ArrayList<>();
        s.add("s1");
        s.add("s2");
        return Observable.just(s);
    }

    public Observable<String> getData1() {
        return Observable.just("a");
    }

    public Observable<String> getData2() {
        return Observable.just("b");
    }
}
