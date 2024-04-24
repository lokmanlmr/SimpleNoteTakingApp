package com.example.takenotes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.takenotes.databinding.RecyclerViewItemLayoutBinding;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.MyViewHolder> {
    private ArrayList<Note> allNotes ;
    private NoteClickedInterface noteClickedInterface;

    public NoteAdapter(ArrayList<Note> allNotes, NoteClickedInterface noteClickedInterface) {
        this.allNotes = allNotes;
        this.noteClickedInterface = noteClickedInterface;
        notifyDataSetChanged();
    }

    public void setAllNotes(ArrayList<Note> allNotes) {
        this.allNotes = allNotes;
    }

    @NonNull
    @Override
    public NoteAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerViewItemLayoutBinding recyclerViewItemLayoutBinding= DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext())
                        ,R.layout.recycler_view_item_layout,
                        parent,
                        false
                );
        return new MyViewHolder(recyclerViewItemLayoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.MyViewHolder holder, int position) {
            holder.recyclerViewItemLayoutBinding.setNoteItem(allNotes.get(position));

            holder.recyclerViewItemLayoutBinding.RVitem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    noteClickedInterface.onNoteClicked(allNotes.get(position),position);
                }
            });
    }

    @Override
    public int getItemCount() {
        if(allNotes!=null){
            return allNotes.size();
        }
        else{
            return 0;
        }
    }

     static class MyViewHolder extends RecyclerView.ViewHolder {

         private RecyclerViewItemLayoutBinding recyclerViewItemLayoutBinding;

         public MyViewHolder(RecyclerViewItemLayoutBinding recyclerViewItemLayoutBinding) {
             super(recyclerViewItemLayoutBinding.getRoot());
             this.recyclerViewItemLayoutBinding = recyclerViewItemLayoutBinding;

         }
    }
}
