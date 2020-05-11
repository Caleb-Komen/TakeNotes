package com.architecture.android.takenotes.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.architecture.android.takenotes.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NoteListFragment fragment = new NoteListFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment, null)
                .commit();
    }

    public void showFragment(int noteId){
        NoteFragment fragment = NoteFragment.newInstance(noteId);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment, null)
                .addToBackStack("note")
                .commit();
    }
}
