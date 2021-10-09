package com.github.ddnosh.arabbit.sample.module.network.coroutine.interceptor

import com.github.ddnosh.arabbit.sample.util.CacheUtil
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class MyHeadInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        builder.addHeader("token", "token123456").build()
        builder.addHeader("device", "Android").build()
        builder.addHeader("isLogin", CacheUtil.isLogin().toString()).build()
        return chain.proceed(builder.build())
    }
}
