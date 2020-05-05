package com.androidwind.androidquick.sample.mvvm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.androidwind.androidquick.util.RxUtil;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;

public class LoginViewModel extends BaseViewModel {
    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<Boolean> login() {
        final MutableLiveData<Boolean> loginData = new MutableLiveData<>();
        //进行一些验证
//        if (验证未通过) {
//            data.setValue(false);
//            return data;
//        }

        Disposable subscribe = Observable.create((ObservableOnSubscribe<Boolean>) emitter -> {
                    try {
                        Thread.sleep(2000); // 假设此处是耗时操作
                    } catch (Exception e) {
                        e.printStackTrace();
//                        emitter.onError(new RuntimeException());
                    }
                    emitter.onNext(true);
                }
        )
                .compose(RxUtil.io2Main())
                .subscribe(aBoolean -> {
                    loginData.setValue(aBoolean);
                }, throwable -> loginData.setValue(null));
        addDisposable(subscribe);

        return loginData;
    }
}
