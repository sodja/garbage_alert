package com.codesodja.garbagealert.ui

import android.Manifest
import android.Manifest.permission
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.codesodja.garbagealert.R
import com.codesodja.garbagealert.utils.Constants.PERMISSIONS_REQUEST_RECEIVER_SMS
import com.codesodja.garbagealert.utils.Constants.PERMISSIONS_REQUEST_SEND_SMS
import com.codesodja.garbagealert.utils.checkReceiveSmsPermission
import com.codesodja.garbagealert.utils.checkSendSmsPermission
import com.codesodja.garbagealert.utils.getLongToast


class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragment = inflater.inflate(R.layout.home_fragment, container, false)
        checkForSmsPermission()
        return fragment
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        // TODO: Use the ViewModel
    }

    private fun checkForSmsPermission() {
        if (checkSendSmsPermission(requireContext(), requireActivity())) {
            enableSmsButton()
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkReceiveSmsPermission(requireContext(), requireActivity())
        }
    }

    private fun enableSmsButton() {
        //TODO
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSIONS_REQUEST_SEND_SMS -> {
                if (permissions[0] == permission.SEND_SMS
                    && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED
                ) {
                    // Permission was granted. Enable sms button.
                    enableSmsButton();
                } else {
                    // Permission denied.
                    getLongToast(
                        requireContext(),
                        "send sms not authorized"
                    )
                    // Disable the sms button.
                    disableSmsButton();
                }
            }
            PERMISSIONS_REQUEST_RECEIVER_SMS -> {
                if (permissions[0] == permission.RECEIVE_SMS
                    && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED
                ) {
                    // Permission was granted. Enable sms button.
                    enableSmsButton();
                } else {
                    getLongToast(
                        requireContext(),
                        "receive sms not authorized"
                    )
                }
            }
        }
    }

    private fun disableSmsButton() {
        TODO("Not yet implemented")
    }
}