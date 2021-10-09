package com.github.ddnosh.arabbit.ext

import com.github.ddnosh.arabbit.util.LogUtil

const val TAG = "ARabbit"

fun String.logv(tag: String = TAG) = LogUtil.v(tag, this)
fun String.logd(tag: String = TAG) = LogUtil.d(tag, this)
fun String.logi(tag: String = TAG) = LogUtil.i(tag, this)
fun String.logw(tag: String = TAG) = LogUtil.w(tag, this)
fun String.loge(tag: String = TAG) = LogUtil.e(tag, this)
