package Activity;


import User.User;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ActivityInterface implements Serializable {


    private static List<Activity> activities;

    public ActivityInterface() {
        activities = new ArrayList<>();
    }

    @SuppressWarnings("unchecked")
    public static ActivityInterface loadActivities(String filename) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(filename);
        ObjectInputStream ois = new ObjectInputStream(fis);

        ActivityInterface.activities = (List<Activity>) ois.readObject();


        ActivityInterface newDriveIt = (ActivityInterface) ois.readObject();

        return newDriveIt;
    }


    public void saveActivities(String filename) throws IOException {
        FileOutputStream fos = new FileOutputStream(filename);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(activities);
        oos.writeObject(this);

        oos.flush();
        oos.close();
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void addActivity(Scanner sc, User user, String activity, String activitiesFile) {
        System.out.println("Enter name of activity");
        String name = sc.nextLine();

        sc.nextLine();

        System.out.println("Enter the time: ");
        int time = sc.nextInt();

        sc.nextLine();

        Activity.Difficulty difficulty = null;
        boolean validInput = false;
        while (!validInput) {
            System.out.print("Enter difficulty (NORMAL or HARD): ");
            String difficultyStr = sc.nextLine().toUpperCase().trim();
            try {
                difficulty = Activity.Difficulty.valueOf(difficultyStr);
                validInput = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input. Please enter either NORMAL or HARD.");
            }
        }

        switch (activity) {
            case "Pushups":
                System.out.println("Enter the number of repetitions for new pushups:");
                int reps = sc.nextInt();
                activities.add(new Pushups(name, time, difficulty, user, reps));
                break;
            case "Rowing":
                System.out.println("Enter the distance: ");
                double distance = sc.nextDouble();
                activities.add(new Rowing(name, time, difficulty, user, distance));
                break;
            case "Trail":
                System.out.println("Enter the distance: ");
                double distanceTrail = sc.nextDouble();

                System.out.println("Enter the altimeter: ");
                double altimeter = sc.nextDouble();
                activities.add(new Trail(name, time, difficulty, user, distanceTrail, altimeter));
                break;
            case "Weightlifting":
                System.out.println("Enter the reps: ");
                int repsWeight = sc.nextInt();

                System.out.println("Enter the weight: ");
                int weight = sc.nextInt();
                activities.add(new Weightlifting(name, time, difficulty, user, repsWeight, weight));
                break;
        }
        try {
            this.saveActivities(activitiesFile);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printActivities() {
        for (Activity activity : activities) {
            System.out.println(activity.toString());
        }
    }


    private static Activity getActivityByName(String name) {
        for (Activity activity : activities) {
            if (activity.clone().getName().equalsIgnoreCase(name)) {
                return activity;
            }
        }
        return null;
    }


    public void makeActivity(Scanner sc, User user) {
        System.out.println("Enter what activity do you want to realize:");
        String inputName = sc.nextLine();

        sc.nextLine();

        Activity foundActivity = getActivityByName(inputName);

        System.out.println("Enter heart rate:");
        double hr = sc.nextDouble();
        double ahr = user.getAhr();
        user.setAhr((ahr + hr)/2);

        double cal = foundActivity.calories(foundActivity, user, hr);
        System.out.println("Calories: " + cal);
    }
}