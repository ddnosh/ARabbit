package com.androidwind.androidquick.ui.base

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import java.lang.reflect.Field
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import butterknife.ButterKnife
import butterknife.Unbinder
import com.androidwind.androidquick.util.StringUtil
import com.androidwind.androidquick.ui.dialog.dialogactivity.CommonDialog
import com.androidwind.androidquick.module.asynchronize.eventbus.EventCenter
import com.androidwind.androidquick.ui.viewstatus.VaryViewHelperController
import com.google.android.material.snackbar.Snackbar

import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
abstract class QuickFragment : Fragment() {

    protected lateinit var mContext: Context

    private var isFirstResume = true
    private var isFirstVisible = true
    private var isFirstInvisible = true
    private var isPrepared: Boolean = false

    private var mVaryViewHelperController: VaryViewHelperController? = null

    /**
     * butterknife 8+ support
     */
    private var mUnbinder: Unbinder? = null

    /**
     * databinding
     */
    lateinit var mainLayout: View
    protected var binding: ViewDataBinding? = null

    /**
     * bind eventbus
     *
     * @return
     */
    protected abstract val isBindEventBus: Boolean

    /**
     * bind layout resource file
     *
     * @return id of layout resource
     */
    protected abstract val contentViewLayoutID: Int

    /**
     * get the support fragment manager
     *
     * @return
     */
    protected val supportFragmentManager: FragmentManager
        get() = activity!!.supportFragmentManager

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        mContext = activity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (isBindEventBus) {
            EventBus.getDefault().register(this)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layoutId = contentViewLayoutID
        if (layoutId != 0) {
            try {
                binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
                if (binding != null) {
                    mainLayout = binding!!.root
                } else {
                    mainLayout = inflater.inflate(layoutId, container, false)
                }
            } catch (e: NoClassDefFoundError) {
                mainLayout = inflater.inflate(layoutId, container, false)
            }

            mainLayout.isClickable = true
            return mainLayout
        } else {
            return super.onCreateView(inflater, container, savedInstanceState)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mUnbinder = ButterKnife.bind(this, view)

        if (null != setDefaultVaryViewRoot()) {
            mVaryViewHelperController = VaryViewHelperController(setDefaultVaryViewRoot())
        }

        initViewsAndEvents(savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
        mUnbinder?.unbind()
        if (isBindEventBus) {
            EventBus.getDefault().unregister(this)
        }
    }

    override fun onDetach() {
        super.onDetach()
        // for bug ---> java.lang.IllegalStateException: Activity has been destroyed
        // not necessary for androidx any more
        // try {
        //     Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
        //     childFragmentManager.setAccessible(true);
        //     childFragmentManager.set(this, null);
        //
        // } catch (NoSuchFieldException e) {
        //     throw new RuntimeException(e);
        // } catch (IllegalAccessException e) {
        //     throw new RuntimeException(e);
        // }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initPrepare()
    }

    override fun onResume() {
        super.onResume()
        if (isFirstResume) {
            isFirstResume = false
            return
        }
        if (userVisibleHint) {
            onUserVisible()
        }
    }

    override fun onPause() {
        super.onPause()
        if (userVisibleHint) {
            onUserInvisible()
        }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            if (isFirstVisible) {
                isFirstVisible = false
                initPrepare()
            } else {
                onUserVisible()
            }
        } else {
            if (isFirstInvisible) {
                isFirstInvisible = false
                onFirstUserInvisible()
            } else {
                onUserInvisible()
            }
        }
    }

    @Synchronized
    private fun initPrepare() {
        if (isPrepared) {
            onFirstUserVisible()
        } else {
            isPrepared = true
        }
    }

    /**
     * when fragment is visible for the first time, here we can do some initialized work or refresh data only once
     */
    protected abstract fun onFirstUserVisible()

    /**
     * this method like the fragment's lifecycle method onResume()
     */
    protected abstract fun onUserVisible()

    /**
     * when fragment is invisible for the first time
     */
    private fun onFirstUserInvisible() {
        // here we do not recommend do something
    }

    /**
     * this method like the fragment's lifecycle method onPause()
     */
    protected abstract fun onUserInvisible()

    /**
     * get loading target view
     */
    protected abstract fun setDefaultVaryViewRoot(): View?

    /**
     * init all views and add events
     */
    protected abstract fun initViewsAndEvents(savedInstanceState: Bundle?)

    /**
     * when event comming
     *
     * @param eventCenter
     */
    protected abstract fun onEventComing(eventCenter: EventCenter<*>)

    protected open fun getGoIntent(clazz: Class<*>): Intent {
        return Intent(activity, clazz)
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
     * startActivityForResult
     *
     * @param clazz         类名.class，获取类型类
     * @param requestCode   请求码
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
        val intent = getGoIntent(clazz)
        if (null != bundle) {
            intent.putExtras(bundle)
        }
        startActivityForResult(intent, requestCode)
    }

    /**
     * show toast
     *
     * @param msg
     */
    protected fun showToast(msg: String?) {
        if (null != msg && !StringUtil.isEmpty(msg)) {
            Snackbar.make((mContext as Activity).window.decorView, msg, Snackbar.LENGTH_SHORT).show()
        }
    }

    /**
     * toggle show loading
     *
     * @param toggle
     */
    protected fun toggleShowLoading(toggle: Boolean, msg: String) {
        requireNotNull(mVaryViewHelperController) { "You must return a right target view for loading" }

        if (toggle) {
            mVaryViewHelperController!!.showLoading(msg)
        } else {
            mVaryViewHelperController!!.restore()
        }
    }

    /**
     * toggle show empty
     *
     * @param toggle
     */
    protected fun toggleShowEmpty(toggle: Boolean, msg: String, onClickListener: View.OnClickListener) {
        requireNotNull(mVaryViewHelperController) { "You must return a right target view for loading" }

        if (toggle) {
            mVaryViewHelperController!!.showEmpty(msg, onClickListener)
        } else {
            mVaryViewHelperController!!.restore()
        }
    }

    /**
     * toggle show error
     *
     * @param toggle
     */
    protected fun toggleShowError(toggle: Boolean, msg: String, onClickListener: View.OnClickListener) {
        requireNotNull(mVaryViewHelperController) { "You must return a right target view for loading" }

        if (toggle) {
            mVaryViewHelperController!!.showError(msg, onClickListener)
        } else {
            mVaryViewHelperController!!.restore()
        }
    }

    /**
     * toggle show network error
     *
     * @param toggle
     */
    protected fun toggleNetworkError(toggle: Boolean, onClickListener: View.OnClickListener) {
        requireNotNull(mVaryViewHelperController) { "You must return a right target view for loading" }

        if (toggle) {
            mVaryViewHelperController!!.showNetworkError(onClickListener)
        } else {
            mVaryViewHelperController!!.restore()
        }
    }

    fun onEventMainThread(eventCenter: EventCenter<*>?) {
        if (null != eventCenter) {
            onEventComing(eventCenter)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEventBus(eventCenter: EventCenter<*>?) {
        if (null != eventCenter) {
            onEventComing(eventCenter)
        }
    }

    /**
     * dialog
     */
    fun showLoadingDialog() {
        if (activity != null && activity is QuickActivity) {
            (activity as QuickActivity).showLoadingDialog()
        }
    }

    fun showLoadingDialog(tips: String) {
        if (activity != null && activity is QuickActivity) {
            (activity as QuickActivity).showLoadingDialog(tips)
        }
    }

    fun dismissLoadingDialog() {
        if (activity != null && activity is QuickActivity) {
            (activity as QuickActivity).dismissLoadingDialog()
        }
    }

    fun getDialogBuilder(context: Context): CommonDialog.Builder {
        if (activity != null && activity is QuickActivity) {
            return (activity as QuickActivity).getDialogBuilder(context)
        }
        throw IllegalStateException("CommonDialog can only be used in a class which extends QuickActivity!")
    }

    fun showError(throwable: Throwable) {
        if (activity is QuickActivity)
            (activity as QuickActivity).showError(throwable)
    }

    companion object {
        @JvmField
        var TAG = "QuickFragment"
    }
}
