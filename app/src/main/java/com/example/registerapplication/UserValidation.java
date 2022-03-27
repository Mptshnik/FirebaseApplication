package com.example.registerapplication;

import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidation
{
    public boolean CheckEmail(String email)
    {
        String regex = "^(.+)@(.+)$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if(!matcher.matches())
        {
            return false;
        }

        return true;
    }

    public boolean CheckPassword(String password)
    {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        if(!matcher.matches())
        {
            return false;
        }

        return true;
    }

    public boolean PasswordMatch(String password, String repeatPassword)
    {
        if(!password.equals(repeatPassword))
        {
            return false;
        }

        return true;
    }
}
