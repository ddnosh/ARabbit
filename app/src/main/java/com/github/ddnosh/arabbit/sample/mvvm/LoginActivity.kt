package com.github.ddnosh.arabbit.sample.mvvm;

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import com.github.ddnosh.arabbit.module.rxbus.RxBus
import com.github.ddnosh.arabbit.sample.R
import com.github.ddnosh.arabbit.sample.base.BaseActivity
import com.github.ddnosh.arabbit.sample.event.TestEvent
import com.github.ddnosh.arabbit.util.LogUtil
import com.github.ddnosh.arabbit.util.RxUtil
import com.github.ddnosh.arabbit.util.ToastUtil
import com.trello.rxlifecycle3.android.FragmentEvent
import com.trello.rxlifecycle3.kotlin.bindUntilEvent
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import kotlinx.android.synthetic.main.activity_login.*
import java.util.concurrent.TimeUnit

class LoginActivity : BaseActivity() {

    private val TAG = "LoginActivity"

    //使用JetPack的ViewModels
    private val logoutViewModel: LogoutViewModel by viewModels()
    private val registerViewModel: RegisterViewModel by viewModels()

    override val contentViewLayoutID: Int = R.layout.activity_login

    @SuppressLint("AutoDispose")
    override fun initViewsAndEvents(savedInstanceState: Bundle?) {
        super.initViewsAndEvents(savedInstanceState)

        login.setOnClickListener {
//            Observable.create { emitter: ObservableEmitter<Boolean?> ->
//                try {
//                    Thread.sleep(2000) // 假设此处是耗时操作
//                } catch (e: Exception) {
//                    e.printStackTrace()
//                    //                        emitter.onError(new RuntimeException());
//                }
//                emitter.onNext(true)
//            }
//                    .compose(RxUtil.io2Main())
//                    .bindUntilEvent(lifecycleProvider, Lifecycle.Event.ON_STOP)
//                    .subscribe(
//                            { ToastUtil.showToast("[RxJava + RxLifecycle] Has Login") }
//                    )
//                    { ToastUtil.showToast("[RxJava + RxLifecycle] Error") }

            Observable.interval(0, 1, TimeUnit.SECONDS)
                    .compose(RxUtil.io2Main())
                    .bindUntilEvent(lifecycleProvider, Lifecycle.Event.ON_STOP)
                    .subscribe(
                            { LogUtil.d(TAG, "$it") }
                    ) { ToastUtil.showToast("[RxJava + RxLifecycle] Error") }
        }

        logout.setOnClickListener {
            logoutViewModel.logout().observe(this, {
                logoutSuccess(it)
            })
        }

        register.setOnClickListener {
            registerViewModel.register().observe(this, {
                registerSuccess(it)
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtil.d(TAG, "onDestroy")
    }

    private fun logoutSuccess(isSuccess: Boolean) {
        if (isSuccess) {
            ToastUtil.showToast("[LiveData] Has Logout");
            finish();
        }
    }

    private fun registerSuccess(count: Long) {
        LogUtil.d(TAG, "$count")
    }
}
