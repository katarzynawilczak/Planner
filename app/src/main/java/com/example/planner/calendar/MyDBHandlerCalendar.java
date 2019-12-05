package com.example.planner.calendar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.planner.Task;

import java.util.ArrayList;

import androidx.annotation.Nullable;

public class MyDBHandlerCalendar extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="plannerCalendar.db";
    private static final String TABLE_NOTES ="notes";
    private static final String COLUMN_ID="_id";
    private static final String COLUMN_DATE="notedate";
    private static final String COLUMN_NOTE="notename";

    public MyDBHandlerCalendar(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NOTES + "( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_DATE + " TEXT, " + COLUMN_NOTE + " TEXT" +");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NOTES);
        onCreate(db);
    }

    public void addNote(Note note){
        ContentValues values = new ContentValues();
        values.put(COLUMN_DATE, note.getNoteDate());
        values.put(COLUMN_NOTE, note.getNoteName());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_NOTES,null,values);
        db.close();
    }

    public void deleteNote(String note){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(" DELETE FROM " + TABLE_NOTES + " WHERE " + COLUMN_NOTE + "=\"" + note + "\";" );
        db.close();
    }

    public String dataBaseToString(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = " SELECT * FROM " + TABLE_NOTES + " WHERE 1";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        while(!c.isAfterLast()){
            if(c.getString(c.getColumnIndex("notedate")) != null){
                dbString+=c.getString(c.getColumnIndex("notedate"));
                dbString+="\n";
            }
            c.moveToNext();
        }
        db.close();
        return dbString;
    }

    public ArrayList<String> getArrayOfNoteDates(){
        ArrayList<String> dbArray = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        String query = " SELECT * FROM " + TABLE_NOTES + " WHERE 1";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        while(!c.isAfterLast()){
            if(c.getString(c.getColumnIndex("notedate")) != null){
                dbArray.add(c.getString(c.getColumnIndex("notedate")));
            }
            c.moveToNext();
        }
        db.close();
        return dbArray;
    }

    public ArrayList<String> getArrayOfNoteNames(){
        ArrayList<String> dbArray = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        String query = " SELECT * FROM " + TABLE_NOTES + " WHERE 1";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        while(!c.isAfterLast()){
            if(c.getString(c.getColumnIndex("notename")) != null){
                dbArray.add(c.getString(c.getColumnIndex("notename")));
            }
            c.moveToNext();
        }
        db.close();
        return dbArray;
    }
}
