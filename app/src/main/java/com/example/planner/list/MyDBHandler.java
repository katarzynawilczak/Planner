package com.example.planner.list;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import androidx.annotation.Nullable;

public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="planner.db";
    private static final String TABLE_TASKS="tasks";
    private static final String COLUMN_ID="_id";
    private static final String COLUMN_TASKNAME="taskname";
    private static final String COLUMN_TASKDESCP="taskdescp";

    public MyDBHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_TASKS + "( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_TASKNAME + " TEXT, " + COLUMN_TASKDESCP + " TEXT" +");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_TASKS);
        onCreate(db);
    }

    public void addTask(Task task){
        ContentValues values = new ContentValues();
        values.put(COLUMN_TASKNAME, task.getTaskName());
        values.put(COLUMN_TASKDESCP, task.getTaskDescription());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_TASKS,null,values);
        db.close();
    }

    public void deleteTask(String task){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(" DELETE FROM " + TABLE_TASKS + " WHERE " + COLUMN_TASKNAME + "=\"" + task + "\";" );
        db.close();
    }

    public String dataBaseToString(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = " SELECT * FROM " + TABLE_TASKS + " WHERE 1";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        while(!c.isAfterLast()){
            if(c.getString(c.getColumnIndex("taskname")) != null){
                dbString+=c.getString(c.getColumnIndex("taskname"));
                dbString+="\n";
            }
            c.moveToNext();
        }
        db.close();
        return dbString;
    }

    public ArrayList<String> getArrayOfTaskNames(){
        ArrayList<String> dbArray = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        String query = " SELECT * FROM " + TABLE_TASKS + " WHERE 1";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        while(!c.isAfterLast()){
            if(c.getString(c.getColumnIndex("taskname")) != null){
                dbArray.add(c.getString(c.getColumnIndex("taskname")));
            }
            c.moveToNext();
        }
        db.close();
        return dbArray;
    }

    public ArrayList<String> getArrayOfTaskDescriptions(){
        ArrayList<String> dbArray = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        String query = " SELECT * FROM " + TABLE_TASKS + " WHERE 1";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        while(!c.isAfterLast()){
            if(c.getString(c.getColumnIndex("taskdescp")) != null){
                dbArray.add(c.getString(c.getColumnIndex("taskdescp")));
            }
            c.moveToNext();
        }
        db.close();
        return dbArray;
    }
}
