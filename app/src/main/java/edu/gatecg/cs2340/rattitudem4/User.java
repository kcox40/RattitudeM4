package edu.gatecg.cs2340.rattitudem4;

/**
 * Created by kcox8 on 9/28/2017.
 */

public class User {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String userType;

    public User(String firstName, String lastName, String email,
                String username, String password, String userType) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userType = userType;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public boolean equals(Object o) {

        if (null == o) {
            return false;
        }
        if (this == o) {
            return true;
        }
        if (o instanceof User) {
            User thatUser = ((User) (o));
            String pass1 = this.getPassword();
            String u1 = this.getUsername();
            String pass2 = thatUser.getPassword();
            String u2 = thatUser.getPassword();


            if (this.getUsername().equals(thatUser.getUsername())
                    && this.getPassword().equals(thatUser.getPassword())) {
                return true;
            }
        }
        return false;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
