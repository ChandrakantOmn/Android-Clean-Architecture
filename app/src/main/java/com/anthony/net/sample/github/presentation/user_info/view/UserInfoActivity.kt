package com.anthony.net.sample.github.presentation.user_info.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.anthony.net.sample.github.client.databinding.ActivityUserInfoBinding
import com.anthony.net.sample.github.presentation.base.BaseActivity
import com.anthony.net.sample.github.presentation.user_info.adapter.RepositoriesAdapter
import com.anthony.net.sample.github.presentation.user_info.adapter.RepositoryItemCallback
import com.anthony.net.sample.github.presentation.user_info.viewmodel.UserInfoState
import com.anthony.net.sample.github.presentation.user_info.viewmodel.UserInfoViewModel
import org.koin.android.ext.android.inject

class UserInfoActivity : BaseActivity(), RepositoriesAdapter.OnRepositoryItemClick {

    companion object {

        const val LOGIN_NAME = "LoginName"

    }

    private lateinit var viewBinding: ActivityUserInfoBinding

    private var repositoriesAdapter: RepositoriesAdapter? = null

    private val userInfoViewModel: UserInfoViewModel by inject()

    override fun onStart() {
        super.onStart()
        initViewModel()
    }

    override fun onStop() {
        super.onStop()
        userInfoViewModel.onUserInfoState.removeObservers(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityUserInfoBinding.inflate(layoutInflater)

        setContentView(viewBinding.root)

        initView()

    }


    private fun initView() {

        val loginName = intent.getStringExtra(LOGIN_NAME) ?: ""

        viewBinding.userName.text = loginName

        repositoriesAdapter = RepositoriesAdapter(RepositoryItemCallback(), this)

        val linearLayoutManager = LinearLayoutManager(this)

        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL

        viewBinding.repositoriesRecyclerView.layoutManager = linearLayoutManager

        viewBinding.repositoriesRecyclerView.adapter = repositoriesAdapter

        userInfoViewModel.getRepositories(loginName)

    }

    private fun initViewModel() {

        userInfoViewModel.onUserInfoState.observe(this) { userInfoState ->

            when (userInfoState) {

                is UserInfoState.Loading -> {

                    showLoadingDialog()

                    return@observe

                }

                is UserInfoState.Success -> {

                    repositoriesAdapter?.submitList(userInfoState.repositories)

                }

                is UserInfoState.Error -> {

                    val errorMessage = userInfoState.errorMessage

                    Toast.makeText(
                        this,
                        errorMessage,
                        Toast.LENGTH_SHORT
                    ).show()

                }
                else -> Unit
            }

            dismissLoadingDialog()

        }

    }

    override fun onRepositoryItemClick(position: Int) {

        val intent = Intent()

        val bundle = Bundle()

        bundle.putSerializable(
            RepositoryActivity.REPOSITORY,
            repositoriesAdapter?.currentList?.get(position)
        )

        intent.putExtra(
            RepositoryActivity.BUNDLE,
            bundle
        )

        intent.setClass(this, RepositoryActivity::class.java)

        startActivity(intent)

    }

}