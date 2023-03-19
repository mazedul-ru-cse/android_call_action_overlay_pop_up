package com.example.over_lay_pop_up

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.Toast


class MyService : BroadcastReceiver(){

    override fun onReceive(context: Context, intent: Intent) {

        Log.d("Receive Action", intent.action.toString())
        try {
            if("android.intent.action.PHONE_STATE" == intent.action){
                openDialog(context)
                //Log.d("Receive Action", intent.toString())
                Toast.makeText(context, intent.toString(), Toast.LENGTH_SHORT).show()
            }

        }catch (e: Exception){
            Log.d("Exception", e.message.toString())
            //Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
        }
    }

    private fun openDialog(context: Context) {

        val activityIntent = Intent(context, PopUpDialogService::class.java)
        activityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        activityIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        activityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)

        context.startService(activityIntent)

    }


}
