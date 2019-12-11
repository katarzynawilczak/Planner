package com.example.planner.schedule;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.planner.calendar.Note;

import java.util.ArrayList;

import androidx.annotation.Nullable;

public class MyDBHandlerSchedule extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="plannerSchedule.db";
    private static final String TABLE_SCHEDULE="classes";
    private static final String COLUMN_ID_SCHEDULE="_id";
    private static final String COLUMN_CLASSNAME_SCHED="classname";
    private static final String COLUMN_DAY_SCHED="dayofweek";
    private static final String COLUMN_TIME_FROM_SCHED ="timefrom";
    private static final String COLUMN_TIME_TO_SCHED ="timetil";

    public MyDBHandlerSchedule(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_SCHEDULE + "( " + COLUMN_ID_SCHEDULE + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_CLASSNAME_SCHED + " TEXT, " + COLUMN_DAY_SCHED + " TEXT, " + COLUMN_TIME_FROM_SCHED + " TEXT, " +
                COLUMN_TIME_TO_SCHED + " TEXT" +");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_SCHEDULE);
        onCreate(db);
    }

    public void addClassToSchedule(OneClass oneClass) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_CLASSNAME_SCHED, oneClass.getClassName());
        values.put(COLUMN_DAY_SCHED, oneClass.getClassDay());
        values.put(COLUMN_TIME_FROM_SCHED, oneClass.getTimeFrom());
        values.put(COLUMN_TIME_TO_SCHED, oneClass.getTimeTo());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_SCHEDULE,null,values);
        db.close();
    }

    public void deleteClassFromSchedule(String className, String day){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(" DELETE FROM " + TABLE_SCHEDULE + " WHERE " + COLUMN_CLASSNAME_SCHED + "=\"" +
                className + "\" AND " + COLUMN_DAY_SCHED + "=\"" + day + "\";" );
        db.close();
    }

    public String dataBaseToString(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = " SELECT * FROM " + TABLE_SCHEDULE + " WHERE 1";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        while(!c.isAfterLast()){
            if(c.getString(c.getColumnIndex("classname")) != null){
                dbString+=c.getString(c.getColumnIndex("classname"));
                dbString+="\n";
            }
            c.moveToNext();
        }
        db.close();
        return dbString;
    }

    public ArrayList<String> getArrayOfClassNames(){
        ArrayList<String> dbArray = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        String query = " SELECT * FROM " + TABLE_SCHEDULE + " WHERE 1";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        while(!c.isAfterLast()){
            if(c.getString(c.getColumnIndex("classname")) != null){
                dbArray.add(c.getString(c.getColumnIndex("classname")));
            }
            c.moveToNext();
        }
        db.close();
        return dbArray;
    }

    public ArrayList<String> getArrayOfClassDays(){
        ArrayList<String> dbArray = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        String query = " SELECT * FROM " + TABLE_SCHEDULE + " WHERE 1";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        while(!c.isAfterLast()){
            if(c.getString(c.getColumnIndex("dayofweek")) != null){
                dbArray.add(c.getString(c.getColumnIndex("dayofweek")));
            }
            c.moveToNext();
        }
        db.close();
        return dbArray;
    }

    public ArrayList<String> getArrayOfTimeFrom(){
        ArrayList<String> dbArray = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        String query = " SELECT * FROM " + TABLE_SCHEDULE + " WHERE 1";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        while(!c.isAfterLast()){
            if(c.getString(c.getColumnIndex("timefrom")) != null){
                dbArray.add(c.getString(c.getColumnIndex("timefrom")));
            }
            c.moveToNext();
        }
        db.close();
        return dbArray;
    }

    public ArrayList<String> getArrayOfTimeTil(){
        ArrayList<String> dbArray = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        String query = " SELECT * FROM " + TABLE_SCHEDULE + " WHERE 1";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        while(!c.isAfterLast()){
            if(c.getString(c.getColumnIndex("timetil")) != null){
                dbArray.add(c.getString(c.getColumnIndex("timetil")));
            }
            c.moveToNext();
        }
        db.close();
        return dbArray;
    }
}
