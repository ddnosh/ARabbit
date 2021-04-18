package com.github.ddnosh.arabbit.sample.module.network.coroutine.data
import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class UserInfo(var admin: Boolean = false,
                    var chapterTops: List<String> = listOf(),
                    var collectIds: MutableList<String> = mutableListOf(),
                    var email: String="",
                    var icon: String="",
                    var id: String="",
                    var nickname: String="",
                    var password: String="",
                    var token: String="",
                    var type: Int =0,
                    var username: String="") : Parcelable
