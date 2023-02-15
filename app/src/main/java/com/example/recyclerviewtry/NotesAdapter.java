package com.example.recyclerviewtry;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter  extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder>{


    private List<Note> notes;

    public void setNotesOnclickListener(NotesOnclickListener notesOnclickListener) {
        this.notesOnclickListener = notesOnclickListener;
    }

    private NotesOnclickListener notesOnclickListener;

    public NotesAdapter(List<Note> notes) {
        this.notes = notes;
    }
    public void setNotes(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }
    public List<Note> getNotes(){
        return this.notes;
    }
    interface NotesOnclickListener{
        void onNoteClick(int index);
        void onLongNoteClick(int index);
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.textViewTitle.setText(note.getTitle());
        holder.textViewDescription.setText(note.getDescription());
        holder.textViewDayOfTheWeek.setText(Note.getDayById(note.getDayOfTheWeek()));
        int importance = note.getImportance();
        int colorId;
        switch (importance){
            case 1:
                colorId = holder.itemView.getResources().getColor(android.R.color.holo_red_light);
                break;
            case 2:
                colorId = holder.itemView.getResources().getColor(android.R.color.holo_orange_light);
                break;
            default:
                colorId = holder.itemView.getResources().getColor(android.R.color.holo_green_light);
        }
        holder.textViewTitle.setBackgroundColor(colorId);


    }


    @Override
    public int getItemCount() {
        return notes.size();
    }

    class NotesViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewTitle;
        private TextView textViewDescription;
        private TextView textViewDayOfTheWeek;

        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            textViewDayOfTheWeek = itemView.findViewById(R.id.textViewDayOfTheWeek);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(notesOnclickListener != null){
                      notesOnclickListener.onNoteClick(getAdapterPosition());
                    }
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if(notesOnclickListener != null){
                        notesOnclickListener.onLongNoteClick(getAdapterPosition());
                    }
                    return true;
                }
            });
        }
    }
}
