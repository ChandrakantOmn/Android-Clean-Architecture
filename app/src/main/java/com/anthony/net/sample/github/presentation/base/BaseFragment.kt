package com.anthony.net.sample.github.presentation.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.anthony.net.sample.github.presentation.widget.CustomLoadingDialog

open class BaseFragment : Fragment() {

    private lateinit var customLoadingDialog: CustomLoadingDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        customLoadingDialog = CustomLoadingDialog.newInstance()

    }

    fun showLoadingDialog() {
        customLoadingDialog.show(parentFragmentManager, "CUSTOM_LOADING_DIALOG")
    }

    fun dismissLoadingDialog() {
        customLoadingDialog.dismissAllowingStateLoss()
    }
}