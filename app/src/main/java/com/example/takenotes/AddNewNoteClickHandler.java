package com.example.takenotes;

import android.content.Context;
import android.content.Intent;

import android.view.View;
import android.widget.Toast;

public class AddNewNoteClickHandler {
    private final Note Note;
    private Context context;
    private MyViewModel myViewModel;
    private String OperationType;

    public AddNewNoteClickHandler(Note notes, Context context
            , MyViewModel myViewModel,String OperationType) {
        Note = notes;
        this.context = context;
        this.myViewModel = myViewModel;
        this.OperationType=OperationType;

    }

    public void onSubmitClickHandler(View view){
        if (Note.getNoteName() == null || Note.getNoteContent()==null){
            Toast.makeText(context, "Fields Cannot be empty", Toast.LENGTH_SHORT).show();
        }
        else{
            Intent i = new Intent(context,MainActivity.class);

            Note c = new Note(Note.getNoteName(), Note.getNoteContent());

            c.setId(Note.getId());
            myViewModel.addNote(c);

            context.startActivity(i);

        }


    }
}
