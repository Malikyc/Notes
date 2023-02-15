package com.example.recyclerviewtry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class CreateNewNote extends AppCompatActivity {
    private EditText setTitle;
    private EditText setDescription;
    private Spinner dayOfTheWeekSpinner;
    private RadioGroup radioGroup;
    private MainViewModel mainViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_note);

        setTitle = findViewById(R.id.setTitle);
        setDescription = findViewById(R.id.setDescription);
        dayOfTheWeekSpinner = findViewById(R.id.daysOfTheWeekSpinner);
        radioGroup = findViewById(R.id.radioGroup);

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);


    }

    public void addNote(View view) {
        String title = setTitle.getText().toString();
        String description = setDescription.getText().toString();
        int dayOfTheWeek = dayOfTheWeekSpinner.getSelectedItemPosition();
        int checked = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(checked);
        int importance = Integer.parseInt(radioButton.getText().toString());
        if(isFilled(title,description)){
        Note note = new Note(title,description,dayOfTheWeek,importance);
        mainViewModel.insertData(note);
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);}
        else{
            Toast.makeText(getApplicationContext(),"Введите не заполненые поля",Toast.LENGTH_SHORT).show();
        }

    }

    private boolean isFilled(String title,String description){
        return  !title.isEmpty() && !description.isEmpty();
    }
}