package com.architecture.android.takenotes;

import android.app.Application;

import com.architecture.android.takenotes.data.db.NoteDatabase;
import com.architecture.android.takenotes.data.NoteRepository;
import com.architecture.android.takenotes.ui.NoteViewModel;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class BaseApp extends Application {
public Executor mExecutor;

    @Override
    public void onCreate() {
        super.onCreate();
        mExecutor = Executors.newSingleThreadExecutor();
    }

    private NoteDatabase getDatabase(){
        return NoteDatabase.getInstance(this, mExecutor);
    }

    public NoteRepository getNoteRepository(){
        return new NoteRepository(getDatabase());
    }

    public NoteViewModel getNoteViewModel(){
        return new NoteViewModel(this);
    }
}
