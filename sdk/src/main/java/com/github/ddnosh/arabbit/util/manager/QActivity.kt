package com.github.ddnosh.arabbit.util.manager

import android.app.Activity
import java.util.LinkedList

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
object QActivity {
    private val mActivities = LinkedList<Activity>()

    @JvmStatic
    val forwardActivity: Activity?
        @Synchronized get() = if (size() > 0) mActivities[size() - 1] else null

    @JvmStatic
    fun size(): Int = mActivities.size

    @Synchronized
    @JvmStatic
    fun addActivity(activity: Activity) {
        mActivities.add(activity)
    }

    @Synchronized
    @JvmStatic
    fun removeActivity(activity: Activity) {
        if (mActivities.contains(activity)) {
            mActivities.remove(activity)
        }
    }

    @Synchronized
    @JvmStatic
    fun clear() {
        var i = mActivities.size - 1
        while (i > -1) {
            val activity = mActivities[i]
            removeActivity(activity)
            activity.finish()
            i = mActivities.size
            i--
        }
    }

    @Synchronized
    @JvmStatic
    fun clearToTop() {
        var i = mActivities.size - 2
        while (i > -1) {
            val activity = mActivities[i]
            removeActivity(activity)
            activity.finish()
            i = mActivities.size - 1
            i--
        }
    }
}
