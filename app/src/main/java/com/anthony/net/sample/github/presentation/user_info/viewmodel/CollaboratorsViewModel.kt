package com.anthony.net.sample.github.presentation.user_info.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.anthony.net.sample.github.data.remote.Resource
import com.anthony.net.sample.github.domain.usecase.user_info.GetCollaboratorsUseCase
import com.anthony.net.sample.github.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

class CollaboratorsViewModel(private val getCollaboratorsUseCase: GetCollaboratorsUseCase) :
    BaseViewModel() {

    private val _onCollaboratorsState by lazy { MutableLiveData<CollaboratorsState>() }

    val onCollaboratorsState: LiveData<CollaboratorsState> = _onCollaboratorsState

    fun getCollaborators(userName: String, repoName: String) {
        /*viewModelScope是一个綁定到當前viewModel的作用域  當ViewModel被清除時會自動取消该作用域，所以不用擔心oom*/
        viewModelScope.launch(getCoroutineExceptionHandler {
            _onCollaboratorsState.value =
                CollaboratorsState.Error(it.localizedMessage)
        }) {

            getCollaboratorsUseCase(userName, repoName).collect { result ->

                when (result) {

                    is Resource.Loading -> _onCollaboratorsState.value = CollaboratorsState.Loading

                    is Resource.Success -> _onCollaboratorsState.value =
                        CollaboratorsState.Success(result.data)

                    is Resource.Error -> _onCollaboratorsState.value =
                        CollaboratorsState.Error(result.errorMessage)

                }

            }

        }
    }
}