package com.example.paulomello.banca_valdir.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.paulomello.banca_valdir.R;

public class SplashActivity extends AppCompatActivity implements  Runnable{

    private static final int DELAY_MILLIS = 2000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handle = new Handler();
        handle.postDelayed(this, DELAY_MILLIS);
    }

    private void changeToMainActivity() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void run( ) {
        changeToMainActivity();
    }
}
