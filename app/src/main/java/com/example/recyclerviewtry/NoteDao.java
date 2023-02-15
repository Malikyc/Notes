package com.example.recyclerviewtry;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
interface NoteDao {
    @Query("SELECT * FROM notes ORDER BY dayOfTheWeek ASC")
   LiveData<List<Note>> allnotes();

    @Delete
    void deleteFromDB(Note note);

    @Insert
    void insertIntoDB(Note note);

    @Query("DELETE FROM notes")
    void deleteAllNotes();
}
