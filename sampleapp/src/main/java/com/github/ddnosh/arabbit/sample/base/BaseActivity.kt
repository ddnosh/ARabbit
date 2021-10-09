package com.github.ddnosh.arabbit.sample.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.viewbinding.ViewBinding
import com.github.ddnosh.arabbit.ui.base.QuickActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasFragmentInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * 通用抽象方法的实现集合
 *
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
abstract class BaseActivity : QuickActivity(), HasFragmentInjector, HasSupportFragmentInjector {
    companion object {
        @JvmField
        var TAG = "BaseActivity"
    }

    override var isUseViewBinding = true

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    inline fun <reified T : ViewModel> viewModel() =
        lazy { ViewModelProviders.of(this, viewModelFactory).get(T::class.java) }

    abstract fun attachViewBinding(): ViewBinding
    inline fun <reified T : ViewBinding> binding() = lazy {
        T::class.java.getMethod("inflate", LayoutInflater::class.java)
            .invoke(null, layoutInflater) as T
    }
    override fun initViewBinding() {
        setContentView(attachViewBinding().root)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    @Inject
    lateinit var supportFragmentInjector: DispatchingAndroidInjector<Fragment>
    @Inject
    lateinit var frameworkFragmentInjector: DispatchingAndroidInjector<android.app.Fragment>
    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return supportFragmentInjector
    }

    override fun fragmentInjector(): AndroidInjector<android.app.Fragment> {
        return frameworkFragmentInjector
    }
}
