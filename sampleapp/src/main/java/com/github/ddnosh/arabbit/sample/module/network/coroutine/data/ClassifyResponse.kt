package com.github.ddnosh.arabbit.sample.module.network.coroutine.data

import android.annotation.SuppressLint
import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

/**
 * 项目分类
 */
@SuppressLint("ParcelCreator")
@Keep
@Parcelize
data class ClassifyResponse(
    var children: List<String> = listOf(),
    var courseId: Int = 0,
    var id: Int = 0,
    var name: String = "",
    var order: Int = 0,
    var parentChapterId: Int = 0,
    var userControlSetTop: Boolean = false,
    var visible: Int = 0
) : Parcelable
