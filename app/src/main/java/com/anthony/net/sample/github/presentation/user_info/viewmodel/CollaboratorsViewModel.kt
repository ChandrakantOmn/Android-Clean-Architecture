package com.anthony.net.sample.github.presentation.user_info.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.anthony.net.sample.github.data.remote.Resource
import com.anthony.net.sample.github.data.remote.dto.user_info.Collaborator
import com.anthony.net.sample.github.domain.usecase.user_info.CollaboratorsUseCase
import com.anthony.net.sample.github.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

class CollaboratorsViewModel(private val collaboratorsUseCase: CollaboratorsUseCase) :
    BaseViewModel() {

    private val _onCollaborators by lazy { MutableLiveData<Resource<List<Collaborator>>>() }

    val onCollaborators: LiveData<Resource<List<Collaborator>>> = _onCollaborators

    fun getCollaborators(userName: String, repoName: String) {
        /*viewModelScope是一个綁定到當前viewModel的作用域  當ViewModel被清除時會自動取消该作用域，所以不用擔心oom*/
        viewModelScope.launch {

            collaboratorsUseCase.getCollaborators(userName, repoName).collect { resource ->

                _onCollaborators.value = resource

            }

        }
    }
}