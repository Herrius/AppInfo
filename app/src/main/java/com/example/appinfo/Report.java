package com.example.appinfo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Report extends AppCompatActivity {
    private static final int PICK_IMAGE = 100;
    Uri imageUri;
    public static final int REQUEST_CODE_GETMESSAGE = 1014;
    private double latitud, longitud;
    private String ubicacion;
    Button direccion;
    FloatingActionButton foto;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        direccion = findViewById(R.id.direccion);
        foto = findViewById(R.id.btnfoto);
        imageView = findViewById(R.id.imageView);
    }

    public void GPS(View view) {
        Intent i = new Intent(getApplicationContext(), MapsActivity.class);
        startActivityForResult(i, REQUEST_CODE_GETMESSAGE);

    }

    public void Camera(View view) {
        openGalleryorPhoto();
    }

    private void openGalleryorPhoto() {
        AlertDialog.Builder builder=new AlertDialog.Builder(Report.this);
        LayoutInflater inflater=getLayoutInflater();
        View dialogView=inflater.inflate(R.layout.alert_dialog_picture_or_photo,null);


        builder.setCancelable(false);
        builder.setView(dialogView);

        ImageView imageViewCamera=dialogView.findViewById(R.id.imageViewCamera);
        ImageView imageViewGallery=dialogView.findViewById(R.id.imageViewGallery);
        AlertDialog alertDialogProfilePicture=builder.create();
        alertDialogProfilePicture.show();

        imageViewCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkAndRequestPermission()){
                    takePictureFromCamera();
                    alertDialogProfilePicture.cancel();
                }
            }
        });
        imageViewGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePictureFromGallery();
                alertDialogProfilePicture.cancel();
            }
        });
        //startActivityForResult(subirgaleria, PICK_IMAGE);
    }

    private void takePictureFromCamera() {
        Intent takePicture=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePicture.resolveActivity(getPackageManager())!=null){
            startActivityForResult(takePicture,2);
        }
    }

    private void takePictureFromGallery() {
        Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto,1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REQUEST_CODE_GETMESSAGE:{
                if (resultCode == Activity.RESULT_OK) {
                    latitud = data.getDoubleExtra("latitud", 0);
                    longitud = data.getDoubleExtra("longitud", 0);
                    ubicacion = data.getStringExtra("direccion");
                    direccion.setText(ubicacion);
                } else {
                    Toast.makeText(this, "Ubicacion cancelada", Toast.LENGTH_SHORT).show();
                }
            }break;
            case 1:
                if(resultCode==RESULT_OK){
                    Uri selectedImageUri=data.getData();
                    imageView.setImageURI(selectedImageUri);
                break;
            }
            case 2:
                if(resultCode==RESULT_OK){
                    Bundle bundle=data.getExtras();
                    Bitmap bitmapImage= (Bitmap) bundle.get("data");
                    imageView.setImageBitmap(bitmapImage);
                }
        }

    }

    private boolean checkAndRequestPermission(){
        if(Build.VERSION.SDK_INT>=23){
            int cameraPermission= ActivityCompat.checkSelfPermission(Report.this, Manifest.permission.CAMERA);
            if (cameraPermission== PackageManager.PERMISSION_DENIED){
                ActivityCompat.requestPermissions(Report.this,new String[]{Manifest.permission.CAMERA},20);
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 20 && grantResults[0]==PackageManager.PERMISSION_GRANTED){

        }else
            Toast.makeText(Report.this,"Permiso Denegado",Toast.LENGTH_SHORT).show();
    }
}

