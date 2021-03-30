package com.github.ddnosh.arabbit.sample.mvvm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

open class BaseViewModel(app: Application) : AndroidViewModel(app) {

    protected fun launchIO(
            block: suspend (CoroutineScope) -> Unit,
            error: (suspend (Throwable) -> Unit)? = null
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                block.invoke(this)
            } catch (e: Throwable) {
                error?.invoke(e)
            }
        }
    }
}