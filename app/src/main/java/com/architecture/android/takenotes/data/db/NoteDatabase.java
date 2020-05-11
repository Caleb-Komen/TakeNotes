package com.architecture.android.takenotes.data.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.architecture.android.takenotes.data.DataGenerator;
import com.architecture.android.takenotes.data.DateConverter;
import com.architecture.android.takenotes.data.entity.Note;
import com.architecture.android.takenotes.data.dao.NoteDao;

import java.util.List;
import java.util.concurrent.Executor;

@Database(entities = Note.class, version = 1)
@TypeConverters(DateConverter.class)
public abstract class NoteDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "note_database";

    private static NoteDatabase sInstance;

    public abstract NoteDao noteDao();

    public MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();

    public static NoteDatabase getInstance(Context context, Executor executor){
        if (sInstance == null){
            synchronized (NoteDatabase.class){
                if (sInstance == null){
                    sInstance = createDatabase(context.getApplicationContext(), executor);
                    sInstance.updateDatabaseCreated(context);
                }
            }
        }
        return sInstance;
    }

    private static NoteDatabase createDatabase(final Context context, final Executor executor){
        return Room.databaseBuilder(context, NoteDatabase.class, DATABASE_NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        executor.execute(new Runnable() {
                            @Override
                            public void run() {
                                // populate  db with pre-existing data during creation
                                List<Note> notes = DataGenerator.getNotes();
                                NoteDatabase database = NoteDatabase.getInstance(context, executor);
                                insertDataToDb(database, notes);
                                database.setDatabaseCreated();
                            }
                        });
                    }
                })
                .build();
    }

    private static void insertDataToDb(final NoteDatabase database, final List<Note> notes) {
        database.runInTransaction(new Runnable() {
            @Override
            public void run() {
                for (Note note : notes) {
                    database.noteDao().insertNote(note);
                }
            }
        });
    }

    private void updateDatabaseCreated(Context context){
        if (context.getDatabasePath(DATABASE_NAME).exists()){
            setDatabaseCreated();
        }
    }
    private void setDatabaseCreated(){
        mIsDatabaseCreated.postValue(true);
    }

    public LiveData<Boolean> getDataCreated(){
        return mIsDatabaseCreated;
    }
}
