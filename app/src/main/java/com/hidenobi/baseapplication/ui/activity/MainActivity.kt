package com.hidenobi.baseapplication.ui.activity

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.hidenobi.baseapplication.base.BaseViewModel
import com.hidenobi.baseapplication.R
import com.hidenobi.baseapplication.base.BaseActivity
import com.hidenobi.baseapplication.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding, BaseViewModel>() {

    private lateinit var navController: NavController
    private lateinit var hostFragment: NavHostFragment

    override fun initView() {
        setUpNavController()
    }

    override fun getClassViewModel(): Class<BaseViewModel> {
        return BaseViewModel::class.java
    }

    override fun initViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    private fun setUpNavController() {
        hostFragment =
            supportFragmentManager.findFragmentById(R.id.frameContainer) as NavHostFragment
        navController = hostFragment.navController
    }

}