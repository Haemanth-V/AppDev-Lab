package com.example.dialer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    // define objects for edit text and button
    EditText edittext;
    Button button;
    final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        edittext = findViewById(R.id.editText);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg)
            {
                String phone_number
                        = edittext.getText().toString();

                Intent phone_intent
                        = new Intent(Intent.ACTION_CALL);

                phone_intent
                        .setData(Uri.parse("tel:"
                                + phone_number));

                if (ContextCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.CALL_PHONE},
                            MY_PERMISSIONS_REQUEST_CALL_PHONE);

                }

                try {
                    startActivity(phone_intent);
                } catch(SecurityException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}