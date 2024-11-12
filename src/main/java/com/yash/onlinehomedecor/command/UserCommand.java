package com.yash.onlinehomedecor.command;

import com.yash.onlinehomedecor.domain.User;
//command is used to convert values obtained from jsp form to domain or entity
public class UserCommand {
    private String name;
    private String email;
    private String password;
    private String role;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // Method to convert UserCommand to User entity
    public User getUser () {
        User user = new User();
        user.setName(this.name);
        user.setEmail(this.email);
        user.setPassword(this.password);
        user.setRole(User.UserRole.valueOf(this.role)); // Assuming role is already set
        return user;
    }
}