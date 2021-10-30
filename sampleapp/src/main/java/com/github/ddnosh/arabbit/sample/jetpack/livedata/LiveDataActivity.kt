package com.github.ddnosh.arabbit.sample.jetpack.livedata

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.viewbinding.ViewBinding
import com.github.ddnosh.arabbit.sample.R
import com.github.ddnosh.arabbit.sample.appViewModel
import com.github.ddnosh.arabbit.sample.base.BaseActivity
import com.github.ddnosh.arabbit.sample.databinding.ActivityLivedataBinding
import com.github.ddnosh.arabbit.sample.jetpack.livedata.singelivedata.AFragment
import com.github.ddnosh.arabbit.sample.jetpack.livedata.singelivedata.BFragment
import com.github.ddnosh.arabbit.util.ToastUtil

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
class LiveDataActivity : BaseActivity() {
    private val TAG = "LiveDataActivity"

    private val mViewModel by viewModels<LiveDataViewModel>()

    private val binding by binding<ActivityLivedataBinding>()
    override fun attachViewBinding(): ViewBinding {
        return binding
    }

    override fun initViewsAndEvents(savedInstanceState: Bundle?) {
        mViewModel.liveData.observe(this, {
            ToastUtil.showToast("LiveDataActivity $it " + System.currentTimeMillis().toString())
        })

        binding.button1.setOnClickListener {
            appViewModel.info.value = System.currentTimeMillis().toString()
        }
        binding.button2.setOnClickListener {
            mViewModel.test()
        }
        binding.buttonA.setOnClickListener {
            switchFragment(aFragment)
        }
        binding.buttonB.setOnClickListener {
            switchFragment(bFragment)
        }
    }

    private var currentFragment: Fragment? = null
    private val aFragment = AFragment()
    private val bFragment = BFragment()

    private fun switchFragment(targetFragment: Fragment) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        if (!targetFragment.isAdded) {
            if (currentFragment != null) {
                transaction.hide(currentFragment!!)
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
