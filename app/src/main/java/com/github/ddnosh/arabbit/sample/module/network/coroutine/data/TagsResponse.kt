package com.github.ddnosh.arabbit.sample.module.network.coroutine.data

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
/**
 * 文章的标签
 */
@SuppressLint("ParcelCreator")
@Parcelize
data class TagsResponse(var name:String, var url:String): Parcelable
