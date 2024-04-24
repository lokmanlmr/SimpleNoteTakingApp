package com.example.takenotes;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Note.class},version = 1)
public abstract class NoteDatabase extends RoomDatabase {

    public abstract NoteDao getDao();
    private static NoteDatabase noteDb;

    public static synchronized NoteDatabase getInstance(Context context){
        //we check if the db instance doesn't exist first
        //if it doesn't we create a new one
        if(noteDb==null){
            noteDb= Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class,
                    "contacts_db").fallbackToDestructiveMigration().build();
        }
        // if there is an existing db instance we just return it
        return noteDb ;
    }


}
