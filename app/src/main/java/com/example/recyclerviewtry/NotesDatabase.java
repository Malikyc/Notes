package com.example.recyclerviewtry;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Note.class},version = 1,exportSchema = false)
public abstract class NotesDatabase extends RoomDatabase {
    private static  NotesDatabase notesDatabase;
    private static final String DB_NAME = "notes.db2";
    private static final Object LOCK = new Object();

    public static NotesDatabase getInstanceOf(Context context){
        synchronized (LOCK){
        if(notesDatabase==null){
            notesDatabase = Room.databaseBuilder(context, NotesDatabase.class, DB_NAME)
                    .build();
        }
        }
        return notesDatabase;

    }
    public abstract NoteDao noteDao();

}
