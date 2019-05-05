package com.example.samplechatapplication;

import java.util.ArrayList;

/*
This class is model class for contacts
 */
public class Contact {
    String id;
    String name;
    ArrayList<ContactEmail> emails = new ArrayList<>();
    ArrayList<ContactPhone> numbers = new ArrayList<>();

    public void addEmail(String address, String type) {
        ContactEmail email = new ContactEmail();
        email.address = address;
        email.type = type;
        emails.add(email);
    }

    public void addNumber(String number, String type) {
        ContactPhone phone = new ContactPhone();
        phone.number = number;
        phone.type = type;
        numbers.add(phone);
    }
}
