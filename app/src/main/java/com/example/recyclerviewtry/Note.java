package com.example.recyclerviewtry;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")
public class Note {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String description;
    private int dayOfTheWeek;
    private int importance;

    @Ignore
    public Note(String title, String description, int dayOfTheWeek, int importance) {
        this.title = title;
        this.description = description;
        this.dayOfTheWeek = dayOfTheWeek;
        this.importance = importance;
    }

    public Note(int id, String title, String description, int dayOfTheWeek, int importance) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dayOfTheWeek = dayOfTheWeek;
        this.importance = importance;
    }


    public int getId() {
        return id;
    }

    public static String getDayById(int id){
        switch (id){
            case 0:
                return "Понедельник";
            case 1 :
                return "Вторник";
            case 2:
                return "Среда";
            case 3 :
                return "Четверг";
            case 4:
                return "Пятница";
            case 5 :
                return "Суббота";
            default:
                return "Воскресенье";
        }

    }
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getImportance() {
        return importance;
    }

    public int getDayOfTheWeek() {
        return dayOfTheWeek;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDayOfTheWeek(int dayOfTheWeek) {
        this.dayOfTheWeek = dayOfTheWeek;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }
}
