package com.example.cameraapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;

public class ViewImage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image);

        Bitmap bitmap = (Bitmap) getIntent().getExtras().get("bitmap");

        ImageView imageView = findViewById(R.id.displayImage);
        imageView.setImageBitmap(bitmap);

    }

}