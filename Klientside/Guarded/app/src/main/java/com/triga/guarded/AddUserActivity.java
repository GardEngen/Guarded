package com.triga.guarded;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class AddUserActivity extends AppCompatActivity {

    private Button addUserButton;
    private Switch guardianSwitch;

    private EditText familyCodeText;
    private EditText firstNameText;
    private EditText lastNameText;
    private EditText phoneNumberText;
    private EditText passwordText;
    private EditText repeatPasswordText;

    private Boolean isGuardian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //variables
        this.isGuardian = true;

        //Text fields
        this.familyCodeText = (EditText) findViewById(R.id.familyCodeText);
        this.firstNameText = (EditText) findViewById(R.id.firstNameText);
        this.lastNameText =(EditText) findViewById(R.id.lastNameText);
        this.phoneNumberText = (EditText) findViewById(R.id.phoneNumberText);
        this.passwordText = (EditText) findViewById(R.id.passwordText);
        this.repeatPasswordText = (EditText) findViewById(R.id.repeatPasswordText);

        //Button
        this.addUserButton=(Button)findViewById(R.id.addUserbutton);
        this.guardianSwitch=(Switch)findViewById(R.id.guardianSwitch);

        //Methods
        addUserButtonAction();

    }


    private void addUserButtonAction()
    {
        addUserButton.setOnClickListener(new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            addUser();

        }
    });

    }

    private void addUser()
    {

       //Sets Boolean isGuardian to true or false based ond the state of the switch
        guardianSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                isGuardian = isChecked;
            }
        });

        //Translates all text fields to string
        String fCodeString = familyCodeText.getText().toString();
        String fNString = firstNameText.getText().toString();
        String lNString = lastNameText.getText().toString();
        String pNString = phoneNumberText.getText().toString();
        String pString = passwordText.getText().toString();
        String rPString = repeatPasswordText.getText().toString();

       //check if som of the fields are empty
        if(!(fCodeString.equals("") || fNString.equals("") || lNString.equals("") || pNString.equals("")
                || pString.equals("") || rPString.equals(""))) {

            //check if password match repeatPassword
            if ((pString.equals(rPString))) {
                System.out.println(isGuardian);

                //Creates a SHA1 Hash of password
                String hashPassword = new String(Hex.encodeHex(DigestUtils.sha1(pString)));

                System.out.println(" familyCodeText: "+fCodeString+" firstNameText: "+fNString+" lastNameText: "+lNString+" phoneNumberText: "
                        +pNString+ " passwordText: " +hashPassword+" repeatPasswordText: "+rPString+" isGuardian: "+isGuardian);

                familyCodeText.setText("");
                firstNameText.setText("");
                lastNameText.setText("");
                phoneNumberText.setText("");
                passwordText.setText("");
                repeatPasswordText.setText("");


            }
            else
            {
                Toast.makeText(this, "Your password do not match! try again", Toast.LENGTH_SHORT).show();
            }
        }


    }

}
