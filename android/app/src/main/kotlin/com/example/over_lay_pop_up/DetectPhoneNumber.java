package com.example.over_lay_pop_up;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;


public class DetectPhoneNumber  {

    private PhoneStateListener phoneStateListener;
    private TelephonyManager telephonyManager;

    void callStateListener(Context context){

        telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

        phoneStateListener = new PhoneStateListener(){

            @Override
            public void onCallStateChanged(int state, String phoneNumber) {
                super.onCallStateChanged(state, phoneNumber);

                if(TelephonyManager.CALL_STATE_IDLE == state) {


                    int length = phoneNumber.length();
                    String realNumber = "";
                    if (length >= 12) {

                        realNumber = phoneNumber.substring(length - 11, length);

                        Intent popUpWindow = new Intent(context,PopUpDialogService.class);

                        popUpWindow.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        popUpWindow.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        popUpWindow.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        popUpWindow.putExtra("contactNumber",realNumber);
                        popUpWindow.putExtra("contactName",realNumber);

                        //Start overlay pop up service
                        context.startService(popUpWindow);
                    }




                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nCALL_STATE_IDLE ,Phone number with country code " + phoneNumber + "\nPhone number without country code " + realNumber);


                }


            }

        };


        telephonyManager.listen(phoneStateListener,PhoneStateListener.LISTEN_CALL_STATE);



    }
}
