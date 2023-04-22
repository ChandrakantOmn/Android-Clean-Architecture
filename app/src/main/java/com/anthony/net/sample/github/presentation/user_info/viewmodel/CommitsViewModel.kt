package com.anthony.net.sample.github.presentation.user_info.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.anthony.net.sample.github.data.remote.Resource
import com.anthony.net.sample.github.domain.usecase.user_info.CommitsUseCase
import com.anthony.net.sample.github.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

class CommitsViewModel(private val commitsUseCase: CommitsUseCase) : BaseViewModel() {

    private val _onCommitsState by lazy { MutableLiveData<CommitsState>() }

    val onCommitsState: LiveData<CommitsState> = _onCommitsState

    fun getCommits(userName: String, repoName: String) {
        viewModelScope.launch {

            commitsUseCase.getCommits(userName, repoName).collect { result ->

                when (result) {

                    is Resource.Success -> _onCommitsState.value = CommitsState.Success(result.data)

                    is Resource.Error -> _onCommitsState.value =
                        CommitsState.Error(result.errorMessage)

                }

            }

        }
    }


}