package com.architecture.android.takenotes.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.architecture.android.takenotes.BaseApp;
import com.architecture.android.takenotes.data.NoteRepository;
import com.architecture.android.takenotes.data.entity.Note;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    private NoteRepository mRepository;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        mRepository = ((BaseApp)application).getNoteRepository();
    }

    public LiveData<List<Note>> getNotes(){
        return mRepository.getNotes();
    }

    public LiveData<Note> getNote(int noteId){
        return mRepository.getNote(noteId);
    }

}
