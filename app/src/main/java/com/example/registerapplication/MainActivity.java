package com.example.registerapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private String _databaseURL = "https://registerapplication-8b5f3-default-rtdb.firebaseio.com/";

    private EditText _textPhoneNumber;
    private EditText _textEmail;
    private EditText _textPassword;
    private EditText _textRepeatPassword;
    private Button _registerButton;
    private DatabaseReference _database;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _textPhoneNumber = findViewById(R.id.textPhoneNumber);
        _textEmail = findViewById(R.id.textEmail);
        _textPassword = findViewById(R.id.textPassword);
        _textRepeatPassword = findViewById(R.id.textRepeatPassword);
        _registerButton = findViewById(R.id.registerButton);

        FirebaseApp.initializeApp(getApplicationContext());
        _database = FirebaseDatabase.getInstance(_databaseURL).getReference();

        _registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User(_textPhoneNumber.getText().toString(),
                        _textEmail.getText().toString(), _textPassword.getText().toString());
                RegisterUser(user);
            }
        });
    }

    private void RegisterUser(User user)
    {
        UserValidation validation = new UserValidation();

        if(!validation.CheckEmail(user.Email))
        {
            Toast.makeText(getApplicationContext(), "Email введен не корректно", Toast.LENGTH_LONG).show();
            return;
        }
        if(!validation.CheckPassword(user.Password))
        {
            Toast.makeText(getApplicationContext(), "Пароль должен содержать " +
             "спецсимволы и буквы разного регистра. Минимальная длина пароля - 8 символов", Toast.LENGTH_LONG).show();
            return;
        }
        if(!validation.PasswordMatch(user.Password, _textRepeatPassword.getText().toString()))
        {
            Toast.makeText(getApplicationContext(), "Пароли должны совпадать", Toast.LENGTH_LONG).show();
            return;
        }


        _database.push().setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                _textEmail.getText().clear();
                _textPassword.getText().clear();
                _textPhoneNumber.getText().clear();
                _textRepeatPassword.getText().clear();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Ошибка", Toast.LENGTH_LONG).show();
            }
        });

        Toast.makeText(getApplicationContext(), "Успешная регистрация", Toast.LENGTH_LONG).show();
    }

}