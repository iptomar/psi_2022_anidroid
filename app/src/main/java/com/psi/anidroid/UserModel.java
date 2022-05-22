package com.psi.anidroid;

public class UserModel {
    private int id;
    private String username;
    private String email;
    private String password;
    private String roles;

    public UserModel(String username, String email, String password) {
        //this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        if (email.equals("admin@admin.ipt")){
            this.roles = "Admin";
        }else{
            this.roles = "User";
        }

    }
    //toString


    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    //getters e setters
    public String getId() {
        return id+"";
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public String getRoles(){
        return roles;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
