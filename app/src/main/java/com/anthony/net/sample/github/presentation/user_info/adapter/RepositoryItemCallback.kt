package com.anthony.net.sample.github.presentation.user_info.adapter

import androidx.recyclerview.widget.DiffUtil
import com.anthony.net.sample.github.domain.model.login.Repository

class RepositoryItemCallback : DiffUtil.ItemCallback<Repository>() {

    override fun areItemsTheSame(oldItem: Repository, newItem: Repository): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Repository, newItem: Repository): Boolean {
        return oldItem.id == newItem.id
    }

}