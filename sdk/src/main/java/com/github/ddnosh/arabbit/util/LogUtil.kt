package com.github.ddnosh.arabbit.util

import android.util.Log

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
object LogUtil {

    val TAG = "LogUtil"
    val LOG_LEVEL = Log.VERBOSE

    private var logOn = true

    @JvmStatic
    fun dumpException(t: Throwable) {
        if (isLoggable(Log.WARN)) {
            val maxLen = 256
            val err = StringBuilder(maxLen)

            err.append("Got exception: ")
            err.append(t.toString())
            err.append("\n")

            println(err.toString())
            t.printStackTrace(System.out)
        }
    }

    @JvmStatic
    fun v(aTag: String, aMsg: String) {
        log(Log.VERBOSE, aTag, aMsg)
    }

    @JvmStatic
    fun v(aTag: String, aMsg: String, aThrowable: Throwable) {
        log(Log.VERBOSE, aTag, aMsg, aThrowable)
    }

    @JvmStatic
    fun d(aTag: String, aMsg: String) {
        log(Log.DEBUG, aTag, aMsg)
    }

    @JvmStatic
    fun d(aTag: String, aMsg: String, aThrowable: Throwable) {
        log(Log.DEBUG, aTag, aMsg, aThrowable)
    }

    @JvmStatic
    fun i(aTag: String, aMsg: String) {
        log(Log.INFO, aTag, aMsg)
    }

    @JvmStatic
    fun i(aTag: String, aMsg: String, aThrowable: Throwable) {
        log(Log.INFO, aTag, aMsg, aThrowable)
    }

    @JvmStatic
    fun w(aTag: String, aMsg: String) {
        log(Log.WARN, aTag, aMsg)
    }

    @JvmStatic
    fun w(aTag: String, aMsg: String, aThrowable: Throwable) {
        log(Log.WARN, aTag, aMsg, aThrowable)
    }

    @JvmStatic
    fun e(aTag: String, aMsg: String) {
        log(Log.ERROR, aTag, aMsg)
    }

    @JvmStatic
    fun e(aTag: String, aMsg: String, aThrowable: Throwable) {
        log(Log.ERROR, aTag, aMsg, aThrowable)
    }

    /**
     * log Send a logLevel log message and log the exception, then collect the log entry.
     *
     * @param aLogLevel  Used to identify log level/
     * @param aTag       Used to identify the source of a log message. It usually identifies the class or activity
     * where the
     * log call occurs.
     * @param aMessage   The message you would like logged.
     * @param aThrowable An exception to log
     */
    @JvmOverloads
    @JvmStatic
    fun log(aLogLevel: Int, aTag: String, aMessage: String, aThrowable: Throwable? = null) {
        if (logOn && isLoggable(aLogLevel)) {
            when (aLogLevel) {
                Log.VERBOSE -> Log.v(TAG, "$aTag: $aMessage", aThrowable)
                Log.DEBUG -> Log.d(TAG, "$aTag: $aMessage", aThrowable)
                Log.INFO -> Log.i(TAG, "$aTag: $aMessage", aThrowable)
                Log.WARN -> Log.w(TAG, "$aTag: $aMessage", aThrowable)
                Log.ERROR -> Log.e(TAG, "$aTag: $aMessage", aThrowable)
                else -> Log.e(TAG, "$aTag: $aMessage", aThrowable)
            }
        }
    }

    /**
     * call when enter the method body that you want to debug with only one line
     */
    @JvmStatic
    fun method() {
        val stack = Throwable().stackTrace
        if (null == stack || 2 > stack.size) {
            return
        }

        val s = stack[1]
        if (null != s) {
            val className = s.className
            val methodName = s.methodName
            d(className, "+++++$methodName")
        }
    }

    /**
     * call when enter the method body that you want to debug.
     */
    @JvmStatic
    fun enter() {
        val stack = Throwable().stackTrace
        if (null == stack || 2 > stack.size) {
            return
        }

        val s = stack[1]
        if (null != s) {
            val className = s.className
            val methodName = s.methodName
            d(className, "====>$methodName")
        }
    }

    /**
     * call when leave the method body that you want to debug.
     */
    @JvmStatic
    fun leave() {
        val stack = Throwable().stackTrace
        if (null == stack || 2 > stack.size) {
            return
        }

        val s = stack[1]
        if (null != s) {
            val className = s.className
            val methodName = s.methodName
            d(className, "<====$methodName")
        }
    }

    /**
     * Checks to see whether or not a log for the specified tag is loggable at the specified level.
     *
     * @param aLevel The level to check.
     *
     * @return Whether or not that this is allowed to be logged.
     */
    @JvmStatic
    fun isLoggable(aLevel: Int): Boolean {

        return aLevel >= LOG_LEVEL
    }

    @JvmStatic
    fun setLogOn(logOn: Boolean) {
        LogUtil.logOn = logOn
    }
}