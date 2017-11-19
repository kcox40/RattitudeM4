package edu.gatech.cs2340.rattitudem4;

/** 
 * Created by kcox8 on 9/28/2017. 
 * @author team 57 
 * @version 1 
 */

class User {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String userType;
    /** 
     * constructor of user 
     * @param firstName name of user 
     * @param lastName lastName of user
     * @param email email of new user 
     * @param username of new user 
     * @param password password of account
     * @param userType admin vs normal user 
     */ 

    public User(String firstName, String lastName, String email,
                String username, String password, String userType) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userType = userType;
    }
    /** 
     * setting password 
     * @param password sets password of user 
     */ 
    public void setPassword(String password) {
        this.password = password;
    }
     /** 
     * returns first name 
     * @return first name 
     */ 
    public String getFirstName() {
        return firstName;
    }
     /** 
     * setting firstName
     * @param firstName of user 
     */ 
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    /** 
     * get lastName
     * @return lastName of user 
     */ 
    public String getLastName() {
        return lastName;
    }
    /** 
     * setting lastName
     * @param lastName of user 
     */ 
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    /** 
     * get username 
     * @return username of user 
     */
    private String getUsername() {
        return username;
    }
    /** 
     * setting username 
     * @param username of user 
     */ 
    public void setUsername(String username) {
        this.username = username;
    }
    /** 
     * get password of user 
     * @return password of user 
     */
    private String getPassword() {
        return password;
    }
    /** 
     * is it a real user 
     * @param o user object 
     * @return is it a valid object 
     */
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
    /** 
     * get email of user 
     * @return email of user 
     */ 
    public String getEmail() {
        return email;
    }
    /** 
     * sets email 
     * @param email sets email of user 
     */ 
    public void setEmail(String email) {
        this.email = email;
    }
    /** 
     * get user type 
     * @return user type 
     */ 
    public String getUserType() {
        return userType;
    }
     /** 
     * sets userType
     * @param userType sets admin or normal 
     */ 
    public void setUserType(String userType) {
        this.userType = userType;
    }
}
