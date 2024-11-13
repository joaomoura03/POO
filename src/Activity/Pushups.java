package Activity;

import User.User;


public class Pushups extends Activity {

    private int reps;


    public Pushups(String name, double time, Difficulty difficulty, User user, int reps) {
        super(name, time, difficulty, user);
        this.reps = reps;
    }


    public Pushups(Pushups pushups) {
        super(pushups);
        this.reps = pushups.reps;
    }

    
    public Pushups clone() {
        return new Pushups(this);
    }


    public int getReps() {
        return reps;
    }


    public void setReps(int reps) {
        this.reps = reps;
    }


    public String toString() {
        return super.toString() + ";" + reps;
    }
}
