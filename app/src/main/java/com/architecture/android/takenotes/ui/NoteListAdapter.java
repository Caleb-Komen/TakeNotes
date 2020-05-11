package com.architecture.android.takenotes.ui;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.architecture.android.takenotes.R;
import com.architecture.android.takenotes.data.entity.Note;
import com.architecture.android.takenotes.databinding.NoteItemBinding;

import java.util.ArrayList;
import java.util.List;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.NoteViewHolder> {

    private OnItemClickListener mOnItemClickListener;
    private List<Note> mNotes = new ArrayList<>();

    public NoteListAdapter(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setNotes(final List<Note> notes){
        if (mNotes == null){
            mNotes = notes;
            notifyItemRangeInserted(0, mNotes.size());
        } else {
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return mNotes.size();
                }

                @Override
                public int getNewListSize() {
                    return notes.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return mNotes.get(oldItemPosition).equals(notes.get(newItemPosition));
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Note newNote = notes.get(newItemPosition);
                    Note oldNote = mNotes.get(oldItemPosition);
                    return oldNote.getId() == newNote.getId()
                            && TextUtils.equals(oldNote.getNoteTitle(), newNote.getNoteTitle())
                            && TextUtils.equals(oldNote.getNoteContent(), newNote.getNoteContent())
                            && oldNote.getDateCreated().getTime() == newNote.getDateCreated().getTime();
                }
            });
            mNotes = notes;
            diffResult.dispatchUpdatesTo(this);
        }
    }
    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NoteItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.note_item, parent, false);
        binding.setCallback(mOnItemClickListener);
        return new NoteViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.mBinding.setNote(mNotes.get(position));
        holder.mBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }

    static class NoteViewHolder extends RecyclerView.ViewHolder {
        private NoteItemBinding mBinding;

        public NoteViewHolder(@NonNull NoteItemBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }
}
