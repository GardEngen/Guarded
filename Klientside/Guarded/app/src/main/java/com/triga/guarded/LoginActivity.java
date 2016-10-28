package com.triga.guarded;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;


public class LoginActivity extends AppCompatActivity {

    Button loginButton;
    Button registerButton;

    EditText usernameText;
    EditText passwordText;

    Boolean loginMatch;
    ServerConnection serverConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //variables
        this.loginMatch = false;
        serverConnection = new ServerConnection();


        //buttons
        this.loginButton = (Button)findViewById(R.id.loginButton);
        this.registerButton = (Button)findViewById(R.id.registerButton);

        //Text fields
        this.usernameText = (EditText) findViewById(R.id.usernameText);
        this.passwordText = (EditText) findViewById(R.id.passwordText);



        loginButtonAction();
        registerButtonAction();

    }

    private void loginButtonAction()
    {
        loginButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                LoginAction();

            }
        });
    }

    private void registerButtonAction()
    {

        registerButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AddUserActivity.class);
                startActivity(i);

            }
        });
    }


    private void LoginAction()
    {
        //Translates all text fields to string
        Integer username = Integer.parseInt(usernameText.getText().toString());
        String password = passwordText.getText().toString();

        //generate a SHA1 Hash of password to match the password in database
        String hashPassword = new String(Hex.encodeHex(DigestUtils.sha1(password)));

        //The login info matche or not
        if(matchUserByLoginInfo(username,hashPassword))
        {
            System.out.println("DU ER NÅ INNE!!!!!!!! enten som voksen eller som barn");
        }
        else
        {
            Toast.makeText(this, "Somthing went wrong, try agian ", Toast.LENGTH_SHORT).show();
        }



    }
    //TODO MÅ GJØRES PÅ SERVERSIDEN
    //TODO MÅ GENERERE EN INNLOGGINGS TOKEN
    //TODO MÅ HA EN CHECK PÅ OM MAN ER VOKSEN ELLER BARN
    //iterates through all AppUsers too se if the login info matches any AppUser
    // if yes, return true
    //if no, return false
    public Boolean matchUserByLoginInfo(Integer username, String password)
    {

        for(AppUser a : serverConnection.getAllUsersService())
        {

            if(a.getphoneNumber().equals(username) && a.getPassword().equals(password))
            {
                loginMatch = true;
            }
            else
            {
                loginMatch = false;
            }
        }
        return loginMatch;
    }

}
