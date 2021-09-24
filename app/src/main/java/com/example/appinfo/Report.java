package com.example.appinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Report extends AppCompatActivity {
    public static final int REQUEST_CODE_GETMESSAGE=1014;
    private double latitud,longitud;
    private String ubicacion;
    Button direccion;
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        direccion=findViewById(R.id.direccion);
    }

    public void GPS(View view) {
        Intent i = new Intent(getApplicationContext(),MapsActivity.class);
        startActivityForResult(i,REQUEST_CODE_GETMESSAGE);

    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_GETMESSAGE) {
            if (resultCode == Activity.RESULT_OK) {
                latitud = data.getDoubleExtra("latitud",0);
                longitud = data.getDoubleExtra("longitud",0);
                ubicacion = data.getStringExtra("direccion");
                direccion.setText(ubicacion);
            } else {
                Toast.makeText(this, "Ubicacion cancelada", Toast.LENGTH_SHORT).show();
            }
        }

    }

}