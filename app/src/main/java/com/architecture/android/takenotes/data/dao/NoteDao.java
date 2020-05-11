package com.architecture.android.takenotes.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.architecture.android.takenotes.data.entity.Note;

import java.util.List;

@Dao
public interface NoteDao {
    @Query("SELECT * FROM notes")
    LiveData<List<Note>> getNotes();

    @Query("SELECT * FROM notes WHERE notes.id = :id")
    LiveData<Note> getNote(int id);

    @Insert
    void insertNote(Note note);

    @Delete
    void deleteNote(Note note);
}
