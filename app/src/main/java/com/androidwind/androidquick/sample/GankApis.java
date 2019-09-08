package com.androidwind.androidquick.sample;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public interface GankApis {

    @GET("day/history")
    Observable<GankRes<List<String>>> getHistoryDate();

    @GET("https://api.bintray.com/packages/ddnosh/maven/androidquick/images/download.svg")
    Observable<String> getSdkVersion();
}
