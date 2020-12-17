package com.example.campy.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.campy.model.Constants;
import com.example.campy.R;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[a-zA-Z])" +      //any letter
                    ".{4,}" +               //at least 4 characters
                    "$");
    Button btnconnx;
    EditText editTextEmail;
    EditText editTextPass;
TextView textView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnconnx = (Button) findViewById(R.id.btnconnx);
        editTextEmail = (EditText)findViewById(R.id.edit_adr);
        editTextPass =(EditText) findViewById(R.id.edit_pass);
        TextView textView = findViewById(R.id. textView ) ;
        SpannableString content = new SpannableString( "Mot de Passe Oublié" ) ;
        content.setSpan( new UnderlineSpan() , 0 , content.length() , 0 ) ;
        textView.setText("Mot de Passe Oublié") ;
        btnconnx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              if (validateEmail() && validatePassword()) {


                        loginUser();

                   }
                }



        });
    }

    private boolean validateEmail() {
        String emailInput = editTextEmail.getText().toString().trim();
        if (emailInput.isEmpty()) {
            editTextEmail.setError("Le champ ne peut pas être vide");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            editTextEmail.setError("S'il vous plaît, mettez une adresse email valide");
            return false;
        } else {
            editTextEmail.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String passwordInput = editTextPass.getText().toString().trim();
        if (passwordInput.isEmpty()) {
            editTextPass.setError("Le champ ne peut pas être vide");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            editTextPass.setError("S'il vous plaît, mettez une Mot de pass valide :/ ");
            return false;
        } else {
            editTextPass.setError(null);
            return true;
        }
    }

    private void loginUser() {
        String Adres = editTextEmail.getText().toString();
        String pass = editTextPass.getText().toString();
        boolean ok = false ;
        if(Adres.equals(Constants.ADRESSE ) && pass.equals(Constants.MOTPASS))
        {
            ok = true ;
        }
        if(ok)
        {
            SharedPreferences sharedPreferences = getSharedPreferences(Constants.MY_PREFS, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(Constants.ISCONNECTED, true);
            editor.putString(Constants.EMAILCONNECTE,Adres);
            editor.apply();
            startActivity(new Intent(MainActivity.this, DashboardActivte.class));
        }else {
            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
            alertDialog.setTitle(":/ Erreur !!!!");
            alertDialog.setMessage("Mot Passe Or/Et Email incorrect");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
             /*   */


        }

    }
