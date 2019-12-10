package com.example.planner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ListActivity extends AppCompatActivity  implements ListFragment.ItemSelected {

    TextView taskName;
    TextView detailText;
    ArrayList<String> details;
    ArrayList<String> names;
    MyDBHandler myDBHandler;
    String taskNameStr;
    Button deleteTaskBtn;


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.list_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.addNoteId) {
            Intent intent = new Intent(ListActivity.this, AddToListActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        details = myDBHandler.getArrayOfTaskDescriptions();
        names = myDBHandler.getArrayOfTaskNames();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        myDBHandler = new MyDBHandler(this,null,null,1);
        details = myDBHandler.getArrayOfTaskDescriptions();
        names = myDBHandler.getArrayOfTaskNames();
        detailText = findViewById(R.id.detailTextId);
        taskName = findViewById(R.id.nameTextId);
        deleteTaskBtn = findViewById(R.id.deleteTaskBtn);
        deleteTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteTask(v);
            }
        });
        if (findViewById(R.id.layout_portrait) != null){
            FragmentManager manager = this.getSupportFragmentManager();
            manager.beginTransaction()
                    .hide(manager.findFragmentById(R.id.detailFrag))
                    .show(manager.findFragmentById(R.id.listFrag))
                    .commit();
        }
        if (findViewById(R.id.layout_land) != null){
            FragmentManager  manager = this.getSupportFragmentManager();
            manager.beginTransaction()
                    .show(manager.findFragmentById(R.id.detailFrag))
                    .show(manager.findFragmentById(R.id.listFrag))
                    .commit();
        }

    }

    @Override
    public void onItemSelected(int index) {
        detailText.setText(details.get(index));
        taskName.setText(names.get(index));
        if (findViewById(R.id.layout_portrait) != null){
            FragmentManager  manager = this.getSupportFragmentManager();
            manager.beginTransaction()
                    .show(manager.findFragmentById(R.id.detailFrag))
                    .hide(manager.findFragmentById(R.id.listFrag))
                    .addToBackStack(null)
                    .commit();
        }
    }

    public void deleteTask(View view) {
        taskName = findViewById(R.id.nameTextId);
        taskNameStr = taskName.getText().toString().trim();
        myDBHandler.deleteTask(taskNameStr);
        FragmentManager manager = this.getSupportFragmentManager();
        finish();
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
        Toast.makeText(this, "Task deleted", Toast.LENGTH_LONG).show();

    }

}
