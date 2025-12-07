package models;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String username;
    private String status;
    private boolean isOnline;
    
    public User(String username) {
        this.username = username;
        this.status = "Online";
        this.isOnline = true;
    }
    
    // Getters
    public String getUsername() {
        return username;
    }
    
    public String getStatus() {
        return status;
    }
    
    public boolean isOnline() {
        return isOnline;
    }
    
    // Setters
    public void setStatus(String status) {
        this.status = status;
    }
    
    public void setOnline(boolean online) {
        isOnline = online;
    }
}