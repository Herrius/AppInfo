package com.example.appinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CategoryReports extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_reports);

    }

    public void Report(View view) {
        Intent i = new Intent(getApplicationContext(),Report.class);
        startActivity(i);
    }
}