package com.example.appinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class userReport extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_report);
    }

    public void ReportCategory(View view) {
        Intent i = new Intent(getApplicationContext(),CategoryReports.class);
        startActivity(i);
    }
}