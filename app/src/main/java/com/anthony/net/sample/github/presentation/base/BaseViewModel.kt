package com.anthony.net.sample.github.presentation.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler

open class BaseViewModel : ViewModel() {

    protected fun getCoroutineExceptionHandler(onCoroutineException: (Throwable) -> Unit): CoroutineExceptionHandler {
        return CoroutineExceptionHandler { _, throwable ->
            onCoroutineException(throwable)
        }
    }

    override fun onCleared() {
        super.onCleared()
        // Add your custom cleanup code here if needed.
    }
}