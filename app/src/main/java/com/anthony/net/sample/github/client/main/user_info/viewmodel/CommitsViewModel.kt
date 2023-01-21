package com.anthony.net.sample.github.client.main.user_info.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.anthony.net.sample.github.client.base.BaseViewModel
import com.anthony.net.sample.github.client.model.user_info.Commit
import com.anthony.net.sample.github.client.network.Resource
import com.anthony.net.sample.github.client.repository.user_info.CommitsRepository
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class CommitsViewModel(private val commitsRepository: CommitsRepository) : BaseViewModel() {

    private val _onCommits by lazy { MutableLiveData<Resource<List<Commit>>>() }

    val onCommits: LiveData<Resource<List<Commit>>> = _onCommits

    fun getCommits(userName: String, repoName: String) =
        /*viewModelScope是一个綁定到當前viewModel的作用域  當ViewModel被清除時會自動取消该作用域，所以不用擔心oom*/
        viewModelScope.launch {

            commitsRepository.getCommits(userName, repoName).onEach { resource ->

                _onCommits.value = resource

            }.launchIn(this)

        }

}