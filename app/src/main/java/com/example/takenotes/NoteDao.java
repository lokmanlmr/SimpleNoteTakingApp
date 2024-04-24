package com.example.takenotes;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addNote(Note note);

    @Delete
    void deleteNote(Note note);

    @Query("Select * From Note_table")
    LiveData<List<Note>> getAllNotes();
}
