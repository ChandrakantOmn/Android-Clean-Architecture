package com.anthony.net.sample.github.presentation.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.anthony.net.sample.github.data.remote.network.Resource
import com.anthony.net.sample.github.domain.usecase.login.GetLoginUseCase
import com.anthony.net.sample.github.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

class LoginViewModel(private val getLoginUseCase: GetLoginUseCase) : BaseViewModel() {

    private val _onLoginState by lazy { MutableLiveData<LoginState>() }

    val onLoginState: LiveData<LoginState> = _onLoginState

    fun getUser(userName: String) {
        /*viewModelScope是一个綁定到當前viewModel的作用域  當ViewModel被清除時會自動取消该作用域，所以不用擔心oom*/
        viewModelScope.launch(getCoroutineExceptionHandler {
            _onLoginState.value =
                LoginState.Error(it.localizedMessage)
        }) {

            getLoginUseCase(userName).collect { result ->

                when (result) {

                    is Resource.Loading -> _onLoginState.value = LoginState.Loading

                    is Resource.Success -> _onLoginState.value = LoginState.Success(result.data)

                    is Resource.Error -> _onLoginState.value = LoginState.Error(result.errorMessage)

                }

            }

        }
    }
}