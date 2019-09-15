package com.androidwind.androidquick.module.glide.support;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public interface ImageListener<T> {

    void onSuccess(T result);

    void onFail(Throwable throwable);
}
