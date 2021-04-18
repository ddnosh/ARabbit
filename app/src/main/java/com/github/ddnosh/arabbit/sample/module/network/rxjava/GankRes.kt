package com.github.ddnosh.arabbit.sample.module.network.rxjava

import java.io.Serializable

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
class GankRes<T> : Serializable {
    var isError = false
    var results: T? = null
}