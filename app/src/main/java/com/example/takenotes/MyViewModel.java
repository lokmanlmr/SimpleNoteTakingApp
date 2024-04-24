package com.example.takenotes;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MyViewModel extends AndroidViewModel {
    private Repository repository;
    private LiveData<List<Note>> allNotes;
    public MyViewModel(@NonNull Application application) {
        super(application);
        this.repository=new Repository(application);
    }
    public LiveData<List<Note>> getAllNotes(){
        allNotes= repository.getAllNotes();
        return allNotes;
    }

    public void addNote (Note note){
        repository.addNote(note);
    }


    public void deletNote(Note note){
        repository.deleteNote(note);
    }

}
