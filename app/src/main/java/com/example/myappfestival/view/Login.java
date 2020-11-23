package com.example.myappfestival.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myappfestival.R;
import com.example.myappfestival.business.implementation.UserImpl;
import com.example.myappfestival.business.interfaces.UserInterface;
import com.example.myappfestival.model.User;
import com.google.android.material.textfield.TextInputEditText;
import com.mysql.jdbc.log.LogFactory;

import java.util.logging.Logger;

public class Login extends AppCompatActivity {
    Button btnToSignin;
    Button btnToLogin;
    TextInputEditText usernameField;
    TextInputEditText passwdField;
    TextView response;
    UserInterface userI = new UserImpl();
    Logger logger = Logger.getLogger("LoginLog");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pagesignin);
        init();
    }

    public void init(){
        btnToLogin = (Button)findViewById(R.id.login);
        btnToSignin= (Button)findViewById(R.id.signin);
        usernameField = (TextInputEditText)findViewById(R.id.usernameLogin);
        passwdField = (TextInputEditText)findViewById(R.id.passwordLogin);
        response = (TextView)findViewById(R.id.response);
        btnToSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        myClick(v);
                    }
                }).start();
            }
        });
        btnToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        myClick(v);
                    }
                }).start();
            }
        });
    }

    public void myClick(View v){
       final String username = usernameField.getText().toString();
       final String password = passwdField.getText().toString();
       logger.info("Username = "+username+" / passwd = "+password);
       if(v.getId() == R.id.login) {
           logger.info("Username = " + username + " / passwd = " + password);
           User user = userI.loginUser(username, password);
           if (user == null) {
               response.setText("Error Authentification. Incorrect Password or username");
               response.setTextColor(Color.RED);
           } else {
               Intent intent = new Intent(Login.this, Event.class);
               intent.putExtra("user", user);
               startActivity(intent);
           }
       } else if(v.getId()==R.id.signin) {
           logger.info("Hellooooooooo Signin");
           int status = userI.createUser(username,password);
           if(status==-1){
               response.setText("Error creation of user");
               response.setTextColor(Color.RED);
           } else{
               response.setText("user is created");
               response.setTextColor(Color.GREEN);
           }
       }
    }
}