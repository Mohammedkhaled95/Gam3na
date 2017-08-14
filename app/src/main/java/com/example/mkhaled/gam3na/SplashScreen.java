package com.example.mkhaled.gam3na;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);



        new CountDownTimer(3000,1000){
            @Override
            public void onTick(long l) {



            }

            @Override
            public void onFinish() {
                Intent i = new Intent(SplashScreen.this, Login.class);
                startActivity(i);
                finish();

            }
        }.start();
    }
}
