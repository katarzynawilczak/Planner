package com.example.planner;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import androidx.appcompat.app.AppCompatActivity;

public class CalendarActivity extends AppCompatActivity {
    private TextView dataView;
    private CompactCalendarView calendarView;
    private  TextView monthView;
    private Date visiblePosition = Calendar.getInstance().getTime();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        dataView = findViewById(R.id.currentData);
        Date currentTime = Calendar.getInstance().getTime();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL, Locale.ENGLISH).format(currentTime);
        dataView.setText(currentDate);
        setCurrentMonth(currentTime);

        calendarView = findViewById(R.id.calendarId);
        calendarView.setLocale(TimeZone.getDefault(),Locale.ENGLISH);

        final Date date = new GregorianCalendar(2019, 11, 4).getTime();
        Event ev = new Event(Color.GREEN, date.getTime(), "My note");
        calendarView.addEvent(ev);
        calendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                visiblePosition = dateClicked;
                List<Event> events = calendarView.getEvents(dateClicked);
                if (!events.isEmpty())
                    Toast.makeText(CalendarActivity.this, "Note: "  + events.get(0).getData(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                setCurrentMonth(firstDayOfNewMonth);
            }
        });

    }

    public void setCurrentMonth(Date currentTime){
        monthView =  findViewById(R.id.monthTextViewId);
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentTime);
        final int month = cal.get(Calendar.MONTH);
        String[] monthTab = {"January","February",
                "March", "April", "May", "June", "July",
                "August", "September", "October", "November",
                "December"};
        String monthTxt = monthTab[month];
        monthView.setText(monthTxt);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.calendar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.addCalendarNoteId) {
            Intent intent = new Intent(CalendarActivity.this, AddToCalendarActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
