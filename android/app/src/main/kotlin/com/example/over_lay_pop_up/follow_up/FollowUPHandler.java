package com.example.over_lay_pop_up.follow_up;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.text.format.Time;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.over_lay_pop_up.R;

import java.util.Calendar;

public class FollowUPHandler {

    Context context;
    ViewGroup viewGroup;

    Spinner callStatusSpinner,assignToSpinner,followCategorySpinner;

    EditText nextFollowUpField,noteField;

    String nextFollowUp,note = "",callStatus,assignTo,category;
    Button followUpSendBtn;

    DBHandler dbHandler;

    public FollowUPHandler(Context context, ViewGroup viewGroup) {
        this.context = context;
        this.viewGroup = viewGroup;
    }

    public void onCreate(){

        //Call status
        callStatusSpinner = viewGroup.findViewById(R.id.callStatusSpinner);
        assignToSpinner = viewGroup.findViewById(R.id.assignToSpinner);
        followCategorySpinner = viewGroup.findViewById(R.id.followCategorySpinner);
        nextFollowUpField = viewGroup.findViewById(R.id.nextFollowUpdate);
        noteField = viewGroup.findViewById(R.id.followUpNote);
        followUpSendBtn = viewGroup.findViewById(R.id.followUpSendBtn);


        //Set call status spinner data
        ArrayAdapter<CharSequence> callStatusSpinnerAdapter = ArrayAdapter.createFromResource(context, R.array.callStatus, R.layout.spinner_item);
        callStatusSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        callStatusSpinner.setAdapter(callStatusSpinnerAdapter);

        //Set assignTo spinner data
        ArrayAdapter<CharSequence> assignToSpinnerAdapter = ArrayAdapter.createFromResource(context, R.array.assignTo, R.layout.spinner_item);
        assignToSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        assignToSpinner.setAdapter(assignToSpinnerAdapter);


        //Set follow category spinner data
        ArrayAdapter<CharSequence> followCategorySpinnerAdapter = ArrayAdapter.createFromResource(context, R.array.followUpCategory, R.layout.spinner_item);
        followCategorySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        followCategorySpinner.setAdapter(followCategorySpinnerAdapter);



      nextFollowUpField.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                final int DRAWABLE_RIGHT = 2;

                if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    if(motionEvent.getRawX() >= (nextFollowUpField.getRight() -
                            nextFollowUpField.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())){
                    }
                }
                return false;
            }
        });


        followUpSendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //get next follow up date and time
                nextFollowUp = nextFollowUpField.getText().toString();

                //get note
                note = noteField.getText().toString();

                //get call status
                callStatus = callStatusSpinner.getSelectedItem().toString();

                //get assign to name
                assignTo = assignToSpinner.getSelectedItem().toString();

                //get category
                category = callStatusSpinner.getSelectedItem().toString();

                //check nextFollowUpField is valid or not
                if(nextFollowUpField.getText().toString().trim().length() < 6){
                    nextFollowUpField.setError("It's required!");
                    return;
                }

                //check call status selected or not
                if(callStatus.contains("Call Status*")){

                    View selectedView = callStatusSpinner.getSelectedView();
                    if (selectedView != null && selectedView instanceof TextView) {
                        callStatusSpinner.requestFocus();
                        TextView selectedTextView = (TextView) selectedView;
                        selectedTextView.setTextColor(Color.RED);
                        selectedTextView.setText(callStatus);
                        callStatusSpinner.performClick();
                    }

                    return;
                }

                //check assignTo selected or not
                if(assignTo.contains("Assign To*")){

                    View selectedView = assignToSpinner.getSelectedView();
                    if (selectedView != null && selectedView instanceof TextView) {
                        assignToSpinner.requestFocus();
                        TextView selectedTextView = (TextView) selectedView;
                        selectedTextView.setTextColor(Color.RED);
                        selectedTextView.setText(assignTo);
                        assignToSpinner.performClick();
                    }

                    return;
                }

                //check category selected or not
                if(category.contains("Select follow category*")){

                    View selectedView = followCategorySpinner.getSelectedView();
                    if (selectedView != null && selectedView instanceof TextView) {
                        followCategorySpinner.requestFocus();
                        TextView selectedTextView = (TextView) selectedView;
                        selectedTextView.setTextColor(Color.RED);
                        selectedTextView.setText(category);
                        followCategorySpinner.performClick();
                    }
                    return;
                }

                dbHandler = new DBHandler(context);

                dbHandler.addNewFollowUp(nextFollowUp,note,callStatus,assignTo,category);
                dbHandler.readData();
            }
        });
    }


}
