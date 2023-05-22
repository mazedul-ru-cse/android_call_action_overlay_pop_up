package com.example.over_lay_pop_up.utils_;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.over_lay_pop_up.R;
import com.example.over_lay_pop_up.database_.DBHandler;

public class Note {
    Context context;
    ViewGroup viewGroup;

    DBHandler dbHandler;

    Button saveNoteBtn;
    EditText note;


    public Note(Context context, ViewGroup viewGroup) {
        this.context = context;
        this.viewGroup = viewGroup;
    }

    public void onCreate(){

        //Button initialize

        saveNoteBtn = viewGroup.findViewById(R.id.saveNoteBtn);
        note = viewGroup.findViewById(R.id.noteText);


        dbHandler  = new DBHandler(context);

        saveNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String noteText = note.getText().toString();

                if (noteText.trim().isEmpty()){
                    note.setError("Please write some words");
                    return;
                }

                saveNote(noteText);
                note.setText("");

            }
        });

    }

    void saveNote(String note){

        dbHandler.setNote(note);
        dbHandler.getData();
    }


}
