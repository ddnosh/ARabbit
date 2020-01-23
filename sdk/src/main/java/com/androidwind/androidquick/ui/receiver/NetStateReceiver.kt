package com.androidwind.androidquick.ui.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter

import com.androidwind.androidquick.util.LogUtil
import com.androidwind.androidquick.util.NetUtil
import com.androidwind.androidquick.module.asynchronize.eventbus.EventCenter

import org.greenrobot.eventbus.EventBus

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
class NetStateReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        mBroadcastReceiver = this@NetStateReceiver
        if (intent.action!!.equals(ANDROID_NET_CHANGE_ACTION, ignoreCase = true) || intent.action!!.equals(CUSTOM_ANDROID_NET_CHANGE_ACTION, ignoreCase = true)) {
            if (!NetUtil.isNetworkAvailable(context)) {
                LogUtil.i(TAG, "<--- network disconnected --->")
                isNetworkAvailable = false
                EventBus.getDefault().post(EventCenter<Any>(10001))
            } else {
                LogUtil.i(TAG, "<--- network connected --->")
                isNetworkAvailable = true
                apnType = NetUtil.getAPNType(context)
                EventBus.getDefault().post(EventCenter<Any>(10002))
            }
        }
    }

    companion object {

        private val TAG = "NetStateReceiver"
        private val CUSTOM_ANDROID_NET_CHANGE_ACTION = "com.androidwind.androidquick.net.conn.CONNECTIVITY_CHANGE"
        private val ANDROID_NET_CHANGE_ACTION = "android.net.conn.CONNECTIVITY_CHANGE"

        var isNetworkAvailable = false
            private set
        var apnType: NetUtil.NetType? = null
            private set
        private var mBroadcastReceiver: BroadcastReceiver? = null

        private val receiver: BroadcastReceiver?
            get() {
                if (null == mBroadcastReceiver) {
                    synchronized(NetStateReceiver::class.java) {
                        if (null == mBroadcastReceiver) {
                            mBroadcastReceiver = NetStateReceiver()
                        }
                    }
                }
                return mBroadcastReceiver
            }

        fun registerNetworkStateReceiver(mContext: Context) {
            val filter = IntentFilter()
            filter.addAction(CUSTOM_ANDROID_NET_CHANGE_ACTION)
            filter.addAction(ANDROID_NET_CHANGE_ACTION)
            mContext.applicationContext.registerReceiver(receiver, filter)
        }

        fun checkNetworkState(mContext: Context) {
            val intent = Intent()
            intent.action = CUSTOM_ANDROID_NET_CHANGE_ACTION
            mContext.sendBroadcast(intent)
        }

        fun unRegisterNetworkStateReceiver(mContext: Context) {
            mBroadcastReceiver?.let {
                try {
                    mContext.applicationContext.unregisterReceiver(it)
                } catch (e: Exception) {
                    LogUtil.e(TAG, e.message!!)
                }
            }
        }
    }
}