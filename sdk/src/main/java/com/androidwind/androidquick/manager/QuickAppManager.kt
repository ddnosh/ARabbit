package com.androidwind.androidquick.manager

import android.app.Activity

import java.util.LinkedList

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
object QuickAppManager {
    private val mActivities = LinkedList<Activity>()

    val forwardActivity: Activity?
        @Synchronized get() = if (size() > 0) mActivities[size() - 1] else null

    fun size(): Int {
        return mActivities.size
    }

    @Synchronized
    fun addActivity(activity: Activity) {
        mActivities.add(activity)
    }

    @Synchronized
    fun removeActivity(activity: Activity) {
        if (mActivities.contains(activity)) {
            mActivities.remove(activity)
        }
    }

    @Synchronized
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
