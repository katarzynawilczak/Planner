package com.example.planner;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddToCalendarActivity extends AppCompatActivity {
    private EditText date;
    private EditText note;
    private Button addBtn;
    //private MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_list);

        //dbHandler = new MyDBHandler(this,null,null,1);
//        addBtn = findViewById(R.id.noteId);
//
//        addBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                date = findViewById(R.id.dateId);
//                note =  findViewById(R.id.note);
//                String taskDate = date.getText().toString().trim();
//                String taskNote = note.getText().toString().trim();
//
//                try {
//                    String[] dzienMiesiacRok = taskDate.split(".");
//                    Integer.parseInt(dzienMiesiacRok[0]);
//                    Integer.parseInt(dzienMiesiacRok[1]);
//                    Integer.parseInt(dzienMiesiacRok[2]);
//                } catch (Exception e) {
//                    Toast.makeText(AddToCalendarActivity.this, "Wrong date format", Toast.LENGTH_LONG).show();
//                }
//
//                if(taskNote.equals("")){
//                    Toast.makeText(AddToCalendarActivity.this, "Note is required", Toast.LENGTH_LONG).show();
//                }
//                else {
//                    Task task = new Task(taskDate,taskNote);
//                    //dbHandler.addTask(task);
//                    Toast.makeText(AddToCalendarActivity.this, "Please add this note to the database! It is not implemented", Toast.LENGTH_LONG).show();
//                    finish();
//                }
//            }
//        });
    }
}
