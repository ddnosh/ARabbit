package com.androidwind.androidquick.module.retrofit

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
class CommonHttpException(//错误码
    var errorCode: Int, //错误消息
    var errorMsg: String
) : RuntimeException(errorMsg)