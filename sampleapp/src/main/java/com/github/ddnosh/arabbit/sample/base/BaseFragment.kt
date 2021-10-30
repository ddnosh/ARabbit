package com.github.ddnosh.arabbit.sample.base

import android.app.Activity
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.github.ddnosh.arabbit.BaseApplication
import com.github.ddnosh.arabbit.ui.base.QuickFragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
abstract class BaseFragment : QuickFragment(), HasSupportFragmentInjector {

    override var isUseViewBinding = true

    abstract fun attachViewBinding(viewContainer: ViewGroup?): ViewBinding
    inline fun <reified T : ViewBinding> binding() = lazy {
        T::class.java.getMethod("inflate", LayoutInflater::class.java)
            .invoke(null, layoutInflater) as T
    }

    override fun initViewBinding(viewContainer: ViewGroup?): View {
        return attachViewBinding(viewContainer).root
    }

    // application's viewModel
    inline fun <reified T : ViewModel> viewModelApplication() =
        lazy {
            (requireActivity().application as? BaseApplication).let {
                it?.getAppViewModelProvider()?.get(T::class.java)
                    ?: throw NullPointerException("Your application should extends BaseApplication")
            }
        }

    // activity's viewModel
    @Inject
    lateinit var viewModelFactoryActivity: ViewModelProvider.Factory
    inline fun <reified T : ViewModel> viewModelActivity() =
        lazy {
            activity?.let {
                ViewModelProvider(
                    it,
                    viewModelFactoryActivity
                ).get(T::class.java)
            }
        }

    // fragment's viewModel
    @Inject
    lateinit var viewModelFactoryFragment: ViewModelProvider.Factory
    inline fun <reified T : ViewModel> viewModelFragment() =
        lazy { ViewModelProvider(this, viewModelFactoryFragment).get(T::class.java) }

    override fun onFirstUserVisible() {}
    override fun onUserVisible() {}
    override fun onUserInvisible() {}

    @Inject
    lateinit var childFragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return childFragmentInjector
    }

    @SuppressWarnings("deprecation")
    override fun onAttach(activity: Activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            // Perform injection here before M, L (API 22) and below because onAttach(Context)
            // is not yet available at L.
            AndroidSupportInjection.inject(this)
        }
        super.onAttach(activity)
    }

    override fun onAttach(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // Perform injection here for M (API 23) due to deprecation of onAttach(Activity).
            AndroidSupportInjection.inject(this)
        }
        super.onAttach(context)
    }
}
