package com.codesodja.garbagealert.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.telephony.SmsMessage
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.codesodja.garbagealert.utils.Constants.RECEIVER_SMS
import com.codesodja.garbagealert.utils.Constants.SEND_SMS


class SmsReceivers : BroadcastReceiver() {

    companion object {
        lateinit var SMS_INSTANCE: SmsReceivers
    }


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onReceive(context: Context?, intent: Intent?) {


        if (intent?.action.equals(RECEIVER_SMS)) {
            val bundle = intent!!.extras
            val msgs: Array<SmsMessage?>
            var strMessage = ""
            if (bundle != null) {
                val format = bundle.getString("format")
                try {
                    val pdus = bundle["pdus"] as Array<*>
                    val isVersionM = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                    msgs = arrayOfNulls(pdus.size)
                    for (i in msgs.indices) {
                        if (isVersionM) {
                            msgs[i] = SmsMessage.createFromPdu(pdus[i] as ByteArray?, format)
                        } else {
                            msgs[i] = SmsMessage.createFromPdu(pdus[i] as ByteArray?)
                        }
                        strMessage += "SMS from " + msgs[i]!!.originatingAddress
                        strMessage += """ :${msgs[i]!!.messageBody}"""
                        Log.d("TAG", "onReceive: $strMessage")
                        Toast.makeText(context, strMessage, Toast.LENGTH_LONG).show()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        } else if (intent?.action.equals(SEND_SMS)) {
            Log.d("TAG", "onService")
        }
    }
}