package com.example.over_lay_pop_up

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi


class MyService : BroadcastReceiver(){
    override fun onReceive(context: Context, intent: Intent) {

        try {
            if("android.intent.action.PHONE_STATE" == intent.action){

                openDialog(context)
                Log.d("Exception", intent.toString())
            }

        }catch (e: Exception){
            Log.d("Exception", e.message.toString())

        }
    }
    private fun openDialog(context: Context) {


        try {
            DetectPhoneNumber().callStateListener(context)
        }catch (e :Exception){
            Log.i("Phone number detection",e.message.toString())
        }
//
//        val activityIntent = Intent(context, PopUpDialogService::class.java)
//        activityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//        activityIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
//        activityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
//
//        //Start overlay pop up service
//        context.startService(activityIntent)


    }

}


