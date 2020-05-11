package com.architecture.android.takenotes.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.architecture.android.takenotes.R;
import com.architecture.android.takenotes.data.entity.Note;
import com.architecture.android.takenotes.databinding.FragmentNoteListBinding;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NoteListFragment extends Fragment {

    private FragmentNoteListBinding mBinding;
    private NoteListAdapter mAdapter;

    public NoteListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_note_list, container, false);

        mAdapter = new NoteListAdapter(onItemClickListener);
        mBinding.notesRecyclerView.setAdapter(mAdapter);

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NoteViewModel viewModel = new ViewModelProvider(this).get(NoteViewModel.class);
        viewModel.getNotes().observe(getViewLifecycleOwner(), new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                if (notes != null) {
                    mAdapter.setNotes(notes);
                    mBinding.setIsVisible(true);
                } else{
                    mBinding.setIsVisible(false);
                }
                mBinding.executePendingBindings();
            }
        });
    }

    private OnItemClickListener onItemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(Note note) {
            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                ((MainActivity) requireActivity()).showFragment(note.getId());
            }
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
        mAdapter = null;
    }
}
