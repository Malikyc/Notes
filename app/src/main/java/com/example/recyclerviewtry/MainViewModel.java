package com.example.recyclerviewtry;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MainViewModel extends AndroidViewModel {



    private static NotesDatabase notesDatabase;
    private LiveData<List<Note>> notes;


    public MainViewModel(@NonNull Application application) {
        super(application);
        notesDatabase = NotesDatabase.getInstanceOf(getApplication());
        notes = notesDatabase.noteDao().allnotes();
    }
    public LiveData<List<Note>> getNotes() {
        return notes;
    }

    public void insertData (Note note){
          new InsertTask().execute(note);
    }

    public void deleteData (Note note){
        new DeleteTask().execute(note);
    }

    public void DeleteAllNotes(){
     new DeleteAllTask().execute();
    }


    public static class InsertTask extends AsyncTask<Note,Void,Void>{

        @Override
        protected Void doInBackground(Note... notes) {
         if(notes != null && notes.length>0){
             notesDatabase.noteDao().insertIntoDB(notes[0]);
         }
            return null;
        }
    }

    public static class DeleteTask extends AsyncTask<Note,Void,Void>{

        @Override
        protected Void doInBackground(Note... notes) {
            if(notes != null && notes.length>0){
                notesDatabase.noteDao().deleteFromDB(notes[0]);
            }
            return null;
        }
    }
    public static class DeleteAllTask extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... notes) {
            notesDatabase.noteDao().deleteAllNotes();
            return null;
        }
    }

}
