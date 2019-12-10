package com.example.planner.schedule;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.planner.MyDBHandler;
import com.example.planner.R;
import com.google.android.material.chip.Chip;

public class AddClass extends AppCompatActivity {

    private Chip chMon, chTue, chWed, chThur, chFri;
    private Button saveBtn;
    //do zapisu class
    private String className, classFrom, classTil;
    private String classDay;
    private EditText cName, cFrom, cTil;
    private MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);

        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        dbHandler = new MyDBHandler(this,null,null,1);

        chMon = findViewById(R.id.chipMon);
        chTue = findViewById(R.id.chipTue);
        chWed = findViewById(R.id.chipWed);
        chThur = findViewById(R.id.chipThur);
        chFri = findViewById(R.id.chipFri);
        saveBtn = findViewById(R.id.btnSave);

        cName = findViewById(R.id.edClass);
        cFrom = findViewById(R.id.edFrom);
        cTil = findViewById(R.id.edTil);

        chMon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearChips();
                chMon.setSelected(true);
            }
        });

        chTue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearChips();
                chTue.setSelected(true);
            }
        });

        chWed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearChips();
                chWed.setSelected(true);
            }
        });

        chThur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearChips();
                chThur.setSelected(true);
            }
        });

        chFri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearChips();
                chFri.setSelected(true);
            }
        });

        /* save class to db and return */
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (chMon.isSelected()) {
                   classDay = "Monday";
               } else if (chTue.isSelected()) {
                   classDay = "Tuesday";
               } else if (chWed.isSelected()) {
                   classDay = "Wednesday";
               } else if (chThur.isSelected()) {
                   classDay = "Thursday";
               } else if (chFri.isSelected()) {
                   classDay = "Friday";
               } else {
                   classDay = "Monday";
               }

               className = cName.getText().toString();
               classFrom = cFrom.getText().toString();
               classTil  = cTil.getText().toString();

                Toast.makeText(AddClass.this, className + " " + classFrom + " " + classTil, Toast.LENGTH_LONG).show();

                OneClass oneClass = new OneClass(className, classDay, classFrom, classTil);
                dbHandler.addClassToSchedule(oneClass);
            }
        });
    }

    private void clearChips() {
        chMon.setSelected(false);
        chTue.setSelected(false);
        chWed.setSelected(false);
        chThur.setSelected(false);
        chFri.setSelected(false);
    }
}
