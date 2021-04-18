package com.github.ddnosh.arabbit.sample.jetpack.livedata

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.github.ddnosh.arabbit.sample.R
import com.github.ddnosh.arabbit.sample.base.BaseActivity
import com.github.ddnosh.arabbit.sample.jetpack.livedata.singelivedata.AFragment
import com.github.ddnosh.arabbit.sample.jetpack.livedata.singelivedata.BFragment
import com.github.ddnosh.arabbit.ui.base.QuickActivity
import com.github.ddnosh.arabbit.util.LogUtil
import kotlinx.android.synthetic.main.activity_livedata.*

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
class LiveDataActivity : BaseActivity() {
    private val TAG = "LiveDataActivity"
    private val mViewModel: LiveDataViewModel by viewModels()
    override val contentViewLayoutID: Int = R.layout.activity_livedata

    override fun initViewsAndEvents(savedInstanceState: Bundle?) {
        mViewModel.liveData.observe(this, {
            LogUtil.d(TAG, it)
        })

        button1.setOnClickListener {
            mViewModel.test()
        }
        button2.setOnClickListener {
            switchFragment(aFragment)
        }
        button3.setOnClickListener {
            switchFragment(bFragment)
        }
    }

    private var currentFragment: Fragment? = null
    private val aFragment = AFragment()
    private val bFragment = BFragment()

    private fun switchFragment(targetFragment: Fragment) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction();
        if (!targetFragment.isAdded) {
            if (currentFragment != null) {
                transaction.hide(currentFragment!!);
            }
            transaction.add(R.id.main_fragment, targetFragment)
            transaction.commit()
        } else {
            currentFragment?.let {
                transaction
                        .hide(it)
                        .show(targetFragment)
                        .commit()
            }
        }
        currentFragment = targetFragment
    }
}