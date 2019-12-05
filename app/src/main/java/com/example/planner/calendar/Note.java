package com.example.planner.calendar;

public class Note {
    private int id;
    private String noteDate;
    private String noteName;

    public Note(){}

    public Note(String noteDate, String taskDescription) {
        this.noteDate = noteDate;
        this.noteName = taskDescription;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNoteDate(String noteDate) {
        this.noteDate = noteDate;
    }

    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }

    public int getId() {
        return id;
    }

    public String getNoteDate() {
        return noteDate;
    }

    public String getNoteName() {
        return noteName;
    }

}
