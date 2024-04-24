package com.example.takenotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.example.takenotes.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  implements NoteClickedInterface {

    private int noteClickedPosition=-1;
    private NoteDatabase noteDatabase;
    private ArrayList<Note> noteArrayList =new ArrayList<>();
    //Adapter
    private NoteAdapter myAdapter;
    //Binding
    private ActivityMainBinding activityMainBinding;
    private MainActivityClickHandler handlers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityMainBinding= DataBindingUtil.setContentView(this,
                R.layout.activity_main);
        handlers=new MainActivityClickHandler(this);

        activityMainBinding.setClickHandler(handlers);

        RecyclerView recyclerView=activityMainBinding.RecyclerView;
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setHasFixedSize(true);

        //Creating an Instance of the database
        noteDatabase= NoteDatabase.getInstance(this);

        //ViewModel
        MyViewModel viewModel=new ViewModelProvider(this).
                get(MyViewModel.class);


        viewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                noteArrayList.clear();
                for (Note c:notes ) {
                    noteArrayList.add(c);
                }
                myAdapter.notifyDataSetChanged();
            }
        });

        //Adapter
        myAdapter=new NoteAdapter(noteArrayList,this);
        //set the adapter
        recyclerView.setAdapter(myAdapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Note c=noteArrayList.get(viewHolder.getAdapterPosition());
                viewModel.deletNote(c);
            }
        }).attachToRecyclerView(recyclerView);


    }

    @Override
    public void onNoteClicked(Note note, int pos) {
            noteClickedPosition=pos;
            Intent intent=new Intent(getApplicationContext(), AddNewNote.class);
            intent.putExtra("isViewOrUpdate","u");
            intent.putExtra("note",note);
            startActivity(intent);
    }
}