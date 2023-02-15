package com.example.recyclerviewtry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList <Note> notes = new ArrayList<>();
    private static  NotesAdapter notesAdapter ;
    private MainViewModel mainViewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        recyclerView = findViewById(R.id.recyclerViewNotes);

        getData();

        notesAdapter = new NotesAdapter(notes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(notesAdapter);

        notesAdapter.setNotesOnclickListener(new NotesAdapter.NotesOnclickListener() {
            @Override
            public void onNoteClick(int index) {

            }

            @Override
            public void onLongNoteClick(int index) {
                remove(index);
            }

        });
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                remove(viewHolder.getAdapterPosition());
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);




    }
    public void remove(int position){
        mainViewModel.deleteData(notesAdapter.getNotes().get(position));
        getData();
        notesAdapter.notifyDataSetChanged();
    }

    public void addNoteOnClick(View view) {
        Intent intent = new Intent(this,CreateNewNote.class);
        startActivity(intent);
    }

    public void getData(){

        LiveData<List<Note>> notesFromDB = mainViewModel.getNotes();
        notesFromDB.observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notesFromLiveData) {
                notesAdapter.setNotes(notesFromLiveData);
            }
        });


    }

}