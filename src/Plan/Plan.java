package Plan;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import Activity.Activity;

public class Plan implements Serializable {

    private List<Activity> activities;
    private LocalDateTime date;
    private int timesPerWeek;

    
    public List<Activity> getActivities() {
        return activities;
    }


    public int getTimesPerWeek() {
        return timesPerWeek;
    }


    public void setTimesPerWeek(int timesPerWeek) {
        this.timesPerWeek = timesPerWeek;
    }


    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }


    public LocalDateTime getDate() {
        return date;
    }


    public void setDate(LocalDateTime date) {
        this.date = date;
    }


    public Plan(List<Activity> activities, LocalDateTime date, int timesPerWeek) {
        this.activities = activities;
        this.date = date;
        this.timesPerWeek = timesPerWeek;
    }


    public Plan(Plan plan) {
        this.activities = plan.activities;
        this.date = plan.date;
        this.timesPerWeek = plan.timesPerWeek;
    }


    public Plan clone() {
        return new Plan(this);
    }


    public String toString() {
        return activities.toString() + ";" + date + ";" + timesPerWeek;
    }

}
