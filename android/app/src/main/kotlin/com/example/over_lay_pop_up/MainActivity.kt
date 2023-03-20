package com.example.over_lay_pop_up
import io.flutter.embedding.android.FlutterActivity
//import io.flutter.app.FlutterActivity
import android.content.BroadcastReceiver
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Toast
import android.util.Log
import androidx.annotation.NonNull
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

class MainActivity: FlutterActivity() {

   private var receiver: BroadcastReceiver? = null

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        Toast.makeText(this, "Work", Toast.LENGTH_SHORT).show()
        Log.i("Action", "Work")

        configureReceiver()
    }

    private fun configureReceiver(){

        val filter = IntentFilter()
        Toast.makeText(this, "Registered", Toast.LENGTH_SHORT).show()

//        filter.addAction("com.ihelpbd.isalescrm")
          filter.addAction("android.intent.action.PHONE_STATE")
          filter.addAction("android.intent.action.READ_PHONE_STATE")
          filter.addAction("android.intent.action.NEW_OUTGOING_CALL")
          filter.addAction("android.intent.action.CALL_PHONE")

        receiver = MyService()
        registerReceiver(receiver, filter)

        Toast.makeText(this, "Registered okay", Toast.LENGTH_SHORT).show()
    }
}
