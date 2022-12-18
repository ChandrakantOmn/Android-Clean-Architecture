package com.anthony.net.sample.github.client.main.user_info.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.anthony.net.sample.github.client.R
import com.anthony.net.sample.github.client.databinding.ActivityRepositoryBinding
import com.anthony.net.sample.github.client.dto.response.Repository
import com.anthony.net.sample.github.client.main.user_info.adapter.RepositoryViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class RepositoryActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityRepositoryBinding

    companion object {

        const val REPOSITORIES = "Repository"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityRepositoryBinding.inflate(layoutInflater)

        setContentView(viewBinding.root)

        initView()

    }

    private fun initView() {

        val repository = intent.getSerializableExtra(REPOSITORIES) as? Repository

        val userName = repository?.owner?.login ?: ""

        val repoName = repository?.name ?: ""

        viewBinding.repositoryName.text = repository?.name ?: ""

        viewBinding.repositoryDescription.text =
            repository?.description ?: getString(R.string.no_description)

        val pages = resources.getStringArray(R.array.repository_tab)

        val homeViewPagerAdapter = RepositoryViewPagerAdapter(
            this, userName, repoName
        )

        viewBinding.repositoryViewPager.offscreenPageLimit = 1

        viewBinding.repositoryViewPager.adapter = homeViewPagerAdapter

        TabLayoutMediator(
            viewBinding.repositoryTabLayout,
            viewBinding.repositoryViewPager
        ) { tab, position ->

            tab.text = (pages[position])

        }.attach()


    }


}