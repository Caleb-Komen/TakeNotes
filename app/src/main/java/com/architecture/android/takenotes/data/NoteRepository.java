package com.architecture.android.takenotes.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.architecture.android.takenotes.data.db.NoteDatabase;
import com.architecture.android.takenotes.data.entity.Note;

import java.util.List;

public class NoteRepository {
    private NoteDatabase mDatabase;

    private MediatorLiveData<List<Note>> mObservableNote;

    public NoteRepository(NoteDatabase database){
        mDatabase = database;
        mObservableNote = new MediatorLiveData<>();

        mObservableNote.addSource(mDatabase.noteDao().getNotes(), new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                mObservableNote.postValue(notes);
            }
        });
    }

    public LiveData<List<Note>> getNotes(){
        return mObservableNote;
    }

    public LiveData<Note> getNote(int noteId){
        return mDatabase.noteDao().getNote(noteId);
    }
}
