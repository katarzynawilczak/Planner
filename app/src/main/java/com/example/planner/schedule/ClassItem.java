package com.example.planner.schedule;

public class ClassItem {
    public String className;
    public String classFrom;
    public String classTil;

    public ClassItem(String cname, String cFrom, String cTil) {
        this.className = cname;
        this.classFrom = cFrom;
        this.classTil = cTil;
    }

    public String getClassName() {
        return this.className;
    }

    public void setClassName(String cname) {
        this.className = cname;
    }
    public String getFrom() {
        return this.classFrom;
    }

    public void setClassFrom(String cfrom) {
        this.classFrom = cfrom;
    }

    public String getTil() {
        return this.classTil;
    }

    public void setClassTilTil(String ctil) {
        this.classTil = ctil;
    }
}
