package UI;

import User.User;
import User.UserInterface;
import Activity.ActivityInterface;
import Plan.PlanInterface;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class TextUI {

    private UserInterface userInterface;
    private ActivityInterface activityInterface;
    private PlanInterface planInterface;
    private String usersFile;
    private String activitiesFile;
    private String planFile;

    private Scanner sc;

    public TextUI() {
        this.userInterface = new UserInterface();
        this.activityInterface = new ActivityInterface();
        this.planInterface = new PlanInterface();
        this.usersFile = Paths.get("POO", "GrupoTP-22", "data", "users.dat").toAbsolutePath().toString();
        this.activitiesFile = Paths.get("POO", "GrupoTP-22", "data", "activities.dat").toAbsolutePath().toString();
        this.planFile = Paths.get("POO", "GrupoTP-22", "data", "plan.dat").toAbsolutePath().toString();
        try {
            this.userInterface = UserInterface.loadUsers(this.usersFile);
            this.activityInterface = ActivityInterface.loadActivities(this.activitiesFile);
            this.planInterface = PlanInterface.loadPlan(this.planFile);
        }
        catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        sc = new Scanner(System.in);
    }
    
    public void run() {
        NewMenu menu = new NewMenu(new String[] {
                " Login User",
                " Register User"
        });
        
        menu.setHandler(1, () -> loginUser());
        menu.setHandler(2, () -> registerUser());
        
        menu.run();
    }


    private void loginUser() { 
        System.out.println("Enter Username:");
        String inputName = sc.nextLine();

        System.out.println("Enter Password:");
        String inputPassword = sc.nextLine();
        User userlogedin;
        if (userInterface.authenticate(inputName, inputPassword)) {
            System.out.println("Login successful!\n");
            userlogedin = userInterface.getUser(inputName, inputPassword);
        } else {
            System.out.println("Login failed: Incorrect username or password.");
            return ;
        }

        NewMenu loggedMenu = new NewMenu(new String[] {
                " Add Activity",
                " Add Plan",
                " Make Activity",
                " Print Activities",
                " Print User",
                " Print Plan"
        });

        loggedMenu.setHandler(1, () -> addActivity(userlogedin));
        loggedMenu.setHandler(2, () -> addPlan(userlogedin));
        loggedMenu.setHandler(3, () -> makeActivity(userlogedin));
        loggedMenu.setHandler(4, () -> activityInterface.printActivities());
        loggedMenu.setHandler(5, () -> userInterface.printUser());
        loggedMenu.setHandler(6, () -> planInterface.printPlan());

        loggedMenu.run();
    }

    private void addActivity(User userlogedin) {
        NewMenu activityMenu = new NewMenu(new String[] {
            " Pushups",
            " Rowing",
            " Trail",
            " Weightlifiting"
        });

        activityMenu.setHandler(1, () -> activityInterface.addActivity(sc, userlogedin, "Pushups", activitiesFile));
        activityMenu.setHandler(2, () -> activityInterface.addActivity(sc, userlogedin, "Rowing", activitiesFile));
        activityMenu.setHandler(3, () -> activityInterface.addActivity(sc, userlogedin, "Trail", activitiesFile));
        activityMenu.setHandler(4, () -> activityInterface.addActivity(sc, userlogedin, "Weightlifting", activitiesFile));

        activityMenu.run();
    }

    private void registerUser() {
        userInterface.registerUser(sc);
        try {
            userInterface.saveUsers(usersFile);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void addPlan(User userlogedin) {
        planInterface.addPlan(sc, userlogedin, planFile);
    }

    private void makeActivity(User userlogedin) {
        activityInterface.makeActivity(sc, userlogedin);
    }
}