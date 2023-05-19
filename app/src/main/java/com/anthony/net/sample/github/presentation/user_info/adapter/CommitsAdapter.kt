package com.anthony.net.sample.github.presentation.user_info.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.anthony.net.sample.github.client.databinding.ItemCommitBinding
import com.anthony.net.sample.github.domain.model.user_info.Commit

class CommitsAdapter(
    commitItemCallback: CommitItemCallback,
) :
    ListAdapter<Commit, CommitsAdapter.ViewHolder>(commitItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCommitBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(getItem(position))

    }

    inner class ViewHolder(private val viewBinding: ItemCommitBinding) : RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(item: Commit) {

            viewBinding.userName.text = item.userName

            viewBinding.date.text = item.date

            viewBinding.message.text = item.message

        }

    }

}