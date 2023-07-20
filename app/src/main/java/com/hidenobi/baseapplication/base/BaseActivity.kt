package com.hidenobi.baseapplication.base

import android.content.Context
import android.content.ContextWrapper
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.hidenobi.baseapplication.other.setUpLanguage

abstract class BaseActivity<VB : ViewBinding, VM : ViewModel> : AppCompatActivity() {

    //  TODO remove label private for binding, viewModel when MainActivity use them
    private lateinit var binding: VB
    private lateinit var viewModel: VM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = initViewBinding()
        viewModel = ViewModelProvider(this)[getClassViewModel()]
        initView()
        setContentView(binding.root)
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(ContextWrapper(newBase?.setUpLanguage()))
    }

    abstract fun initView()
    abstract fun getClassViewModel(): Class<VM>
    abstract fun initViewBinding(): VB
}