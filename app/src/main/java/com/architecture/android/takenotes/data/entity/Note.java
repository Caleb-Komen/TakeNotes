package com.architecture.android.takenotes.data.entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "notes")
public class Note {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String noteTitle;
    private String noteContent;
    private Date dateCreated;

    @Ignore
    public Note(int id, String noteTitle, String noteContent, Date dateCreated) {
        this.id = id;
        this.noteTitle = noteTitle;
        this.noteContent = noteContent;
        this.dateCreated = dateCreated;
    }

    @Ignore
    public Note( String noteTitle, String noteContent, Date dateCreated) {
        this.noteTitle = noteTitle;
        this.noteContent = noteContent;
        this.dateCreated = dateCreated;
    }

    public Note() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
