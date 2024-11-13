package User;

import java.io.Serializable;

public class User implements Serializable {
    
    private int code;
    private String name;
    private String password;
    private String residence;
    private String email;
    private double ahr;
    public enum TypeOfUser {
        AMATEUR,
        PROFESSIONAL,
        OCCASIONAL
    }
    private TypeOfUser typeofuser;


    public User(int code, String name, String password, String residence, String email, double ahr, TypeOfUser typeofuser) {
        this.code = code;
        this.name = name;
        this.password = password;
        this.residence = residence;
        this.email = email;
        this.ahr = ahr;
        this.typeofuser = typeofuser;
    }


    public User(User user) {
        this.code = user.code;
        this.name = user.name;
        this.password = user.password;
        this.residence = user.residence;
        this.email = user.email;
        this.ahr = user.ahr;
        this.typeofuser = user.typeofuser;
    }


    public User clone() {
        return new User(this);
    }


    public int getCode() {
        return code;
    }


    public void setCode(int code) {
        this.code = code;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public String getResidence() {
        return residence;
    }


    public void setResidence(String residence) {
        this.residence = residence;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public double getAhr() {
        return ahr;
    }


    public void setAhr(double ahr) {
        this.ahr = ahr;
    }


    public TypeOfUser getTypeofuser() {
        return typeofuser;
    }


    public void setTypeofuser(TypeOfUser typeofuser) {
        this.typeofuser = typeofuser;
    }
    

    public String toString() {
        return code + ";" + name + ";" + residence + ";" + email + ";" + ahr + ";" + typeofuser;
    }

    
    public boolean checkCredentials(String inputName, String inputPassword) {
        return this.name.equals(inputName) && this.password.equals(inputPassword);
    }


    public boolean isAmateur() {
        return typeofuser == TypeOfUser.AMATEUR;
    }

    public boolean isProfessional() {
        return typeofuser == TypeOfUser.PROFESSIONAL;
    }

    public boolean isOccasional() {
        return typeofuser == TypeOfUser.OCCASIONAL;
    }
}
