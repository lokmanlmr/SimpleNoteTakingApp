package com.example.takenotes;

import android.content.Context;
import android.content.Intent;
import android.view.View;

public class MainActivityClickHandler {
    Context context;

    public MainActivityClickHandler(Context context) {
        this.context = context;
    }

    public void onFabClicked(View view){
        Intent i =new Intent(view.getContext(), AddNewNote.class);
        Note note=new Note();
        i.putExtra("note",note);
        i.putExtra("isViewOrUpdate","v");
        context.startActivity(i);
    }
}
