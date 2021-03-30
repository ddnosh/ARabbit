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

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.github.ddnosh.arabbit.R
import com.github.ddnosh.arabbit.module.exception.ExceptionEngine
import com.github.ddnosh.arabbit.ui.dialog.ViewHolder
import com.github.ddnosh.arabbit.ui.dialog.dialogactivity.ADialog
import com.github.ddnosh.arabbit.ui.dialog.dialogactivity.BaseDialog
import com.github.ddnosh.arabbit.util.LogUtil
import com.github.ddnosh.arabbit.util.ToastUtil
import com.github.ddnosh.arabbit.util.immersion.StatusBarUtil
import com.github.ddnosh.arabbit.util.manager.QActivity

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
abstract class QuickActivity : AppCompatActivity() {

    companion object {
        @JvmField
        var TAG = "QuickActivity"
    }

    protected lateinit var mContext: Context

    /**
     * default toolbar
     */
    protected lateinit var tvTitle: TextView
    protected lateinit var tvRight: TextView
    protected lateinit var toolbar: Toolbar
    protected lateinit var toolbarLayout: FrameLayout
    private lateinit var contentView: LinearLayout

    /**
     * dialog
     */
    private var mLoadingDialog: ADialog? = null

    /**
     * default title bar
     *
     * @return
     */
    protected abstract val isLoadDefaultTitleBar: Boolean

    /**
     * bind layout resource file
     *
     * @return id of layout resource
     */
    protected abstract val contentViewLayoutID: Int

    /**
     * get the overridePendingTransition mode
     */
    protected abstract val overridePendingTransitionMode: TransitionMode

    /**
     * overridePendingTransition mode
     */
    enum class TransitionMode {
        LEFT, RIGHT, TOP, BOTTOM, SCALE, FADE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        // activity manager
        QActivity.addActivity(this)
        // animation
        if (toggleOverridePendingTransition()) {
            when (overridePendingTransitionMode) {
                TransitionMode.LEFT -> overridePendingTransition(R.anim.left_in, R.anim.left_out)
                TransitionMode.RIGHT -> overridePendingTransition(R.anim.right_in, R.anim.right_out)
                TransitionMode.TOP -> overridePendingTransition(R.anim.top_in, R.anim.top_out)
                TransitionMode.BOTTOM -> overridePendingTransition(R.anim.bottom_in, R.anim.bottom_out)
                TransitionMode.SCALE -> overridePendingTransition(R.anim.scale_in, R.anim.scale_out)
                TransitionMode.FADE -> overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            }
        }
        // extras
        val extras = intent.extras
        extras?.let {
            getBundleExtras(it)
        }
        // layout
        val layoutId = contentViewLayoutID
        if (layoutId != 0) {
            setContentView(layoutId)
        } else {
            throw IllegalArgumentException("You must return a right contentView layout resource Id")
        }
        // system status bar immersion
        StatusBarUtil.setRootViewFitsSystemWindows(this, true)
        StatusBarUtil.setTranslucentStatus(this)
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            StatusBarUtil.setStatusBarColor(this, 0x55000000)
        }
        // init view & event
        initViewsAndEvents(savedInstanceState)
    }

    override fun setContentView(layoutResID: Int) {
        // 添加toolbar布局
        if (isLoadDefaultTitleBar) {
            initToolBarView()
            initContentView()
            contentView.addView(toolbarLayout, ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT))
            LayoutInflater.from(this).inflate(layoutResID, contentView, true)
            super.setContentView(contentView, ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT))
        } else {
            super.setContentView(layoutResID)
        }
    }

    private fun initContentView() {
        contentView = LinearLayout(this)
        contentView.orientation = LinearLayout.VERTICAL
    }

    private fun initToolBarView() {
        toolbarLayout = FrameLayout(this)
        LayoutInflater.from(this).inflate(R.layout.layout_toolbar, toolbarLayout,
                true)
        tvTitle = toolbarLayout.findViewById(R.id.tv_title)
        tvRight = toolbarLayout.findViewById(R.id.tv_right)
        toolbar = toolbarLayout.findViewById(R.id.toolbar)
        toolbar.title = ""

        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        initToolBarSet(actionBar)
    }

    private fun initToolBarSet(actionBar: ActionBar?) {
        actionBar?.run {
            setDisplayShowTitleEnabled(false)
            setDisplayShowHomeEnabled(true)
            setDisplayHomeAsUpEnabled(true)
            setDisplayUseLogoEnabled(false)
            setHomeAsUpIndicator(R.drawable.icon_back)
            toolbar.setNavigationOnClickListener { finish() }
        }
    }

    override fun finish() {
        super.finish()
        QActivity.removeActivity(this)
        if (toggleOverridePendingTransition()) {
            when (overridePendingTransitionMode) {
                TransitionMode.LEFT -> overridePendingTransition(R.anim.left_in, R.anim.left_out)
                TransitionMode.RIGHT -> overridePendingTransition(R.anim.right_in, R.anim.right_out)
                TransitionMode.TOP -> overridePendingTransition(R.anim.top_in, R.anim.top_out)
                TransitionMode.BOTTOM -> overridePendingTransition(R.anim.bottom_in, R.anim.bottom_out)
                TransitionMode.SCALE -> overridePendingTransition(R.anim.scale_in, R.anim.scale_out)
                TransitionMode.FADE -> overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        dismissLoadingDialog()
    }

    /**
     * get bundle data
     *
     * @param extras
     */
    protected abstract fun getBundleExtras(extras: Bundle)

    /**
     * init all views and add events
     */
    protected abstract fun initViewsAndEvents(savedInstanceState: Bundle?)

    /**
     * toggle overridePendingTransition
     *
     * @return
     */
    protected abstract fun toggleOverridePendingTransition(): Boolean

    /**
     * 会被子类覆盖去封装 跳转到不同的activity 或者fragment的页面
     *
     * @param clazz
     * @return
     */
    protected open fun getGoIntent(clazz: Class<*>): Intent {
        return Intent(this, clazz)
    }

    /**
     * startActivity
     *
     * @param clazz
     */
    protected fun readyGo(clazz: Class<*>) {
        val intent = getGoIntent(clazz)
        startActivity(intent)
    }

    /**
     * startActivity with  flag
     * @param clazz
     * @param flag
     */
    protected fun readyGo(clazz: Class<*>, flag: Int) {
        val intent = getGoIntent(clazz)
        intent.flags = flag
        startActivity(intent)
    }

    /**
     * startActivity with bundle
     *
     * @param clazz
     * @param bundle
     */
    protected fun readyGo(clazz: Class<*>, bundle: Bundle?) {
        val intent = getGoIntent(clazz)
        if (null != bundle) {
            intent.putExtras(bundle)
        }
        startActivity(intent)
    }

    /**
     * startActivity with bundle and flag
     * @param clazz
     * @param bundle
     * @param flag
     */
    protected fun readyGo(clazz: Class<*>, bundle: Bundle?, flag: Int) {
        val intent = getGoIntent(clazz)
        if (null != bundle) {
            intent.putExtras(bundle)
        }
        intent.flags = flag
        startActivity(intent)
    }

    /**
     * startActivity then finish
     *
     * @param clazz
     */
    protected fun readyGoThenKill(clazz: Class<*>) {
        val intent = getGoIntent(clazz)
        startActivity(intent)
        finish()
    }

    /**
     * startActivity with bundle then finish
     *
     * @param clazz
     * @param bundle
     */
    protected fun readyGoThenKill(clazz: Class<*>, bundle: Bundle?) {
        val intent = getGoIntent(clazz)
        if (null != bundle) {
            intent.putExtras(bundle)
        }
        startActivity(intent)
        finish()
    }

    /**
     * startActivityForResult
     *
     * @param clazz
     * @param requestCode
     */
    protected fun readyGoForResult(clazz: Class<*>, requestCode: Int) {
        val intent = getGoIntent(clazz)
        startActivityForResult(intent, requestCode)
    }

    /**
     * startActivityForResult with bundle
     *
     * @param clazz
     * @param requestCode
     * @param bundle
     */
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
                    //相同的上下文，无需重新创建
                    if (mContext === this@QuickActivity) {
                        mLoadingDialog!!.show()
                    } else {
                        mLoadingDialog!!.dismiss()
                        mLoadingDialog = ADialog(mContext)
                                .setDialogLayout(R.layout.dialog_loading)
                                .setConvertListener(object : BaseDialog.ViewConvertListener {
                                    override fun convertView(holder: ViewHolder, dialog: BaseDialog) {
                                        holder.setText(R.id.tip, "正在努力加载...");
                                    }
                                })
                        mLoadingDialog!!.show()
                    }
                } ?: mLoadingDialog.run {
                    mLoadingDialog = ADialog(mContext)
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
        LogUtil.e(TAG, "error:" + apiException.message!!)
        ToastUtil.showToast(apiException.message)
    }
}
