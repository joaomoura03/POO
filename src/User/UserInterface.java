package User;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import User.User.TypeOfUser;


public class UserInterface implements Serializable {
    
    private static List<User> users;


    public UserInterface() {
        users = new ArrayList<>();
    }


    @SuppressWarnings("unchecked")
    public static UserInterface loadUsers(String filename) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(filename);
        ObjectInputStream ois = new ObjectInputStream(fis);
        
        UserInterface.users = (List<User>) ois.readObject();


        UserInterface newUserI = (UserInterface) ois.readObject();

        return newUserI;
    }


    public void saveUsers(String filename) throws IOException {
        FileOutputStream fos = new FileOutputStream(filename);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        
        oos.writeObject(users);
        oos.writeObject(this);
        
        oos.flush();
        oos.close();
    }


    private int getLastUserCode() {
        if (users.isEmpty()) {
            return 0;
        }
        return users.size()-1;
    }


    public void registerUser(Scanner sc) {
        System.out.print("Name: ");
        String name = sc.nextLine();

        System.out.println("Password: ");
        String password = sc.nextLine();

        System.out.print("Residence: ");
        String residence = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        TypeOfUser typeOfUser = null;
        boolean validInput = false;
        while (!validInput) {
            System.out.print("Type of User (AMATEUR, PROFESSIONAL, OCCASIONAL): ");
            String typeOfUserStr = sc.nextLine().toUpperCase().trim();
            try {
                typeOfUser = TypeOfUser.valueOf(typeOfUserStr);
                validInput = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input");
            }
        }

        int newCode = getLastUserCode();

        User user = new User(newCode, name, password, residence, email, 0.0, typeOfUser);

        users.add(user);

    }


    public boolean authenticate(String name, String password) {
        return users.stream()
                    .anyMatch(user -> user.checkCredentials(name, password));
    }

    
    public User getUser(String name, String password) {
        for (User user : users) {
            if (user.clone().getName().equals(name) && user.clone().getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public void printUser() {
        for (User user : users) {
            System.out.println(user.toString());
        }
    }
}