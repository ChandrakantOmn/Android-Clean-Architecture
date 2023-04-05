package com.anthony.net.sample.github.presentation.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.anthony.net.sample.github.presentation.widget.CustomLoadingDialog


open class BaseActivity : AppCompatActivity() {

    private lateinit var customLoadingDialog: CustomLoadingDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        customLoadingDialog = CustomLoadingDialog.newInstance()
    }

     fun showLoadingDialog() {
        customLoadingDialog.show(supportFragmentManager, "CUSTOM_LOADING_DIALOG")
    }

     fun dismissLoadingDialog() {
        customLoadingDialog.dismissAllowingStateLoss()
    }
}