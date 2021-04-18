package com.github.ddnosh.arabbit.function.exception

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
enum class Error(private val errorCode: Int, private val errorString: String) {
    SUCCESS(0, "请求成功"),
    //for http exception
    UNAUTHORIZED(401, "当前请求需要用户验证"),
    FORBIDDEN(403, "服务器已经理解请求，但是拒绝执行它"),
    NOT_FOUND(404, "服务器异常，请稍后再试"),
    REQUEST_TIMEOUT(408, "请求超时"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误"),
    UNRECOGNIZED_REQUEST(501, "服务器无法识别请求方法"),
    BAD_GATEWAY(502, "服务器接收到远端服务器的错误响应"),
    SERVICE_UNAVAILABLE(503, "服务器维护或者过载，服务器当前无法处理请求"),
    GATEWAY_TIMEOUT(504, "服务器没有从远端服务器得到及时的响应"),
    UNSUPPORTED_HTTP_VERSION(505, "HTTP 版本不受支持"),

    NO_NETWORK(1001, "设备当前未联网，请检查网络设置"),
    CONNECTION_ERROR(1002, "网络连接异常，请检查网络"),
    SSL_ERROR(1003, "证书出错，请稍后再试"),
    TIMEOUT(1003, "服务器响应超时"),

    PARSE_ERROR(1100, "解析错误"),

    RUN_TIME(1200, "很抱歉,程序运行出现了错误"),
    UNKNOWN(1000, "未知错误");


    fun getValue(): String {
        return errorString
    }

    fun getKey(): Int {
        return errorCode
    }
}
