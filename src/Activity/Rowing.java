package Activity;

import User.User;


public class Rowing extends Activity {
    
    private double distance; //In km


    public Rowing(String name, double time, Difficulty difficulty, User user, double distance) {
        super(name, time, difficulty, user);
        this.distance = distance;
    }


    public Rowing(Rowing rowing) {
        super(rowing);
        this.distance = rowing.distance;
    }


    public Rowing clone() {
        return new Rowing(this);
    }


    public double getDistance() {
        return distance;
    }


    public void setDistance(double distance) {
        this.distance = distance;
    }


    public String toString() {
        return super.toString() + ";" + distance;
    }
}
