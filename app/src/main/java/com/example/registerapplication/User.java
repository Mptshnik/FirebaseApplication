package com.example.registerapplication;

import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User
{
    public String PhoneNumber;
    public String Email;
    public String Password;

    public User(String phoneNumber, String email, String password){
        PhoneNumber = phoneNumber;
        Email = email;
        Password = password;
    }

}
