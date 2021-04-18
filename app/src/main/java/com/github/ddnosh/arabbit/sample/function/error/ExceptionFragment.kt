package com.github.ddnosh.arabbit.sample.function.error

import android.os.Bundle
import com.github.ddnosh.arabbit.function.exception.ExceptionEngine
import com.github.ddnosh.arabbit.sample.R
import com.github.ddnosh.arabbit.sample.base.BaseFragment
import com.github.ddnosh.arabbit.util.LogUtil
import java.lang.RuntimeException

class ExceptionFragment: BaseFragment(){
    override val contentViewLayoutID: Int = R.layout.fragment_blank

    override fun initViewsAndEvents(savedInstanceState: Bundle?) {
        try {
            makeError()
        }catch (e: RuntimeException){
            val exception = ExceptionEngine.handleException(e)
            LogUtil.e(TAG, exception.errorMessage)
        }
    }

    @Throws(RuntimeException::class)
    private fun makeError() {
        throw RuntimeException("view must have a tag");
    }
}