package Activity;

import User.User;


public class Weightlifting extends Activity {
    
    private int reps;
    private int weight; //In kg


    public Weightlifting(String name, double time, Difficulty difficulty, User user, int reps, int weight) {
        super(name, time, difficulty, user);
        this.reps = reps;
        this.weight = weight;
    }
    

    public Weightlifting(Weightlifting weightlifting) {
        super(weightlifting);
        this.reps = weightlifting.reps;
        this.weight = weightlifting.weight;
    }
    

    public Weightlifting clone() {
        return new Weightlifting(this);
    }
    

    public int getReps() {
        return reps;
    }
    

    public void setReps(int reps) {
        this.reps = reps;
    }
    

    public int getWeight() {
        return weight;
    }
    

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String toString() {
        return super.toString() + ";" + reps + ";" + weight;
    }
}
