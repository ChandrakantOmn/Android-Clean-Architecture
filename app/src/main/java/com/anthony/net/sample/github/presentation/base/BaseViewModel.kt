package com.anthony.net.sample.github.presentation.base

import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
    override fun onCleared() {
        super.onCleared()
        // Add your custom cleanup code here if needed.
    }
}