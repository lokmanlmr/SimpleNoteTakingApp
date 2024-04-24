package com.example.takenotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Path;
import android.os.Bundle;
import android.util.Log;

import com.example.takenotes.databinding.ActivityAddNewNoteBinding;

import java.util.Objects;

public class AddNewNote extends AppCompatActivity {
    private ActivityAddNewNoteBinding binding;
    private AddNewNoteClickHandler handler;
    private Note note;
    private String OperationType;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_note);

        OperationType= getIntent().getStringExtra("isViewOrUpdate");

        switch (OperationType){
            case "v":
                note=new Note();
            case "u":
                note= (Note) getIntent().getSerializableExtra("note");

        }



        binding= DataBindingUtil.setContentView(this,
                R.layout.activity_add_new_note);

        MyViewModel myViewModel=new ViewModelProvider(this)
                .get(MyViewModel.class);



        handler=new AddNewNoteClickHandler(note,
                this,
                myViewModel,OperationType);
        binding.setNoteItem(note);
        binding.setBtn(handler);
    }

}