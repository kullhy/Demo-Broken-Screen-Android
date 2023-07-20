package com.hidenobi.baseapplication.ui.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import com.hidenobi.baseapplication.base.BaseFragment
import com.hidenobi.baseapplication.databinding.M001HomeFragmentBinding
import com.hidenobi.baseapplication.viewmodel.HomeViewModel


class M001HomeFragment : BaseFragment<M001HomeFragmentBinding, HomeViewModel>() {
    override fun getClassViewModel(): Class<HomeViewModel> {
        return HomeViewModel::class.java
    }

    override fun initView() {
    }

    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): M001HomeFragmentBinding {
        return M001HomeFragmentBinding.inflate(inflater, container, false)
    }
}