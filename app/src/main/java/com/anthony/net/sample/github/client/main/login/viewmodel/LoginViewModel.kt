package com.anthony.net.sample.github.client.main.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.anthony.net.sample.github.client.base.BaseViewModel
import com.anthony.net.sample.github.client.model.common.User
import com.anthony.net.sample.github.client.network.Resource
import com.anthony.net.sample.github.client.repository.login.LoginRepository
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class LoginViewModel(private val loginRepository: LoginRepository) : BaseViewModel() {

    private val _onUser by lazy { MutableLiveData<Resource<User>>() }

    val onUser: LiveData<Resource<User>> = _onUser

    fun getUser(userName: String) {
        /*viewModelScope是一个綁定到當前viewModel的作用域  當ViewModel被清除時會自動取消该作用域，所以不用擔心oom*/
        viewModelScope.launch {

            loginRepository.getUser(userName).onEach { resource ->

                _onUser.value = resource

            }.launchIn(this)

        }
    }
}