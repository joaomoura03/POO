package Activity;

import User.User;


public class Trail extends Activity {
    
    private double distance; //In km
    private double altimeter; // In meters
    

    public Trail(String name, double time, Difficulty difficulty, User user, double distance, double altimeter) {
        super(name, time, difficulty, user);
        this.distance = distance;
        this.altimeter = altimeter;
    }
    

    public Trail(Trail trail) {
        super(trail);
        this.distance = trail.distance;
        this.altimeter = trail.altimeter;
    }
    

    public Trail clone() {
        return new Trail(this);
    }
    

    public double getDistance() {
        return distance;
    }
    

    public void setDistance(double distance) {
        this.distance = distance;
    }
    

    public double getAltimeter() {
        return altimeter;
    }
    

    public void setAltimeter(double altimeter) {
        this.altimeter = altimeter;
    }

    public String toString() {
        return super.toString() + ";" + distance + ";" + altimeter;
    }
}
