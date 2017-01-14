package com.avash.phonebook.model;

public class UserModel {
    public int uID;
    public String userId;
    public String userPassword;
    public String userName;
    public String userAddress;

    public UserModel(int uID, String userId, String userPassword, String userName, String userAddress) {
        this.uID = uID;
        this.userId = userId;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userAddress = userAddress;
    }

    public UserModel(String userId, String userPassword, String userName, String userAddress) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userAddress = userAddress;
    }

    public UserModel() {

    }

    public int getuID() {
        return uID;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserAddress() {
        return userAddress;
    }
}
