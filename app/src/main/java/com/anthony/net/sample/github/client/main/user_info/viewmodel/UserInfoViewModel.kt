package com.anthony.net.sample.github.client.main.user_info.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.anthony.net.sample.github.client.base.BaseViewModel
import com.anthony.net.sample.github.client.dto.login.Repository
import com.anthony.net.sample.github.client.model.repository.user_info.UserInfoRepository
import com.anthony.net.sample.github.client.network.Resource
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class UserInfoViewModel(private val userInfoRepository: UserInfoRepository) : BaseViewModel() {

    private val _onRepositories by lazy { MutableLiveData<Resource<List<Repository>>>() }

    val onRepositories: LiveData<Resource<List<Repository>>> = _onRepositories

    fun getRepositories(loginName: String) {
        /*viewModelScope是一个綁定到當前viewModel的作用域  當ViewModel被清除時會自動取消该作用域，所以不用擔心oom*/
        viewModelScope.launch {

            userInfoRepository.getRepositories(loginName).onEach { resource ->

                _onRepositories.value = resource

            }.launchIn(this)

        }

    }

}