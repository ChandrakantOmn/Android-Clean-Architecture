package com.anthony.net.sample.github.presentation.user_info.viewmodel

import UserInfoUseCase
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.anthony.net.sample.github.data.remote.Resource
import com.anthony.net.sample.github.data.remote.dto.login.Repository
import com.anthony.net.sample.github.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

class UserInfoViewModel(private val userInfoUseCase: UserInfoUseCase) : BaseViewModel() {

    private val _onRepositories by lazy { MutableLiveData<Resource<List<Repository>>>() }

    val onRepositories: LiveData<Resource<List<Repository>>> = _onRepositories

    fun getRepositories(loginName: String) {
        /*viewModelScope是一个綁定到當前viewModel的作用域  當ViewModel被清除時會自動取消该作用域，所以不用擔心oom*/
        viewModelScope.launch {

            userInfoUseCase.getRepositories(loginName).collect { resource ->

                _onRepositories.value = resource

            }

        }

    }

}