package com.example.campy.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.campy.model.Constants;
import com.example.campy.R;

public class IntroductoryActivity extends AppCompatActivity {

    ImageView logo, appName, splashImg;
    LottieAnimationView lottieAnimationView;
    Boolean ok = false ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introductory);

        logo = findViewById(R.id.logo);
        appName = findViewById(R.id.app_name);
        splashImg = findViewById(R.id.img);
        lottieAnimationView = findViewById(R.id.lottie);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                animation ();
                openNextScreen();
               // finish();
            }


        }, Constants.SPLASH_DELAY);

    }
    private void openNextScreen() {
        SharedPreferences sharedPreferences =getSharedPreferences(Constants.MY_PREFS, Context.MODE_PRIVATE);
        Boolean is_connected =sharedPreferences.getBoolean(Constants.ISCONNECTED,false);
        if(is_connected) {
            startActivity(new Intent(IntroductoryActivity.this, DashboardActivte.class));
        }
        else
        {
            startActivity(new Intent(IntroductoryActivity.this, MainActivity.class));
        }

    }

    public void animation ()
    {
        splashImg.animate().translationY(-2000).setDuration(1000).setStartDelay(4000);
        logo.animate().translationY(1400).setDuration(1000).setStartDelay(4000);
        appName.animate().translationY(1400).setDuration(1000).setStartDelay(4000);
        lottieAnimationView.animate().translationY(1400).setDuration(1000).setStartDelay(4000);
        Intent intent = new Intent(IntroductoryActivity.this, MainActivity.class);
        startActivity(intent);
    }












}