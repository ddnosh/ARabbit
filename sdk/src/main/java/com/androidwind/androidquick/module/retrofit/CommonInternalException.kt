package com.androidwind.androidquick.module.retrofit

import com.androidwind.androidquick.constant.Constant

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
class CommonInternalException(
    //错误消息
    var errorMsg: String?
) : RuntimeException(errorMsg) {
    //错误码
    val errorCode = Constant.ERROR_CODE_INTERNAL
}
