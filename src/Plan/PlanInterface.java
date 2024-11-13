package Plan;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Activity.Activity;
import Activity.Pushups;
import Activity.Rowing;
import Activity.Trail;
import Activity.Weightlifting;
import User.User;


public class PlanInterface implements Serializable {
    private static List<Plan> plans;


    public PlanInterface() {
        plans = new ArrayList<>();
    }


    @SuppressWarnings("unchecked")
    public static PlanInterface loadPlan(String filename) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(filename);
        ObjectInputStream ois = new ObjectInputStream(fis);

        PlanInterface.plans = (List<Plan>) ois.readObject();


        PlanInterface newPlanIt = (PlanInterface) ois.readObject();

        return newPlanIt;
    }


    public void savePlan(String filename) throws IOException {
        FileOutputStream fos = new FileOutputStream(filename);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(plans);
        oos.writeObject(this);

        oos.flush();
        oos.close();
    }


    public void addPlan(Scanner sc, User user, String filename) {
        List<Activity> activities = new ArrayList<>();
        System.out.println("How many activities do you want to add?");
        int number = sc.nextInt();
        
        for(int iterator = 0; iterator < number; iterator++) {
            System.out.println("What activity do you want to add? ");
            System.out.println(" 1 - Pushups");
            System.out.println(" 2 - Rowing");
            System.out.println(" 3 - Trail");
            System.out.println(" 4 - Weightlifiting");
            sc.nextLine();

            String activity = sc.nextLine();


            System.out.println("Enter name of activity");
            String name = sc.nextLine();


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
                case "1":
                    System.out.println("Enter the number of repetitions for new pushups:");
                    int reps = sc.nextInt();
                    activities.add(new Pushups(name, time, difficulty, user, reps));
                    break;
                case "2":
                    System.out.println("Enter the distance: ");
                    double distance = sc.nextDouble();
                    activities.add(new Rowing(name, time, difficulty, user, distance));
                    break;
                case "3":
                    System.out.println("Enter the distance: ");
                    double distanceTrail = sc.nextDouble();
    
                    System.out.println("Enter the altimeter: ");
                    double altimeter = sc.nextDouble();
                    activities.add(new Trail(name, time, difficulty, user, distanceTrail, altimeter));
                    break;
                case "4":
                    System.out.println("Enter the reps: ");
                    int repsWeight = sc.nextInt();
    
                    System.out.println("Enter the weight: ");
                    int weight = sc.nextInt();
                    activities.add(new Weightlifting(name, time, difficulty, user, repsWeight, weight));
                    break;
            }
        }

        LocalDateTime date = LocalDateTime.now();

        System.out.println("How many times per week do you do this plan? ");
        int timesPerWeek = sc.nextInt();

        plans.add(new Plan(activities, date, timesPerWeek));

        try {
            this.savePlan(filename);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    public void printPlan() {
        for (Plan plan : plans) {
            System.out.println(plan.toString());
        }
    }
}
