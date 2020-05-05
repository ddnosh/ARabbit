package com.androidwind.androidquick.sample.mvvm;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public abstract class BaseViewModel extends AndroidViewModel {

    protected Context context;
    private CompositeDisposable mCompositeDisposable;

    public BaseViewModel(@NonNull Application application) {
        super(application);
        context = application;
    }

    protected void addDisposable(Disposable disposable) {
        if (this.mCompositeDisposable == null) {
            this.mCompositeDisposable = new CompositeDisposable();
        }
        this.mCompositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (this.mCompositeDisposable != null && !mCompositeDisposable.isDisposed()) {
            this.mCompositeDisposable.clear();
        }
    }
}
