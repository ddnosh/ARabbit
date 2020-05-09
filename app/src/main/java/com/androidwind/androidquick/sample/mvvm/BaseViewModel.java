package com.androidwind.androidquick.sample.mvvm;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.AndroidViewModel;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public abstract class BaseViewModel extends AndroidViewModel {

    protected Context context;
    //生命周期的处理：1.CompositeDisposable， 2.LifecycleProvider
    //    private CompositeDisposable mCompositeDisposable;
    private WeakReference<LifecycleProvider> lifecycleProvider;

    public BaseViewModel(@NonNull Application application) {
        super(application);
        context = application;
    }

//    protected void addDisposable(Disposable disposable) {
//        if (this.mCompositeDisposable == null) {
//            this.mCompositeDisposable = new CompositeDisposable();
//        }
//        this.mCompositeDisposable.add(disposable);
//    }

//    @Override
//    protected void onCleared() {
//        super.onCleared();
//        if (this.mCompositeDisposable != null && !mCompositeDisposable.isDisposed()) {
//            this.mCompositeDisposable.clear();
//        }
//    }

    public void bindLifecycleProvider(LifecycleProvider lifecycle) {
        this.lifecycleProvider = new WeakReference<>(lifecycle);
    }

    public LifecycleProvider getLifecycleProvider() {
        return lifecycleProvider.get();
    }
}
