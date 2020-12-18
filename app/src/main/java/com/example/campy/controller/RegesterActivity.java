package com.example.campy.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.campy.R;
import com.example.campy.model.Constants;

public class RegesterActivity extends AppCompatActivity {
Button btnEnrg;
    private EditText cin, email, username, lastname, firstname, birth_date, phone, password, confirmPassword;
    private ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btnEnrg=(Button) findViewById(R.id.btnSave);
        cin = findViewById(R.id.cin);
        email = findViewById(R.id.email);
        username = findViewById(R.id.firstname);
        lastname = findViewById(R.id.lastname);
        firstname = findViewById(R.id.firstname);
        birth_date = findViewById(R.id.birth_date);
        phone = findViewById(R.id.phone);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmPassword);
        btnEnrg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateInputs())
                {
                    register(email.getText().toString(),password.getText().toString());
                }


            }
        });
    }
    private void displayLoader() {
        pDialog = new ProgressDialog(RegesterActivity.this);
        pDialog.setMessage(getString(R.string.establish));
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
    }

    private void register(String email, String passwored) {
       // displayLoader();
        SharedPreferences preferences = getSharedPreferences(Constants.MY_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(email,passwored);
        editor.commit();
    }

    private boolean validateInputs() {
        if (cin.getText().length() != 8) {
            cin.setError(getString(R.string.invalid_input));
            cin.requestFocus();
            return false;
        }
        if (email.getText().toString().length() < 8) {
            email.setError(getString(R.string.invalid_input));
            email.requestFocus();
            return false;
        }
        if (username.getText().toString().length() < 4) {
            username.setError(getString(R.string.invalid_input));
            username.requestFocus();
            return false;
        }
        if (lastname.getText().toString().length() < 3) {
            lastname.setError(getString(R.string.invalid_input));
            lastname.requestFocus();
            return false;
        }
        if (firstname.getText().toString().length() < 3) {
            firstname.setError(getString(R.string.invalid_input));
            firstname.requestFocus();
            return false;
        }
        try {
            String birth_date_text = birth_date.getText().toString();
            if (birth_date_text.length() != 10 || birth_date_text.charAt(4) != '-' || birth_date_text.charAt(7) != '-')
                throw new Exception();
            int test = Integer.valueOf(birth_date_text.substring(0, 4));
            test = Integer.valueOf(birth_date_text.substring(5, 7));
            if (test < 1 || test > 12)
                throw new Exception();
            test = Integer.valueOf(birth_date_text.substring(8, 10));
            if (test < 1 || test > 31)
                throw new Exception();
        } catch (Exception e) {
            birth_date.setError(getString(R.string.invalid_input) + " (Format: yyyy-mm-dd)");
            birth_date.requestFocus();
            return false;
        }
        if (phone.getText().toString().length() < 4) {
            phone.setError(getString(R.string.invalid_input));
            phone.requestFocus();
            return false;
        }
        if (password.getText().toString().length() < 4) {
            password.setError(getString(R.string.invalid_input));
            password.requestFocus();
            return false;
        }
        if (!password.getText().toString().equals(confirmPassword.getText().toString())) {
            confirmPassword.setError(getString(R.string.invalid_input));
            confirmPassword.requestFocus();
            return false;
        }
        return true;
    }
}