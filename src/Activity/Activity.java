package Activity;


import java.io.Serializable;

import User.User;


public class Activity implements Serializable {
    private String name;
    private double time;
    
    public enum Difficulty {
        NORMAL,
        HARD
    }
    
    private Difficulty difficulty;
    private User user;
    
    
    public Activity(String name, double time, Difficulty difficulty, User user) {
        this.name = name;
        this.time = time;
        this.difficulty = difficulty;
        this.user = user;
    }

    
    public Activity(Activity activities) {
        this.name = activities.name;
        this.time = activities.time;
        this.difficulty = activities.difficulty;
        this.user = activities.user;
    }

    
    public Activity clone() {
        return new Activity(this);
    }

    
    public String toString() {
        return name + ";" + time + ";" + difficulty + ";" + user.toString();
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public double getTime() {
        return time;
    }


    public void setTime(double time) {
        this.time = time;
    }


    public Difficulty getDifficulty() {
        return difficulty;
    }


    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }


    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
    }


    public boolean isNormal() {
        return difficulty == Difficulty.NORMAL;
    }


    public boolean isHard() {
        return difficulty == Difficulty.HARD;
    }
    

    public double calories(Activity activity, User user, double hr) {
        double multiplierByType = 0.0;
        if (user.isAmateur()) {
            multiplierByType = 0.1;
        }
        else if (user.isProfessional()) {
            multiplierByType = 0.05;
        }
        else if (user.isOccasional()) {
            multiplierByType = 0.12;
        }

        double time = activity.clone().getTime();

        if (activity.isNormal()) {
            double cal = multiplierByType * hr * time * 0.2;
            return cal;
        } else {
            double cal = multiplierByType * hr * time * 0.1;
            return cal;
        }
    }

}