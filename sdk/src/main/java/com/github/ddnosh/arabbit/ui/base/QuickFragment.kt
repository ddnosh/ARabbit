package com.github.ddnosh.arabbit.ui.base

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.ddnosh.arabbit.util.StringUtil
import com.google.android.material.snackbar.Snackbar

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
abstract class QuickFragment : Fragment() {

    companion object {
        @JvmField
        var TAG = "QuickFragment"
    }

    open var isUseViewBinding = false

    protected lateinit var mContext: Context

    private var isFirstResume = true
    private var isFirstVisible = true
    private var isFirstInvisible = true
    private var isPrepared: Boolean = false

    open val contentViewLayoutID: Int = 0

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        mContext = activity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return if (isUseViewBinding) {
            initViewBinding(container)
        } else {
            if (contentViewLayoutID != 0) {
                initContentView(contentViewLayoutID, inflater, container)
            } else {
                super.onCreateView(inflater, container, savedInstanceState)
            }
        }
    }

    open fun initContentView(layoutId: Int, inflater: LayoutInflater, container: ViewGroup?): View {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewsAndEvents(savedInstanceState)
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

    open fun initViewBinding(viewContainer: ViewGroup?): View? {
        return null
    }

    /**
     * when fragment is visible for the first time, here we can do some initialized work or refresh data only once
     */
    protected abstract fun onFirstUserVisible()

    protected abstract fun onUserVisible()

    private fun onFirstUserInvisible() {
        // here we do not recommend do something
    }

    protected abstract fun onUserInvisible()

    protected abstract fun initViewsAndEvents(savedInstanceState: Bundle?)

    protected open fun getGoIntent(clazz: Class<*>): Intent {
        return if (QuickFragment::class.java.isAssignableFrom(clazz)) {
            val intent = Intent(activity, FrameActivity::class.java)
            intent.putExtra("fragmentName", clazz.name)
            intent
        } else {
            Intent(activity, clazz)
        }
    }

    protected fun readyGo(clazz: Class<*>) {
        val intent = getGoIntent(clazz)
        startActivity(intent)
    }

    protected fun readyGo(clazz: Class<*>, bundle: Bundle?) {
        val intent = getGoIntent(clazz)
        if (null != bundle) {
            intent.putExtras(bundle)
        }
        startActivity(intent)
    }

    protected fun readyGoForResult(clazz: Class<*>, requestCode: Int) {
        val intent = getGoIntent(clazz)
        startActivityForResult(intent, requestCode)
    }

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
            Snackbar.make((mContext as Activity).window.decorView, msg, Snackbar.LENGTH_SHORT)
                .show()
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

    fun showError(throwable: Throwable) {
        if (activity is QuickActivity)
            (activity as QuickActivity).showError(throwable)
    }
}
