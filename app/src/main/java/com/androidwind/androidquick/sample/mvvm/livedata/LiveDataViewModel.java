package com.androidwind.androidquick.sample.mvvm.livedata;

import com.androidwind.androidquick.mvvm.livedata.EventMutableLiveData;

import androidx.lifecycle.ViewModel;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class LiveDataViewModel extends ViewModel {
    private LiveDataRepository repository;
    private EventMutableLiveData<String> mLiveData;

    public EventMutableLiveData<String> getLiveData() {
        return mLiveData;
    }

    public LiveDataViewModel() {
        repository = new LiveDataRepository();
        mLiveData = repository.getLiveData();
    }

    public void test() {
        repository.test();
    }
}
