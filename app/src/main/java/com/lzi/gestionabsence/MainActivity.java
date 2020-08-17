package com.lzi.gestionabsence;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void classe(View view) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this,ClasseActivity.class);
        startActivity(intent);
        finish();

    }
}
