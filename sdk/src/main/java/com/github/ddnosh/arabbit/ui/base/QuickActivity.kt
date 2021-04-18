/**
 * @Description: Activity的一个基类, 提供丰富的功能, 旨在开发一款新APP应用的时候直接继承使用
 * @Detail:
 * 1.   默认提供六种转场动画, 如需自定义, 请在子类调用父类的onCreate后添加overridePendingTransition
 * 2.   封装页面跳转传参
 * 3.   EventBus事件总线
 * 4.   管理所有启动的activity
 * 5.   设备屏幕信息
 * 6.   监听网络状态变化
 * 7.   页面跳转: readyGo, readyGoThenKill, readyGoForResult
 * 8.   提供页面状态展示: loading, network error, error, empty
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */

package com.github.ddnosh.arabbit.ui.base

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Lifecycle
import com.github.ddnosh.arabbit.R
import com.github.ddnosh.arabbit.function.exception.ExceptionEngine
import com.github.ddnosh.arabbit.ui.dialog.ViewHolder
import com.github.ddnosh.arabbit.ui.dialog.dialogactivity.ADialog
import com.github.ddnosh.arabbit.ui.dialog.dialogactivity.BaseDialog
import com.github.ddnosh.arabbit.util.LogUtil
import com.github.ddnosh.arabbit.util.ToastUtil
import com.github.ddnosh.arabbit.util.immersion.StatusBarUtil
import com.github.ddnosh.arabbit.util.manager.QActivity
import com.trello.lifecycle2.android.lifecycle.AndroidLifecycle
import com.trello.rxlifecycle3.LifecycleProvider
import java.util.*

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
abstract class QuickActivity : AppCompatActivity() {

    companion object {
        @JvmField
        var TAG = "QuickActivity"
    }

    private var mLoadingDialog: ADialog? = null

    protected abstract val contentViewLayoutID: Int

    protected lateinit var lifecycleProvider: LifecycleProvider<Lifecycle.Event>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // activity manager
        QActivity.addActivity(this)
        // layout
        if (contentViewLayoutID != 0) {
            initContentView(contentViewLayoutID)
        } else {
            throw IllegalArgumentException("You must return a right contentView layout resource id")
        }
        // system status bar immersion
        StatusBarUtil.setRootViewFitsSystemWindows(this, true)
        StatusBarUtil.setTranslucentStatus(this)
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            StatusBarUtil.setStatusBarColor(this, 0x55000000)
        }
        // init lifecycleprovider
        lifecycleProvider = AndroidLifecycle.createLifecycleProvider(this)
        // init view & event
        initViewsAndEvents(savedInstanceState)
    }

    open fun initContentView(layoutId: Int) {
        setContentView(layoutId)
    }

    fun setSupportToolBar(toolBar: Toolbar) {
        setSupportActionBar(toolBar)
        supportActionBar?.run {
            setDisplayShowTitleEnabled(false)
            setDisplayShowHomeEnabled(true)
            setDisplayHomeAsUpEnabled(true)
            setDisplayUseLogoEnabled(false)
            setHomeButtonEnabled(true)
            setHomeAsUpIndicator(R.drawable.icon_back)
            toolBar.setNavigationOnClickListener { finish() }
        }
    }

    override fun finish() {
        super.finish()
        QActivity.removeActivity(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        dismissLoadingDialog()
    }

    protected abstract fun initViewsAndEvents(savedInstanceState: Bundle?)

    protected open fun getGoIntent(clazz: Class<*>): Intent {
        return if (QuickFragment::class.java.isAssignableFrom(clazz)) {
            val intent = Intent(this, FrameActivity::class.java)
            intent.putExtra("fragmentName", clazz.name)
            intent
        } else {
            Intent(this, clazz)
        }
    }

    protected fun readyGo(clazz: Class<*>) {
        val intent = getGoIntent(clazz)
        startActivity(intent)
    }

    protected fun readyGo(clazz: Class<*>, flag: Int) {
        val intent = getGoIntent(clazz)
        intent.flags = flag
        startActivity(intent)
    }

    protected fun readyGo(clazz: Class<*>, bundle: Bundle?) {
        val intent = getGoIntent(clazz)
        if (null != bundle) {
            intent.putExtras(bundle)
        }
        startActivity(intent)
    }

    protected fun readyGo(clazz: Class<*>, bundle: Bundle?, flag: Int) {
        val intent = getGoIntent(clazz)
        if (null != bundle) {
            intent.putExtras(bundle)
        }
        intent.flags = flag
        startActivity(intent)
    }

    protected fun readyGoThenKill(clazz: Class<*>) {
        val intent = getGoIntent(clazz)
        startActivity(intent)
        finish()
    }

    protected fun readyGoThenKill(clazz: Class<*>, bundle: Bundle?) {
        val intent = getGoIntent(clazz)
        if (null != bundle) {
            intent.putExtras(bundle)
        }
        startActivity(intent)
        finish()
    }

    protected fun readyGoForResult(clazz: Class<*>, requestCode: Int) {
        val intent = getGoIntent(clazz)
        startActivityForResult(intent, requestCode)
    }

    protected fun readyGoForResult(clazz: Class<*>, requestCode: Int, bundle: Bundle?) {
        val intent = Intent(this, clazz)
        if (null != bundle) {
            intent.putExtras(bundle)
        }
        startActivityForResult(intent, requestCode)
    }

    @JvmOverloads
    fun showLoadingDialog(tips: String? = null) {
        if (!isFinishing) {
            try {
                mLoadingDialog?.run {
                    mLoadingDialog!!.show()
                } ?: mLoadingDialog.run {
                    mLoadingDialog = ADialog(this@QuickActivity)
                            .setDialogLayout(R.layout.dialog_loading)
                            .setConvertListener(object : BaseDialog.ViewConvertListener {
                                override fun convertView(holder: ViewHolder, dialog: BaseDialog) {
                                    holder.setText(R.id.tip, "正在努力加载...");
                                }
                            })
                    mLoadingDialog!!.show()
                }
            } catch (e: Throwable) {
            }
        }
    }

    fun dismissLoadingDialog() {
        try {
            if (!isFinishing && mLoadingDialog != null && mLoadingDialog!!.isShowing) {
                mLoadingDialog!!.dismiss()
            }
        } catch (e: Throwable) {
        }
    }

    fun showError(t: Throwable) {
        val apiException = ExceptionEngine.handleException(t)
        LogUtil.e(TAG, "error:" + apiException.message)
        ToastUtil.showToast(apiException.message)
    }
}
