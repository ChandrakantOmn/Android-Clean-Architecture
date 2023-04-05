package com.anthony.net.sample.github.presentation.user_info.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.anthony.net.sample.github.client.R
import com.anthony.net.sample.github.client.databinding.ItemCollaboratorBinding
import com.anthony.net.sample.github.data.remote.dto.user_info.Collaborator

class CollaboratorsAdapter(
    collaboratorItemCallback: CollaboratorItemCallback,
) :
    ListAdapter<Collaborator, CollaboratorsAdapter.ViewHolder>(collaboratorItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCollaboratorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(getItem(position))

    }

    inner class ViewHolder(private val viewBinding: ItemCollaboratorBinding) : RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(item: Collaborator) {

            viewBinding.collaboratorImg.load(item.avatar_url) {
                crossfade(true)
                placeholder(R.drawable.git_icon)
                transformations(CircleCropTransformation())
            }

            viewBinding.collaboratorName.text = item.login

        }

    }

}