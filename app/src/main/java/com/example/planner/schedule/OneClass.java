package com.example.planner.schedule;

public class OneClass {
    private int id;
    private String className;
    private String classDay;
    private String timeFrom;
    private String timeTo;

    public OneClass(){}

    public OneClass(String className, String classDay, String timeFrom, String timeTo) {
        this.className = className;
        this.classDay = classDay;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setClassDay(String classDay) { this.classDay = classDay; }

    public void setTimeFrom(String timeFrom) { this.timeFrom = timeFrom; }

    public void setTimeTo(String timeTo) { this.timeTo = timeTo; }


    public int getId() {
        return id;
    }

    public String getClassName() {
        return className;
    }

    public String getClassDay() { return classDay; }

    public String getTimeFrom() { return timeFrom; }

    public String getTimeTo() { return timeTo; }

}
