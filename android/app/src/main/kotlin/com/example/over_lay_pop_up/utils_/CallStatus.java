package com.example.over_lay_pop_up.utils_;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.over_lay_pop_up.R;
import com.example.over_lay_pop_up.database_.DBHandler;


public class CallStatus {

    Context context;
    ViewGroup viewGroup;

    DBHandler dbHandler;

    TextView missCallBtn,receivedCallBtn,switchOffBtn,busyBtn,waitingBtn,otherBtn;



    public CallStatus(Context context, ViewGroup viewGroup) {
        this.context = context;
        this.viewGroup = viewGroup;
    }

    public void onCreate(){

        //Button initialize

        missCallBtn = viewGroup.findViewById(R.id.missCallBtn);
        receivedCallBtn = viewGroup.findViewById(R.id.receivedCallBtn);
        switchOffBtn = viewGroup.findViewById(R.id.switchOffBtn);
        busyBtn = viewGroup.findViewById(R.id.busyBtn);
        waitingBtn = viewGroup.findViewById(R.id.waitingBtn);
        otherBtn = viewGroup.findViewById(R.id.otherBtn);

        dbHandler  = new DBHandler(context);

        missCallBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveCallStatus("Missed call");
            }
        });


        receivedCallBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveCallStatus("Received Call");
            }
        });

        switchOffBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveCallStatus("Switch Off");
            }
        });


        busyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveCallStatus("Busy");
            }
        });

        waitingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveCallStatus("Waiting");
            }
        });

        otherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveCallStatus("Other");
            }
        });

    }

    void saveCallStatus(String callStatus){

        dbHandler.setCallStatus(callStatus);
        dbHandler.getData();
    }


}
