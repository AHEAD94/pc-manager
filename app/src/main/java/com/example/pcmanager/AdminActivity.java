package com.example.pcmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
    }

    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.button1: //
                Intent intent1 = new Intent(this, ProjectActivity.class);
                startActivity(intent1);
                break;

            case R.id.button2: //

                break;

            case R.id.button3: // QR code reader

                break;

            case R.id.button4: // 연락처

                break;

            case R.id.prebutton:
                finish();
                break;
        }
    }
}
