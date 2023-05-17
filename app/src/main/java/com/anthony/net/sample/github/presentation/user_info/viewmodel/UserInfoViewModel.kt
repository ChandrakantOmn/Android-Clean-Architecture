package com.anthony.net.sample.github.presentation.user_info.viewmodel

import GetUserInfoUseCase
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.anthony.net.sample.github.data.remote.Resource
import com.anthony.net.sample.github.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

class UserInfoViewModel(private val getUserInfoUseCase: GetUserInfoUseCase) : BaseViewModel() {

    private val _onUserInfoState by lazy { MutableLiveData<UserInfoState>() }

    val onUserInfoState: LiveData<UserInfoState> = _onUserInfoState
    fun getRepositories(loginName: String) {
        /*viewModelScope是一个綁定到當前viewModel的作用域  當ViewModel被清除時會自動取消该作用域，所以不用擔心oom*/
        viewModelScope.launch(getCoroutineExceptionHandler {
            _onUserInfoState.value =
                UserInfoState.Error(it.localizedMessage)
        }) {

            getUserInfoUseCase(loginName).collect { result ->

                when (result) {

                    is Resource.Loading -> _onUserInfoState.value = UserInfoState.Loading

                    is Resource.Success -> _onUserInfoState.value =
                        UserInfoState.Success(result.data)

                    is Resource.Error -> _onUserInfoState.value =
                        UserInfoState.Error(result.errorMessage)

                }

            }

        }

    }

}