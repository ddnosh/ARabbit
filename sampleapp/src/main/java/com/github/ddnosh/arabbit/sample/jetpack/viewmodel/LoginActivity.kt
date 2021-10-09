package com.github.ddnosh.arabbit.sample.jetpack.viewmodel

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.viewbinding.ViewBinding
import com.github.ddnosh.arabbit.sample.base.BaseActivity
import com.github.ddnosh.arabbit.sample.databinding.ActivityLoginBinding
import com.github.ddnosh.arabbit.util.LogUtil
import com.github.ddnosh.arabbit.util.RxUtil
import com.github.ddnosh.arabbit.util.ToastUtil
import com.trello.lifecycle2.android.lifecycle.AndroidLifecycle
import com.trello.rxlifecycle3.LifecycleProvider
import com.trello.rxlifecycle3.kotlin.bindUntilEvent
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

class LoginActivity : BaseActivity() {

    private val TAG = "LoginActivity"

    private lateinit var lifecycleProvider: LifecycleProvider<Lifecycle.Event>

    private val binding by binding<ActivityLoginBinding>()
    override fun attachViewBinding(): ViewBinding {
        return binding
    }

    // 使用JetPack的ViewModels
    private val logoutViewModel by viewModels<LogoutAndroidViewModel>()
    private val registerViewModel by viewModels<RegisterViewModel>()

    @SuppressLint("AutoDispose")
    override fun initViewsAndEvents(savedInstanceState: Bundle?) {
        lifecycleProvider = AndroidLifecycle.createLifecycleProvider(this)
        binding.login.setOnClickListener {
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

        binding.logout.setOnClickListener {
            logoutViewModel.logout().observe(this, {
                logoutSuccess(it)
            })
        }

        binding.register.setOnClickListener {
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
            ToastUtil.showToast("[LiveData] Has Logout")
            finish()
        }
    }

    private fun registerSuccess(count: Long) {
        LogUtil.d(TAG, "$count")
    }
}
