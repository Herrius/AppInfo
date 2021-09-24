package com.example.appinfo;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import Entidades.Reporte;


public class Report extends AppCompatActivity{
    /*implements View.OnClickListener*/
    /*Button btnreport,btnfoto;
    EditText edtdireccion, edtcomentario, edttelefono, edtcorreo;
    private DatabaseReference mDataBase;
    private FirebaseDatabase firebaseDatabase;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
       /* edtdireccion = findViewById(R.id.edtdireccion);
        edtcomentario = findViewById(R.id.edtcomentario);
        edttelefono = findViewById(R.id.edttelefono);
        edtcorreo = findViewById(R.id.edtcorreo);
        btnreport = findViewById(R.id.btnreport);
        btnfoto=findViewById(R.id.btnfoto);
        btnreport.setOnClickListener(this);
        btnfoto.setOnClickListener(this);
        mDataBase= FirebaseDatabase.getInstance().getReference();
        inicializarFirebase();*/
    }

    /*@Override*/
    /*public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnreport: {
                String direccion = edtdireccion.getText().toString();
                String comentario = edtcomentario.getText().toString();
                String telefono = edttelefono.getText().toString();
                String correo = edtcorreo.getText().toString();
                if (!TextUtils.isEmpty(direccion)) {
                    String id = mDataBase.push().getKey();
                    Reporte reporte = new Reporte(id, direccion, comentario, telefono, correo);
                    mDataBase.child("Reporte").child(id).setValue(reporte);
                    Toast.makeText(this, "Reporte registrado", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(this, "Debe rellenar todos los datos", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.btnfoto: {
                break;
            }
        }
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase=FirebaseDatabase.getInstance();
        mDataBase=firebaseDatabase.getReference();
    }*/
}