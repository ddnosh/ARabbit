package com.github.ddnosh.arabbit.sample.function.error

import android.os.Bundle
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.github.ddnosh.arabbit.function.exception.ExceptionEngine
import com.github.ddnosh.arabbit.sample.base.BaseFragment
import com.github.ddnosh.arabbit.sample.databinding.FragmentBlankBinding
import com.github.ddnosh.arabbit.util.LogUtil
import java.lang.RuntimeException

class ExceptionFragment : BaseFragment() {

    private val binding by binding<FragmentBlankBinding>()

    override fun attachViewBinding(viewContainer: ViewGroup?): ViewBinding {
        return binding
    }

    override fun initViewsAndEvents(savedInstanceState: Bundle?) {
        try {
            makeError()
        } catch (e: RuntimeException) {
            val exception = ExceptionEngine.handleException(e)
            LogUtil.e(TAG, exception.errorMessage)
        }
    }

    @Throws(RuntimeException::class)
    private fun makeError() {
        throw RuntimeException("view must have a tag")
    }
}
