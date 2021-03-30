package com.github.ddnosh.arabbit.util

import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method

/**
 * @author    ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
object ReflectUtil {
    private val TAG = "GaramutHelperReflect"
    val LOG_FAIL_REASON = "fail_reason:"

    @JvmStatic
    fun getClass(className: String): Class<*>? {
        try {
            val cls = Class.forName(className)
            LogUtil.d(TAG, "getClass->cls:$cls")

            return cls
        } catch (e: ClassNotFoundException) {
            LogUtil.d(TAG, "getClass->className:" + className
                + "," + LOG_FAIL_REASON + e)
            e.printStackTrace()
            return null
        }
    }

    @JvmStatic
    fun getClass(cls: Class<*>, className: String): Class<*>? {
        var classes = cls.classes
        for (clas in classes) {
            LogUtil.d(TAG, "getClass->getClasses $clas")
            if (clas.name == className) {
                return clas
            }
        }
        classes = cls.declaredClasses
        for (clas in classes) {
            LogUtil.d(TAG, "getClass->getDeclaredClasses $clas")
            if (clas.name == className) {
                return clas
            }
        }
        return null
    }

    @JvmStatic
    fun getMethod(cls: Class<*>, funcName: String): Method? {
        var methods = cls.declaredMethods
        LogUtil.d(TAG, "getMethod->getMethodsDeclared $methods")
        for (method in methods) {
            // GaramutHelperLog.log(TAG, "getMethod", "getMethodDeclared " +
            // method.toString());
            if (method.name == funcName) {
                return method
            }
        }

        methods = cls.methods
        LogUtil.d(TAG, "getMethod->getMethodsBase $methods")
        for (method in methods) {
            // GaramutHelperLog.log(TAG, "getMethod", "getMethodBase " +
            // method.toString());
            if (method.name == funcName) {
                return method
            }
        }

        LogUtil.d(TAG, "getMethod->fail")
        return null
    }

    @JvmStatic
    fun getMethod(
        owner: Class<*>, funcName: String,
        vararg paramTypes: Class<*>
    ): Method? {
        try {
            return owner.getDeclaredMethod(funcName, *paramTypes)
        } catch (e: NoSuchMethodException) {
            LogUtil.d(TAG, "getMethod->fail $LOG_FAIL_REASON$e")
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }

    @JvmStatic
    fun getObject(className: String): Any? {

        return getObject(getClass(className))
    }

    @JvmStatic
    fun getObject(cls: Class<*>?): Any? {
        try {
            return cls!!.newInstance()
        } catch (e: InstantiationException) {
            e.printStackTrace()
            LogUtil.d(TAG, "getObject->fail $LOG_FAIL_REASON$e")
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
            LogUtil.d(TAG, "getObject->fail $LOG_FAIL_REASON$e")
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
            LogUtil.d(TAG, "getObject->fail $LOG_FAIL_REASON$e")
        } catch (e: Exception) {
            e.printStackTrace()
            LogUtil.d(TAG, "getObject->fail $LOG_FAIL_REASON$e")
        }

        return null
    }

    @JvmStatic
    operator fun invoke(owner: Any, method: Method, vararg params: Any): Any? {
        try {
            return method.invoke(owner, *params)
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: InvocationTargetException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }
}