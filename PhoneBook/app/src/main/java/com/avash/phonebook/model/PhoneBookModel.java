package com.avash.phonebook.model;

public class PhoneBookModel {
    public int phoneBookID;
    public int uID;
    public String contactName;
    public String contactNumber;
    public String skypeID;
    public String emailID;
    public String image;

    public PhoneBookModel(int phoneBookID, int uID, String contactName, String contactNumber,
                          String skypeID, String emailID, String image) {
        this.phoneBookID = phoneBookID;
        this.uID = uID;
        this.contactName = contactName;
        this.contactNumber = contactNumber;
        this.skypeID = skypeID;
        this.emailID = emailID;
        this.image = image;
    }

    public PhoneBookModel(String contactName, String contactNumber, String skypeID, String emailID, String image) {
        this.contactName = contactName;
        this.contactNumber = contactNumber;
        this.skypeID = skypeID;
        this.emailID = emailID;
        this.image = image;
    }

    public PhoneBookModel() {

    }

    public int getPhoneBookID() {
        return phoneBookID;
    }

    public int getuID() {
        return uID;
    }

    public String getContactName() {
        return contactName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getSkypeID() {
        return skypeID;
    }

    public String getEmailID() {
        return emailID;
    }

    public String getImage() {
        return image;
    }
}
