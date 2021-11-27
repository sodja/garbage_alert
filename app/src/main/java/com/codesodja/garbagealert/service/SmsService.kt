package com.codesodja.garbagealert.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.codesodja.garbagealert.receivers.SmsReceivers
import android.content.IntentFilter
import com.codesodja.garbagealert.receivers.SmsReceivers.Companion.SMS_INSTANCE


class SmsService: Service() {
    private val smsInstance: SmsReceivers = SMS_INSTANCE


    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }
    override fun onCreate() {
        super.onCreate()
        val iFilter = IntentFilter()

        registerReceiver(this.smsInstance, iFilter)

    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(this.smsInstance);
    }
}