package com.androidwind.androidquick.sample

import io.reactivex.Observable
import retrofit2.http.GET

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
interface GankApis {
    @get:GET("day/history")
    val historyDate: Observable<GankRes<List<String?>?>?>?

    @get:GET("https://api.bintray.com/packages/ddnosh/maven/androidquick/images/download.svg")
    val sdkVersion: Observable<String?>?
}