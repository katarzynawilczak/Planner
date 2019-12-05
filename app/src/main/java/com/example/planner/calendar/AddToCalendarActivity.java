package com.example.planner.calendar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.planner.MyDBHandler;
import com.example.planner.R;
import com.example.planner.Task;

import androidx.appcompat.app.AppCompatActivity;

public class AddToCalendarActivity extends AppCompatActivity {
    private EditText date;
    private EditText note;
    private Button addBtn;
    private MyDBHandlerCalendar dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_calendar);

        dbHandler = new MyDBHandlerCalendar(this,null,null,1);
        addBtn = findViewById(R.id.noteId);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date = findViewById(R.id.dateId);
                note =  findViewById(R.id.note);
                String noteDate = date.getText().toString().trim();
                String noteName = note.getText().toString().trim();

                try {
                    int day = Integer.parseInt(noteDate.substring(0, 2));
                    int month = Integer.parseInt(noteDate.substring(3, 5));
                    int year = Integer.parseInt(noteDate.substring(6, 10));

                    if (day > 31 || month > 12 || year < 2000 || year > 2050) {
                        throw new Exception();
                    }

                    if(noteName.equals("")){
                        Toast.makeText(AddToCalendarActivity.this, "Note is required", Toast.LENGTH_LONG).show();
                    }
                    else {
                        Note note = new Note(noteDate,noteName);
                        dbHandler.addNote(note);
                        Toast.makeText(AddToCalendarActivity.this, "Note added", Toast.LENGTH_LONG).show();
                        finish();
                    }
                } catch (Exception e) {
                    Toast.makeText(AddToCalendarActivity.this, "Wrong date format", Toast.LENGTH_LONG).show();
                }


            }
        });
    }
}
