package com.anthony.net.sample.github.presentation.user_info.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.anthony.net.sample.github.data.remote.Resource
import com.anthony.net.sample.github.domain.usecase.user_info.CommitsUseCase
import com.anthony.net.sample.github.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

class CommitsViewModel(private val commitsUseCase: CommitsUseCase) : BaseViewModel() {

    private val _onCommits by lazy { MutableLiveData<CommitsState>() }

    val onCommits: LiveData<CommitsState> = _onCommits

    fun getCommits(userName: String, repoName: String) {
        viewModelScope.launch {

            commitsUseCase.getCommits(userName, repoName).collect { result ->

                when (result) {

                    is Resource.Success -> _onCommits.value = CommitsState(result.data)

                    is Resource.Error -> _onCommits.value = CommitsState(null, result.errorMessage)

                }

            }

        }
    }


}