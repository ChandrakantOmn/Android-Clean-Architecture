package com.anthony.net.sample.github.presentation.user_info.adapter

import androidx.recyclerview.widget.DiffUtil
import com.anthony.net.sample.github.domain.entity.user_info.Commit

class CommitItemCallback : DiffUtil.ItemCallback<Commit>() {

    override fun areItemsTheSame(oldItem: Commit, newItem: Commit): Boolean {
        return oldItem.nodeId == newItem.nodeId
    }

    override fun areContentsTheSame(oldItem: Commit, newItem: Commit): Boolean {
        return oldItem.nodeId == newItem.nodeId
    }

}