package com.anthony.net.sample.github.presentation.user_info.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.anthony.net.sample.github.data.remote.network.Resource
import com.anthony.net.sample.github.domain.usecase.user_info.GetCommitsUseCase
import com.anthony.net.sample.github.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

class CommitsViewModel(private val getCommitsUseCase: GetCommitsUseCase) : BaseViewModel() {

    private val _onCommitsState by lazy { MutableLiveData<CommitsState>() }

    val onCommitsState: LiveData<CommitsState> = _onCommitsState

    fun getCommits(userName: String, repoName: String) {
        viewModelScope.launch(getCoroutineExceptionHandler {
            _onCommitsState.value =
                CommitsState.Error(it.localizedMessage)
        }) {

            getCommitsUseCase(userName, repoName).collect { result ->

                when (result) {

                    is Resource.Loading -> _onCommitsState.value = CommitsState.Loading

                    is Resource.Success -> _onCommitsState.value = CommitsState.Success(result.data)

                    is Resource.Error -> _onCommitsState.value =
                        CommitsState.Error(result.errorMessage)

                }

            }

        }
    }


}