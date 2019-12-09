package com.example.planner.calendar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.planner.MainActivity;
import com.example.planner.MyDBHandler;
import com.example.planner.R;
import com.example.planner.schedule.ScheduleActivity;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class CalendarActivity extends AppCompatActivity {
    private TextView dataView;
    private CompactCalendarView calendarView;
    private  TextView monthView;
    ArrayList<String> dates;
    ArrayList<String> names;
    private Date visiblePosition = Calendar.getInstance().getTime();
    private MyDBHandlerCalendar myDBHandler;
    private Button sendButton;
    private Switch wifiSwitch;
    private String dailyNote; //notatka która wysyła sie mailem

    /*broadcast receiver do wifi */
    private BroadcastReceiver wifiBroad = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, WifiManager.WIFI_STATE_UNKNOWN);

            switch (wifiState) {
                case WifiManager.WIFI_STATE_ENABLED:
                    wifiSwitch.setChecked(true);
                    wifiSwitch.setText("Wifi is on");
                    break;
                case WifiManager.WIFI_STATE_DISABLED:
                    wifiSwitch.setChecked(false);
                    wifiSwitch.setText("Wifi is off");
                    break;
            }
        }
    };

    /* register wifi receiver */
    @Override
    protected void onStart() {
      super.onStart();
      IntentFilter intentFilter = new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION);
      registerReceiver(wifiBroad, intentFilter);
    }

    /* unregister receiver */
    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(wifiBroad);
    }

    /* prepare for email sending */
    private void prepMail() {
        if (wifiSwitch.isChecked()) {
            Intent intent = new Intent(this, EmailActivity.class);
            intent.putExtra("note",dailyNote);
            startActivity(intent);
        } else {
            Toast.makeText(CalendarActivity.this, "Wifi is off, cannot send mail", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        //send button init and listener
        sendButton = findViewById(R.id.btnSend);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prepMail();
            }
        });

        dataView = findViewById(R.id.currentData);
        Date currentTime = Calendar.getInstance().getTime();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL, Locale.ENGLISH).format(currentTime);
        dataView.setText(currentDate);
        setCurrentMonth(currentTime);

        setNotesInCalendar();

        final Date date = new GregorianCalendar(2019, 11, 4).getTime();
        Event ev = new Event(Color.GREEN, date.getTime(), "My note");
        calendarView.addEvent(ev);
        calendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                visiblePosition = dateClicked;
                List<Event> events = calendarView.getEvents(dateClicked);
                if (!events.isEmpty()) {
                    dailyNote = events.get(0).getData().toString();
                    Toast.makeText(CalendarActivity.this, "Note: " + events.get(0).getData(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                setCurrentMonth(firstDayOfNewMonth);
            }
        });

        wifiSwitch = findViewById(R.id.switchWifi);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        dates = myDBHandler.getArrayOfNoteDates();
        names = myDBHandler.getArrayOfNoteNames();
    }

    public void setNotesInCalendar() {
        myDBHandler = new MyDBHandlerCalendar(this,null,null,1);
        dates = myDBHandler.getArrayOfNoteDates();
        names = myDBHandler.getArrayOfNoteNames();

        calendarView = findViewById(R.id.calendarId);
        calendarView.setLocale(TimeZone.getDefault(), Locale.ENGLISH);

        for (int i = 0; i < dates.size(); i++) {
            int day = Integer.parseInt(dates.get(i).substring(0, 2));
            int month = Integer.parseInt(dates.get(i).substring(3, 5));
            int year = Integer.parseInt(dates.get(i).substring(6, 10));

            Date date = new GregorianCalendar(year, month-1, day).getTime();
            Event ev = new Event(Color.GREEN, date.getTime(), names.get(i));
            calendarView.addEvent(ev);
        }
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

    @Override
    protected void onResume() {
        super.onResume();
        setNotesInCalendar();
    }
}
