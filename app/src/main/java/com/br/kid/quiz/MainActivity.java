package com.br.kid.quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            Thread.sleep(5000);
        Intent login=    new Intent(this,LoginActivity.class);
        startActivity(login);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
