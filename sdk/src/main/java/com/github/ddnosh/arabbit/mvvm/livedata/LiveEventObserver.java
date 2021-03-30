package com.github.ddnosh.arabbit.mvvm.livedata;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.OnLifecycleEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * LiveData 用作事件传递时的观察者
 * 保证所有事件不丢失，保存非激活状态的事件，并能够在激活状态回调，且没有内存泄漏
 */
public class LiveEventObserver<T> implements LifecycleObserver, Observer<T> {
    private LiveData<T> mLiveData;
    private LifecycleOwner mOwner;
    private Observer<? super T> mObserver;

    private final List<T> mPendingData = new ArrayList<>();

    public LiveEventObserver(LiveData<T> liveData, LifecycleOwner owner, Observer<? super T> observer) {
        mLiveData = liveData;
        mOwner = owner;
        mObserver = observer;
        mOwner.getLifecycle().addObserver(this);
        mLiveData.observeForever(this);
    }

    /**
     * 在生命周期结束前的任何时候都可能会调用
     */
    @Override
    public void onChanged(@Nullable T t) {
        if (isActive()) {
            // 如果是激活状态，就直接更新
            mObserver.onChanged(t);
        } else {
            // 非激活状态先把数据存起来
            mPendingData.add(t);
        }
    }

    /**
     * @return 是否是激活状态，即 onStart 之后到 onPause 之前
     */
    private boolean isActive() {
        return mOwner.getLifecycle().getCurrentState()
                .isAtLeast(Lifecycle.State.STARTED);
    }

    /**
     * onStart 之后就是激活状态了，如果之前存的有数据，就发送出去
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    private void onEvent(LifecycleOwner owner, Lifecycle.Event event) {
        if (owner != mOwner) {
            return;
        }
        if (event == Lifecycle.Event.ON_START || event == Lifecycle.Event.ON_RESUME) {
            for (int i = 0; i < mPendingData.size(); i++) {
                mObserver.onChanged(mPendingData.get(i));
            }
            mPendingData.clear();
        }
    }

    /**
     * onDestroy 时解除各方的观察和绑定，并清空数据
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private void onDestroy() {
        mLiveData.removeObserver(this);
        mLiveData = null;

        mOwner.getLifecycle().removeObserver(this);
        mOwner = null;

        mPendingData.clear();

        mObserver = null;
    }


    public static <T> void bind(@NonNull LiveData<T> liveData,
                                @NonNull LifecycleOwner owner,
                                @NonNull Observer<? super T> observer) {
        if (owner.getLifecycle().getCurrentState() == Lifecycle.State.DESTROYED) {
            return;
        }
        new LiveEventObserver<>(liveData, owner, observer);
    }
}
