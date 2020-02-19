package com.androidwind.androidquick.mvvm.livedata;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

/**
 * LiveData 相关的工具类，简化 LiveData 操作
 */
public class LiveDataUtils {

    private static Handler sMainHandler;

    /**
     * 更新 MutableLiveData 的数据，会根据是否在主线程，选择调用 setValue 或 postValue
     * 因为 setValue 会更快，而且不会被后面的数据覆盖，在主线程的话可以直接 setValue
     */
    public static <T> void update(MutableLiveData<T> mld, T d) {
        if (mld == null) {
            return;
        }
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            mld.setValue(d);
        } else {
            mld.postValue(d);
        }
    }

    /**
     * 用 setValue 更新 MutableLiveData 的数据，如果在子线程，就切换到主线程
     */
    public static <T> void setValue(MutableLiveData<T> mld, T d) {
        if (mld == null) {
            return;
        }
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            mld.setValue(d);
        } else {
            postSetValue(mld, d);
        }
    }

    /**
     * 向主线程的 handler 抛 SetValueRunnable
     */
    public static <T> void postSetValue(MutableLiveData<T> mld, T d) {
        if (sMainHandler == null) {
            sMainHandler = new Handler(Looper.getMainLooper());
        }
        sMainHandler.post(SetValueRunnable.create(mld, d));
    }

    private static class SetValueRunnable<T> implements Runnable {
        private final MutableLiveData<T> liveData;
        private final T data;

        private SetValueRunnable(@NonNull MutableLiveData<T> liveData, T data) {
            this.liveData = liveData;
            this.data = data;
        }

        @Override
        public void run() {
            liveData.setValue(data);
        }

        public static <T> SetValueRunnable<T> create(@NonNull MutableLiveData<T> liveData, T data) {
            return new SetValueRunnable<>(liveData, data);
        }
    }
}
