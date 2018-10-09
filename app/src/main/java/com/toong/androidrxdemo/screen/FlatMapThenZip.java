package com.toong.androidrxdemo.screen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.toong.androidrxdemo.R;
import com.toong.androidrxdemo.model.Item;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.List;

public class FlatMapThenZip extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flat_map_then_zip);

        a().subscribe(new SingleObserver<List<Item>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(List<Item> items) {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    public Single<List<Item>> a() {
        return getData().flatMap(new Function<List<String>, SingleSource<List<Item>>>() {
            @Override
            public SingleSource<List<Item>> apply(List<String> stringList) throws Exception {
                return get(stringList);
            }
        });
    }

    public Single<List<Item>> get(List<String> stringList){
        List<SingleSource<String>> singleList = new ArrayList<>();
        final List<Item> itemList = new ArrayList<>();
        for(int i = 0; i < stringList.size(); i++){
            singleList.add(getDescription(i));
            Item item = new Item();
            item.setTitle(stringList.get(i));
            itemList.add(item);
        }

        return Single.zip(singleList, new Function<Object[], List<Item>>() {
            @Override
            public List<Item> apply(Object[] objects) throws Exception {
                return itemList;
            }
        });
    }

    public Single<List<String>> getData() {
        List<String> stringList = new ArrayList<>();
        stringList.add("a");
        stringList.add("b");
        return Single.just(stringList);
    }

    public Single<String> getDescription(int position) {
        return Single.just("" + position);
    }
}
