package com.example.appinfo;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.appinfo.databinding.ActivityMapsBinding;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {
    public Marker ultimaPosicion;
    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private Button enviar;
    int REQUEST_CODE = 200;
    private TextView txtlatitud, txtlongitud, txtDireccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        txtlatitud = findViewById(R.id.latitud);
        txtlongitud = findViewById(R.id.logintud);
        txtDireccion = findViewById(R.id.direccion);
        enviar = findViewById(R.id.enviarGPS);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("latitud", ultimaPosicion.getPosition().latitude);
                intent.putExtra("longitud", ultimaPosicion.getPosition().longitude);
                intent.putExtra("direccion", txtDireccion.getText());
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onMapReady(GoogleMap googleMap) {

        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap = googleMap;
            mMap.setMyLocationEnabled(true);
            float zoomLevel=18.0f;
            final LatLng huancayo = new LatLng(-12.068054821014954,-75.21008371397862 );
            ultimaPosicion=mMap.addMarker(new MarkerOptions().position(huancayo).title("Marcar el lugar del problema"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(huancayo,zoomLevel));
            mMap.getUiSettings().setZoomControlsEnabled(true);
            mMap.setOnMapClickListener(this);
        }else {
            finish();
        }



    }


    @Override
    public void onMapClick(LatLng latLng) {
        if(ultimaPosicion !=null){
            ultimaPosicion.remove();
        }
        ultimaPosicion=mMap.addMarker(new MarkerOptions().position(latLng).title("Marcar el lugar del problema"));
        txtlatitud.setText("Latitud: "+Double.toString(ultimaPosicion.getPosition().latitude));
        txtlongitud.setText("Longitud: "+Double.toString(ultimaPosicion.getPosition().longitude));
        Geocoder geocoder=new Geocoder(getApplicationContext(), Locale.getDefault());
        try{
            List<Address> direccion=geocoder.getFromLocation(ultimaPosicion.getPosition().latitude,ultimaPosicion.getPosition().longitude,1);
            txtDireccion.setText(direccion.get(0).getAddressLine(0));
        }catch (IOException e){
            e.printStackTrace();
        }
    }



}