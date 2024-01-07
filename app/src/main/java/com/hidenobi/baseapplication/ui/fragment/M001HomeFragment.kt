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
            btnBrokenUrl.setOnClickListener {
                overlayWindow.updateView("https://scontent.fhan14-3.fna.fbcdn.net/v/t39.30808-6/387841437_1993233967706809_2236545820343533675_n.jpg?_nc_cat=110&ccb=1-7&_nc_sid=efb6e6&_nc_eui2=AeExIk0Os-dN4NlvyadFkVrGk9UsyvXYbS-T1SzK9dhtLwbYq0bk_UlJgSrypIJN-e5yqs8df0TXPI9vtvfSZHu3&_nc_ohc=4Yejovmp-HkAX_D5_v8&_nc_ht=scontent.fhan14-3.fna&oh=00_AfAzBIVgwZ1N2Rhsy_LgpwNw4YDlFZzH3Th3gcsC0p6BXA&oe=65A00820")
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