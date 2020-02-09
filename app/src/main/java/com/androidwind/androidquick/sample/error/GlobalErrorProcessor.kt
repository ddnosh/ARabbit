@file:Suppress("UNREACHABLE_CODE")

package com.androidwind.androidquick.sample.error

import android.util.Log
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.androidwind.androidquick.module.rxjava.error.core.GlobalErrorTransformer
import com.androidwind.androidquick.module.rxjava.error.retry.RetryConfig
import io.reactivex.Observable
import io.reactivex.Single
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException

object GlobalErrorProcessor {

    /**
     * Status code
     */
    const val STATUS_OK = 200
    const val STATUS_UNAUTHORIZED = 401

    fun <T> processGlobalError(fragmentActivity: FragmentActivity): GlobalErrorTransformer<T> = GlobalErrorTransformer(

        // 通过onNext流中数据的状态进行操作
        onNextInterceptor = {
            when (it) {
                STATUS_UNAUTHORIZED -> Observable.error(
                    Errors.AuthorizationError(timeStamp = System.currentTimeMillis())
                )
                else -> Observable.just(it)
            }
        },

        // 通过onError中Throwable状态进行操作
        onErrorResumeNext = { error ->
            when (error) {
                is ConnectException ->
                    Observable.error<T>(Errors.ConnectFailedException)
                // 这个错误会在onErrorRetrySupplier()中处理
                is Errors.AuthorizationError -> Observable.error<T>(error)
                is HttpException -> Observable.error<T>(Errors.NetException)
                else -> Observable.error<T>(error)
            }
        },

        onErrorRetrySupplier = { retrySupplierError ->
            when (retrySupplierError) {
                // 网络连接异常，弹出dialog，并根据用户选择结果进行错误重试处理
                Errors.ConnectFailedException ->
                    RetryConfig.none()
                // 用户认证失败，弹出login界面
                is Errors.AuthorizationError ->
                    RetryConfig.none()
                is Errors.NetException -> {
                    RetryConfig.simpleInstance {
                        Single.just(false)
                    }
                }
                else -> RetryConfig.none()      // 其它异常都不重试
            }
        },

        onErrorConsumer = { error ->
            when (error) {
                is JSONException -> {
                    Toast.makeText(fragmentActivity, "$error", Toast.LENGTH_SHORT).show()
                    Log.w("rx stream Exception", "Json解析异常:${error.message}")
                }
                is Errors.NetException -> {
                    Toast.makeText(fragmentActivity, "出错啦！", Toast.LENGTH_SHORT).show()
                }
                else -> Log.w("rx stream Exception", "其它异常:${error.message}")
            }
        }
    )
}