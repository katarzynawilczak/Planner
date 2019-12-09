package com.example.planner.calendar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import com.example.planner.R;

public class EmailActivity extends AppCompatActivity {
    private EditText mEditTextTo;
    private EditText mEditTextSubject;
    private EditText mEditTextMssg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        mEditTextTo = findViewById(R.id.toInput);
        mEditTextSubject = findViewById(R.id.subjectInput);
        mEditTextMssg = findViewById(R.id.mssgInput);

        /*pobierz notatke z kalendarza*/
        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            String textNote = extras.getString("note");
            mEditTextMssg.setText(textNote);
        }


        Button sendBt = findViewById(R.id.sendBtnMail);
        sendBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail();
            }
        });
    }

    private void sendMail() {
        String repList = mEditTextTo.getText().toString(); //list of email addresess
        String[] mailers = repList.split(",");

        String subject = mEditTextSubject.getText().toString();
        String msg = mEditTextMssg.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, mailers);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, msg);

        intent.setType("message/rfc822"); //tylko klineci mailowi
        startActivity(Intent.createChooser(intent, "Choose an email client"));
    }
}
