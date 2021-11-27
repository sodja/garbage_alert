package com.codesodja.garbagealert.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContentProviderCompat.requireContext

fun getLongToast(context: Context, message: String){
    Toast.makeText(
        context,
        message,
        Toast.LENGTH_LONG
    ).show()
}
fun getShortToast(context: Context, message: String){
    Toast.makeText(
        context,
        message,
        Toast.LENGTH_SHORT
    ).show()
}

fun checkSendSmsPermission(context: Context, activity: Activity) : Boolean {
    return if (ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.SEND_SMS
        ) !=
        PackageManager.PERMISSION_GRANTED
    ) {
        ActivityCompat.requestPermissions(
            activity, arrayOf(Manifest.permission.SEND_SMS),
            Constants.PERMISSIONS_REQUEST_SEND_SMS
        )
        false
    } else {
        true
    }
}

fun checkReceiveSmsPermission(context: Context, activity: Activity) : Boolean {
    return if(ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.RECEIVE_SMS
        ) !=
        PackageManager.PERMISSION_GRANTED
    ) {
        ActivityCompat.requestPermissions(
            activity, arrayOf(Manifest.permission.RECEIVE_SMS),
            Constants.PERMISSIONS_REQUEST_RECEIVER_SMS
        )
        false
    } else {
        true
    }
}