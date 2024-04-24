package com.example.takenotes;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {

    private final NoteDao noteDao;
    ExecutorService executor;
    Handler handler;
    public Repository(Application application) {

        NoteDatabase noteDatabase=NoteDatabase.getInstance(application);
        this.noteDao = noteDatabase.getDao();

        executor= Executors.newSingleThreadExecutor();
        handler= new Handler(Looper.getMainLooper());
    }

    public void addNote(Note note){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                noteDao.addNote(note);
            }
        });

    }


    public void deleteNote(Note note){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                noteDao.deleteNote(note);
            }
        });

    }

    public LiveData<List<Note>> getAllNotes(){
        return noteDao.getAllNotes();
    }

}
