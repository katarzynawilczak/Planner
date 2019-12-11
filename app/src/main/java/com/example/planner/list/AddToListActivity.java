package com.example.planner.list;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.planner.R;

import androidx.appcompat.app.AppCompatActivity;

public class AddToListActivity extends AppCompatActivity {
    private Button addBtn;
    private EditText nameTxt;
    private EditText descpTxt;
    private MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_list);

        dbHandler = new MyDBHandler(this,null,null,1);
        addBtn = findViewById(R.id.addNoteBtnId);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameTxt = findViewById(R.id.editTaskNameId);
                descpTxt =  findViewById(R.id.editNameDescriptionId);
                String taskName = nameTxt.getText().toString().trim();
                String taskDescp = descpTxt.getText().toString().trim();

                if(taskName.equals("")){
                    Toast.makeText(AddToListActivity.this, "Task name is required", Toast.LENGTH_LONG).show();
                }
                else {
                    Task task = new Task(taskName,taskDescp);
                    dbHandler.addTask(task);
                    Toast.makeText(AddToListActivity.this, "Task added", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });
    }


}
