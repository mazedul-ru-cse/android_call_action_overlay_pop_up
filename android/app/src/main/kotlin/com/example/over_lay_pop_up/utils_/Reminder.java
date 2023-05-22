package com.example.over_lay_pop_up.utils_;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.over_lay_pop_up.R;
import com.example.over_lay_pop_up.database_.DBHandler;

public class Reminder {
    Context context;
    ViewGroup viewGroup;

    DBHandler dbHandler;

    TextView reminderTenMint,reminderOneHrs,reminderThreeHrs,reminderTomorrow,reminderCustom;



    public Reminder(Context context, ViewGroup viewGroup) {
        this.context = context;
        this.viewGroup = viewGroup;
    }

    public void onCreate(){

        //Button initialize

        reminderTenMint = viewGroup.findViewById(R.id.reminderTenMint);
        reminderOneHrs = viewGroup.findViewById(R.id.reminderOneHrs);
        reminderThreeHrs = viewGroup.findViewById(R.id.reminderThreeHrs);
        reminderTomorrow = viewGroup.findViewById(R.id.reminderTomorrow);
        reminderCustom = viewGroup.findViewById(R.id.reminderCustom);

        dbHandler  = new DBHandler(context);

        reminderTenMint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveReminder("10");
            }
        });


        reminderOneHrs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveReminder("60");
            }
        });

        reminderThreeHrs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveReminder("180");
            }
        });


        reminderTomorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveReminder("1440");
            }
        });

        reminderCustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveReminder("Custom time");
            }
        });
        
    }

    void saveReminder(String reminder){

        dbHandler.setReminder(reminder);
        dbHandler.getData();
    }


}
