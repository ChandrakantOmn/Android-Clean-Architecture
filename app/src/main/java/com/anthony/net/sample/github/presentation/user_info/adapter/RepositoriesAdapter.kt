package com.anthony.net.sample.github.presentation.user_info.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.anthony.net.sample.github.client.R
import com.anthony.net.sample.github.client.databinding.ItemRepositoryBinding
import com.anthony.net.sample.github.data.remote.dto.login.Repository
class RepositoriesAdapter(
    repositoryItemCallback: RepositoryItemCallback,
    private val onRepositoryItemClick: OnRepositoryItemClick
) :
    ListAdapter<Repository, RepositoriesAdapter.ViewHolder>(repositoryItemCallback) {

    interface OnRepositoryItemClick {

        fun onRepositoryItemClick(position: Int)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemRepositoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(getItem(position))

    }

    inner class ViewHolder(private val viewBinding: ItemRepositoryBinding) : RecyclerView.ViewHolder(viewBinding.root),
        View.OnClickListener {

        init {

            viewBinding.root.setOnClickListener(this)

        }

        fun bind(item: Repository) {

            val context = itemView.context

            viewBinding.userIcon.load(item.owner.avatar_url) {
                crossfade(true)
                placeholder(R.drawable.git_icon)
                transformations(CircleCropTransformation())
            }

            viewBinding.repositoryStar.text = item.stargazers_count.toString()

            viewBinding.repositoryBranch.text = item.forks_count.toString()

            viewBinding.repositoryOwner.text = item.owner.login

            viewBinding.repositoryName.text = item.name

            viewBinding.repositoryDescription.text =
                item.description ?: context.getString(R.string.no_description)

            viewBinding.repositoryLanguage.text = item.language

        }

        override fun onClick(v: View?) {

            onRepositoryItemClick.onRepositoryItemClick(adapterPosition)

        }

    }

}