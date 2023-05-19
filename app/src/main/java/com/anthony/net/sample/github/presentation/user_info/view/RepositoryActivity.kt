package com.anthony.net.sample.github.presentation.user_info.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.anthony.net.sample.github.client.R
import com.anthony.net.sample.github.client.databinding.ActivityRepositoryBinding
import com.anthony.net.sample.github.domain.entity.login.Repository
import com.anthony.net.sample.github.presentation.user_info.adapter.RepositoryViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class RepositoryActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityRepositoryBinding

    companion object {

        const val REPOSITORY = "Repository"

        const val BUNDLE = "Bundle"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityRepositoryBinding.inflate(layoutInflater)

        setContentView(viewBinding.root)

        initView()

    }

    private fun initView() {

        val repository = intent.getBundleExtra(BUNDLE)?.getSerializable(REPOSITORY) as? Repository

        val userName = repository?.repositoryOwner.orEmpty()

        val repoName = repository?.repositoryName.orEmpty()

        viewBinding.repositoryName.text = repoName

        viewBinding.repositoryDescription.text =
            repository?.repositoryDescription ?: getString(R.string.no_description)

        val pages = resources.getStringArray(R.array.repository_tab)

        val homeViewPagerAdapter = RepositoryViewPagerAdapter(
            this, userName, repoName
        )

        viewBinding.repositoryViewPager.adapter = homeViewPagerAdapter

        TabLayoutMediator(
            viewBinding.repositoryTabLayout,
            viewBinding.repositoryViewPager
        ) { tab, position ->

            tab.text = (pages[position])

        }.attach()


    }


}