package com.anthony.net.sample.github.client.main.user_info.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.anthony.net.sample.github.client.base.BaseViewModel
import com.anthony.net.sample.github.client.dto.user_info.Collaborator
import com.anthony.net.sample.github.client.model.repository.user_info.CollaboratorsRepository
import com.anthony.net.sample.github.client.network.Resource
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class CollaboratorsViewModel(private val collaboratorsRepository: CollaboratorsRepository) :
    BaseViewModel() {

    private val _onCollaborators by lazy { MutableLiveData<Resource<List<Collaborator>>>() }

    val onCollaborators: LiveData<Resource<List<Collaborator>>> = _onCollaborators

    fun getCollaborators(userName: String, repoName: String) {
        /*viewModelScope是一个綁定到當前viewModel的作用域  當ViewModel被清除時會自動取消该作用域，所以不用擔心oom*/
        viewModelScope.launch {

            collaboratorsRepository.getCollaborators(userName, repoName).onEach { resource ->

                _onCollaborators.value = resource

            }.launchIn(this)

        }
    }
}