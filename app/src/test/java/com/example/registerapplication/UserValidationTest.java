package com.example.registerapplication;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserValidationTest
{
    @Test
    public void CheckEmail_True()
    {
        UserValidation validation = new UserValidation();
        User user = new User("","Ivanov123@mail.ru","");

        assertTrue(validation.CheckEmail(user.Email));
    }

    @Test
    public void  CheckPassword_True()
    {
        UserValidation validation = new UserValidation();
        User user = new User("","Ivanov123@mail.ru","Qwerty!#123");

        assertTrue(validation.CheckPassword(user.Password));
    }

    @Test
    public void PasswordMatch()
    {
        UserValidation validation = new UserValidation();
        User user = new User("","Ivanov123@mail.ru","Qwerty!#123");

        String repeatPassword = "Qwerty!#123";

        assertTrue(validation.PasswordMatch(user.Password, repeatPassword));
    }
}