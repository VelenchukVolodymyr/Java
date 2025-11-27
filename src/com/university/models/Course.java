package com.university.models;

import ua.util.Utils;

public class Course {
    private String title;    
    private int credits;

    public Course(String title, int credits) {
        Utils.validateName(title, "Title");
        Utils.validateCredits(credits);
        
        this.title = Utils.formatName(title);
        this.credits = credits;
    }

    public static Course of(String title, int credits) {
        return new Course(title, credits);
    }

    public String getTitle() { return title; }
    public int getCredits() { return credits; }
    
    @Override
    public String toString() {
        return "Course[" + title + ", " + credits + " credits]";
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Course)) return false;
        Course other = (Course) obj;
        return title.equals(other.title) && credits == other.credits;
    }
    
    @Override
    public int hashCode() {
        return title.hashCode() + credits;
    }
}
