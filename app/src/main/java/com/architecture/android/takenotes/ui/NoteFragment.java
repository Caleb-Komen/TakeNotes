package com.architecture.android.takenotes.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.architecture.android.takenotes.BaseApp;
import com.architecture.android.takenotes.R;
import com.architecture.android.takenotes.data.entity.Note;
import com.architecture.android.takenotes.databinding.FragmentNoteBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class NoteFragment extends Fragment {

    private static final String KEY_NOTE_ID = "KEY_NOTE_ID";

    private FragmentNoteBinding mBinding;

    private int mNoteId;

    public static NoteFragment newInstance(int noteId) {
        NoteFragment fragment = new NoteFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_NOTE_ID, noteId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            mNoteId = getArguments().getInt(KEY_NOTE_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_note, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NoteViewModel viewModel = ((BaseApp)requireActivity().getApplication()).getNoteViewModel();
        populateViews(viewModel);
    }

    private void populateViews(NoteViewModel viewModel) {
        viewModel.getNote(mNoteId).observe(getViewLifecycleOwner(), new Observer<Note>() {
            @Override
            public void onChanged(Note note) {
                mBinding.setNote(note);
                mBinding.noteTitle.setText(note.getNoteTitle());
                mBinding.noteContent.setText(note.getNoteContent());
                mBinding.dateCreated.setText(note.getDateCreated().toString());
                mBinding.executePendingBindings();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }
}
