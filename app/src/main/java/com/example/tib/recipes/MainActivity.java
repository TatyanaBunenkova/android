package com.example.tib.recipes;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView text_email, text_phone;
    Button btn_call;
    final int PERMISSIONS_REQUEST_CALL_PHONE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_call = (Button) findViewById(R.id.btn_call);
        text_phone = (TextView) findViewById(R.id.text_phone);
        text_email = (TextView) findViewById(R.id.text_email);
        text_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

                emailIntent.setType("plain/text");
                // Кому
                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,
                        new String[]{text_email.getText().toString()});

                // Зачем
                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
                        "Тестовое сообщение");

                emailIntent.putExtra(android.content.Intent.EXTRA_TEXT,
                        "Текст тестового сообщениия");

                MainActivity.this.startActivity(Intent.createChooser(emailIntent,
                        "Отправка письма..."));
            }

        });
    }public void onClickCallBtn(View view) {


        final Intent phoneIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + Uri.parse(text_phone.getText().toString())));
        try {
            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)  {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, PERMISSIONS_REQUEST_CALL_PHONE);
            }
            else {
                MainActivity.this.startActivity(phoneIntent);
            }
        }
        catch (Exception e) {
            Log.e("Call", "Could not call. " + e.getMessage());
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSIONS_REQUEST_CALL_PHONE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    this.onClickCallBtn(btn_call);
                } else {
                    Log.e("Call", "Could not call. ");
                }
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}


