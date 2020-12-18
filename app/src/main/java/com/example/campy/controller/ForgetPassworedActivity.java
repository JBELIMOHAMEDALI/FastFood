package com.example.campy.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.campy.R;
import com.example.campy.model.Constants;

public class ForgetPassworedActivity extends AppCompatActivity {
EditText editEmail;
Button btnSend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_passwored);
        btnSend = (Button) findViewById(R.id.btnsend);
        editEmail=(EditText) findViewById(R.id.email);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String to = editEmail.getText().toString();
                String subject = "Votre Mot de Pass";
                String passw = "";
                SharedPreferences preferences = getSharedPreferences(Constants.MY_PREFS, MODE_PRIVATE);
                if (preferences.contains(to)) {
                    passw = preferences.getString(to, "notfound");
                    if (passw != "notfound") {
                        String message = passw;


                        Intent email = new Intent(Intent.ACTION_SEND);
                        email.putExtra(Intent.EXTRA_EMAIL, new String[]{to});
                        email.putExtra(Intent.EXTRA_SUBJECT, subject);
                        email.putExtra(Intent.EXTRA_TEXT, message);

                        //need this to prompts email client only
                        email.setType("message/rfc822");

                        startActivity(Intent.createChooser(email, "Choose an Email client :"));

                    }
                }
                else{
                    AlertDialog alertDialog = new AlertDialog.Builder(ForgetPassworedActivity.this).create();
                    alertDialog.setTitle(":/ Erreur !!!!");
                    alertDialog.setMessage("Email Introuvalble");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }
            }
        });
    }
}