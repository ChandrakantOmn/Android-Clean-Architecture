package com.anthony.net.sample.github.presentation.login.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.anthony.net.sample.github.client.R
import com.anthony.net.sample.github.client.databinding.ActivityLoginBinding
import com.anthony.net.sample.github.data.remote.Resource
import com.anthony.net.sample.github.presentation.base.BaseActivity
import com.anthony.net.sample.github.presentation.login.viewmodel.LoginViewModel
import com.anthony.net.sample.github.presentation.user_info.view.UserInfoActivity
import org.koin.android.ext.android.inject

class LoginActivity : BaseActivity() {

    private lateinit var viewBinding: ActivityLoginBinding

    private val loginViewModel: LoginViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityLoginBinding.inflate(layoutInflater)

        setContentView(viewBinding.root)

        initView()

        initViewModel()

    }

    private fun initView() {

        viewBinding.startedLabel.setOnClickListener {

            val userName = viewBinding.accountEditText.text.toString()

            if (userName.isEmpty()) {

                Toast.makeText(this, getString(R.string.account_hint), Toast.LENGTH_SHORT).show()

                return@setOnClickListener

            }

            showLoadingDialog()

            loginViewModel.getUser(userName)

        }

    }

    private fun initViewModel() {

        loginViewModel.onUser.observe(this) { result ->

            when (result) {

                is Resource.Success -> openUserInfoPage(result.data?.login ?: return@observe)

                is Resource.Error -> Toast.makeText(
                    this@LoginActivity,
                    result.errorMessage,
                    Toast.LENGTH_SHORT
                ).show()

            }

            dismissLoadingDialog()

        }

    }

    private fun openUserInfoPage(loginName: String) {

        val intent = Intent()

        intent.putExtra(UserInfoActivity.LOGIN_NAME, loginName)

        intent.setClass(this, UserInfoActivity::class.java)

        startActivity(intent)

    }


}