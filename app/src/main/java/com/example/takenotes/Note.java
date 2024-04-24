package com.example.takenotes;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Note_table")
public class Note implements Serializable {
    @ColumnInfo(name = "note_id")
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "note_name")
    private String NoteName;
    @ColumnInfo(name = "note_content")
    private String  NoteContent;

    public Note() {
    }

    public Note( String noteName, String noteContent) {
        NoteName = noteName;
        NoteContent = noteContent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNoteName() {
        return NoteName;
    }

    public void setNoteName(String noteName) {
        NoteName = noteName;
    }

    public String getNoteContent() {
        return NoteContent;
    }

    public void setNoteContent(String noteContent) {
        NoteContent = noteContent;
    }
}
