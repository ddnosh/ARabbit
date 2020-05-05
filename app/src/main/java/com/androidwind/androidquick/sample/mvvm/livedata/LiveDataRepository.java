package com.androidwind.androidquick.sample.mvvm.livedata;

import com.androidwind.androidquick.mvvm.livedata.EventMutableLiveData;
import com.androidwind.androidquick.mvvm.livedata.LiveDataUtils;

import java.util.concurrent.TimeUnit;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class LiveDataRepository {

    private EventMutableLiveData<String> liveData = new EventMutableLiveData<>();

    public EventMutableLiveData<String> getLiveData() {
        return liveData;
    }

    public void test() {
        Observable.timer(2000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        LiveDataUtils.update(liveData, "aha");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
