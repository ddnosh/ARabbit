package com.github.ddnosh.arabbit.sample.base

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import com.github.ddnosh.arabbit.ui.base.FrameActivity
import com.github.ddnosh.arabbit.ui.base.QuickActivity
import com.trello.lifecycle2.android.lifecycle.AndroidLifecycle
import com.trello.rxlifecycle3.LifecycleProvider

/**
 * 通用抽象方法的实现集合
 *
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
abstract class BaseActivity: QuickActivity() {
    companion object {
        @JvmField
        var TAG = "BaseActivity"
    }
}