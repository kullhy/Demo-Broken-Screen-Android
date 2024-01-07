package com.hidenobi.baseapplication.ui.fragment

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import com.hidenobi.baseapplication.R
import com.hidenobi.baseapplication.base.BaseFragment
import com.hidenobi.baseapplication.databinding.M001HomeFragmentBinding
import com.hidenobi.baseapplication.ui.window.OverlayWindow
import com.hidenobi.baseapplication.viewmodel.HomeViewModel


class M001HomeFragment : BaseFragment<M001HomeFragmentBinding, HomeViewModel>() {

    private val launcherActivity =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {

        }
    private val overlayWindow: OverlayWindow by lazy {
        OverlayWindow(null, requireContext())
    }


    override fun initView() {
        initAction()
    }

    private fun initAction() {
        binding.apply {
            btnRequestPermission.setOnClickListener {
                if (!Settings.canDrawOverlays(requireContext())) {
                    val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION)
                    val uri = Uri.parse("package:${requireContext().packageName}")
                    intent.data = uri
                    launcherActivity.launch(intent)
                }
            }
            btnBroken1.setOnClickListener {
                overlayWindow.updateView(R.drawable.broken_1)
            }
            btnBroken2.setOnClickListener {
                overlayWindow.updateView(R.drawable.broken_2)
            }
        }
    }

    override fun onDetach() {
        overlayWindow.closeWindow()
        super.onDetach()
    }

    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): M001HomeFragmentBinding {
        return M001HomeFragmentBinding.inflate(inflater, container, false)
    }

    override fun getClassViewModel(): Class<HomeViewModel> {
        return HomeViewModel::class.java
    }
}