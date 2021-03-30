package com.github.ddnosh.arabbit.mvvm.livedata;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

/**
 * 用作事件传递的 {@link MediatorLiveData}
 */
public class EventMediatorLiveData<T> extends MediatorLiveData<T> {
    @Override
    public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super T> observer) {
        LiveEventObserver.bind(this, owner, observer);
    }

    @Override
    public void postValue(T value) {
        LiveDataUtils.setValue(this, value);
    }
}
